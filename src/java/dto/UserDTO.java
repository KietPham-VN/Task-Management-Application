package dto;

import java.util.regex.Pattern;

public class UserDTO
{

    private static final String EMAIL_REGEX = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";

    public static boolean verifyLogin(String email, String password)
    {
        if (email == null || email.trim().isEmpty())
        {
            return false;
        }
        if (!Pattern.matches(EMAIL_REGEX, email))
        {
            return false;
        }
        return !(password == null || password.trim().isEmpty());
    }

}
