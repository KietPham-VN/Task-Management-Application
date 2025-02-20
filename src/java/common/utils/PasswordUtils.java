package common.utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class PasswordUtils
{
    private PasswordUtils()
    {
    }

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
     * @param salt     Chuỗi salt (hex) trước đó sinh ra lưu trong DB
     * @return Hash mật khẩu dưới dạng chuỗi hex (64 ký tự)
     * @throws IllegalArgumentException Nếu password hoặc salt là null
     * @throws RuntimeException         Nếu xảy ra lỗi với thuật toán băm hoặc encoding
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
            byte[] inputBytes = saltPlusPassword.getBytes(StandardCharsets.UTF_8);

            // Sử dụng MessageDigest với SHA-256
            MessageDigest md = MessageDigest.getInstance(HASH_ALGORITHM);
            md.update(inputBytes);
            byte[] hashedBytes = md.digest();

            return bytesToHex(hashedBytes);
        } catch (NoSuchAlgorithmException ex)
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
     * @param storedSalt    Salt đã lưu trong DB
     * @param storedHash    Chuỗi hash mật khẩu (đã lưu trong DB)
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
}
