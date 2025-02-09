package dto;

import exceptions.ValidationError;
import exceptions.ValidationException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserDTO
{
    public static void validateRegister(String name, String email, String password, String role) throws ValidationException{
        List<ValidationError> errors = new ArrayList<>();

        if(name==null||name.trim().isEmpty()) errors.add(new ValidationError("name","Name cannot be empty"));
        
        Pattern emailPattern = Pattern.compile("/^\\S+@\\S+\\.\\S+$/");
        Matcher emailMatcher = emailPattern.matcher(email);
        if(!emailMatcher.find()) errors.add(new ValidationError("email","Email is not valid"));
        
        Pattern passwordPattern = Pattern.compile("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$");
        Matcher passwordMatcher = passwordPattern.matcher(password);
        if(!passwordMatcher.find()) errors.add(new ValidationError("password","Password must have a minimum of eight characters, at least one letter and one number"));
        
        if(!(role.equals("Project Manager")||role.equals("Team Member"))) errors.add(new ValidationError("role","Role must be either 'Project Manager' or 'Team Member'"));
    
        if (!errors.isEmpty()) {
            throw new ValidationException(errors);
        }
    }
}
