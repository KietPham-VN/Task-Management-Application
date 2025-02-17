package dto;

import exceptions.ValidationException;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserDTO
{
    private static final String EMAIL_REGEX = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
    private static final String PASSWORD_REGEX = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$";
    
    public static void validateRegister(String name, String email, String password, String role) throws ValidationException{
        HashMap<String,String> errors = new HashMap<>();

        if(name==null||name.trim().isEmpty()) errors.put("name","Name cannot be empty");
        
        if(!Pattern.matches(EMAIL_REGEX, email)) errors.put("email", "Email is not vaild");
        
        if(!Pattern.matches(PASSWORD_REGEX,password))  errors.put("password","Password must have a minimum of eight characters, at least one letter and one number");
        
        if(!(role.equals("Project Manager")||role.equals("Team Member"))) errors.put("role","Role must be either 'Project Manager' or 'Team Member'");
    
        if (!errors.isEmpty()) {
            throw new ValidationException(errors);
        }
    }

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
