package common.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * Lớp tiện ích hỗ trợ xử lý băm (hash) mật khẩu kèm theo salt, dùng thuật toán
 * SHA-256.
 * <p>
 * Cách thức hoạt động chung:
 * <ol>
 * <li>Sinh một chuỗi salt ngẫu nhiên (hex), độ dài 16 byte (khoảng 32 ký tự
 * hex).</li>
 * <li>Băm mật khẩu theo dạng "salt + password" để tạo ra chuỗi hash SHA-256 (64
 * ký tự hex).</li>
 * <li>Lưu salt và chuỗi hash vào CSDL. Khi cần xác thực, hash lại mật khẩu nhập
 * vào với salt cũ và so sánh.</li>
 * </ol>
 * <p>
 * Khuyến nghị: Trong môi trường sản phẩm (production), nên cân nhắc dùng các
 * thư viện bảo mật chuyên dụng như <strong>bcrypt, Argon2, PBKDF2</strong> để
 * hạn chế brute force hoặc rainbow table tốt hơn.
 *
 * <h3>Ví dụ cách sử dụng</h3>
 * <pre>{@code
 * // 1) Khi người dùng đăng ký/đổi mật khẩu:
 * String salt = PasswordUtils.generateSalt();
 * String hashedPassword = PasswordUtils.hashPassword(rawPassword, salt);
 * // Lưu 'salt' và 'hashedPassword' vào DB.
 *
 * // 2) Khi người dùng đăng nhập:
 * // Lấy 'salt' và 'hashedPassword' từ DB
 * boolean matched = PasswordUtils.verifyPassword(inputPassword, salt, hashedPassword);
 * if (matched) {
 *     // Đăng nhập thành công
 * } else {
 *     // Sai mật khẩu
 * }
 * }</pre>
 *
 * @author (Điền tên/nhóm lập trình viên thực hiện)
 */
public class PasswordUtils
{

    /**
     * Thuật toán băm được sử dụng: SHA-256.
     */
    private static final String HASH_ALGORITHM = "SHA-256";

    /**
     * Độ dài mảng byte cho salt (16 byte).
     */
    private static final int SALT_LENGTH = 16; // Độ dài (byte) cho salt

    /**
     * Tạo chuỗi salt ngẫu nhiên dưới dạng hex (32 ký tự, tương ứng 16 byte).
     *
     * @return Chuỗi hex đại diện cho salt ngẫu nhiên.
     */
    public static String generateSalt()
    {
        byte[] salt = new byte[SALT_LENGTH];
        SecureRandom random = new SecureRandom();
        random.nextBytes(salt);
        return bytesToHex(salt);
    }

    /**
     * Băm mật khẩu bằng SHA-256, ghép salt (ở dạng chuỗi hex) với mật khẩu (mặc
     * định: "salt + password").
     * <p>
     * Chuỗi kết quả là mã hex SHA-256 dài 64 ký tự.
     *
     * @param password Mật khẩu gốc (người dùng nhập)
     * @param salt Chuỗi salt (hex) trước đó sinh ra lưu trong DB
     * @return Hash mật khẩu dưới dạng chuỗi hex (64 ký tự)
     * @throws IllegalArgumentException Nếu password hoặc salt là null
     * @throws RuntimeException Nếu xảy ra lỗi với thuật toán băm hoặc encoding
     */
    public static String hashPassword(String password, String salt)
    {
        if (password == null || salt == null)
        {
            throw new IllegalArgumentException("Password hoặc salt không được null.");
        }
        try
        {
            // Ghép salt + password thành 1 chuỗi
            String saltPlusPassword = salt + password;
            byte[] inputBytes = saltPlusPassword.getBytes("UTF-8");

            // Sử dụng MessageDigest với SHA-256
            MessageDigest md = MessageDigest.getInstance(HASH_ALGORITHM);
            md.update(inputBytes);
            byte[] hashedBytes = md.digest();

            return bytesToHex(hashedBytes);
        } catch (NoSuchAlgorithmException | java.io.UnsupportedEncodingException ex)
        {
            throw new RuntimeException("Lỗi khi hash mật khẩu: " + ex.getMessage(), ex);
        }
    }

    /**
     * Kiểm tra mật khẩu nhập vào có khớp với mật khẩu (đã hash) lưu trong DB.
     * <p>
     * Cách làm: hash lại {@code inputPassword} với {@code storedSalt}, so sánh
     * với {@code storedHash}.
     *
     * @param inputPassword Mật khẩu người dùng vừa nhập
     * @param storedSalt Salt đã lưu trong DB
     * @param storedHash Chuỗi hash mật khẩu (đã lưu trong DB)
     * @return {@code true} nếu khớp, {@code false} nếu không
     */
    public static boolean verifyPassword(String inputPassword, String storedSalt, String storedHash)
    {
        String newHash = hashPassword(inputPassword, storedSalt);
        return newHash.equalsIgnoreCase(storedHash);
    }

    // -------------------------------------------------------------------------
    // Các hàm tiện ích (chuyển đổi mảng byte <-> chuỗi hex)
    // -------------------------------------------------------------------------
    /**
     * Chuyển mảng byte sang chuỗi hex (mỗi byte -> 2 ký tự hex).
     *
     * @param bytes Mảng byte cần chuyển đổi
     * @return Chuỗi hex (viết thường)
     */
    private static String bytesToHex(byte[] bytes)
    {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes)
        {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

    /**
     * Chuyển chuỗi hex thành mảng byte.
     * <p>
     * Ví dụ: "ab10" -> mảng byte [ (byte)0xab, (byte)0x10 ].
     *
     * @param hex Chuỗi hex cần chuyển đổi
     * @return Mảng byte tương ứng
     */
    private static byte[] hexToBytes(String hex)
    {
        int length = hex.length();
        byte[] bytes = new byte[length / 2];
        for (int i = 0; i < length; i += 2)
        {
            bytes[i / 2] = (byte) ((Character.digit(hex.charAt(i), 16) << 4)
                    + Character.digit(hex.charAt(i + 1), 16));
        }
        return bytes;
    }
}
