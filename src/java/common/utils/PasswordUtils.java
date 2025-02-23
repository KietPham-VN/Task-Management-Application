package common.utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class PasswordUtils
{
    private static final String HASH_ALGORITHM = "SHA-256";
    private static final int SALT_LENGTH = 16;

    private PasswordUtils()
    {
    }

    public static String generateSalt()
    {
        byte[] salt = new byte[SALT_LENGTH];
        SecureRandom random = new SecureRandom();
        random.nextBytes(salt);
        return bytesToHex(salt);
    }

    public static String hashPassword(String password, String salt)
    {
        if (password == null || salt == null)
        {
            throw new IllegalArgumentException("Password hoặc salt không được null.");
        }
        try
        {
            String saltPlusPassword = salt + password;
            byte[] inputBytes = saltPlusPassword.getBytes(StandardCharsets.UTF_8);
            MessageDigest md = MessageDigest.getInstance(HASH_ALGORITHM);
            md.update(inputBytes);
            byte[] hashedBytes = md.digest();
            return bytesToHex(hashedBytes);
        } catch (NoSuchAlgorithmException ex)
        {
            throw new IllegalArgumentException("Lỗi khi hash mật khẩu: " + ex.getMessage(), ex);
        }
    }

    public static boolean verifyPassword(String inputPassword, String storedSalt, String storedHash)
    {
        String newHash = hashPassword(inputPassword, storedSalt);
        return newHash.equalsIgnoreCase(storedHash);
    }

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
