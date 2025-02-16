package common.constants;

public class Queries
{
    // login
    public static final String LOGIN
            = "SELECT [passwordHash], [salt], [role] "
            + "FROM [Users] "
            + "WHERE email = ?";
       
    
    // geting all infor after logging in
    public static final String GET_USER_INFO
            = "SELECT id, name, role, createdAt "
            + "FROM User "
            + "WHERE email = ? AND passwordHash = ?";
    
    public static final String REGISTER = "INSERT INTO Users(name,email,passwordHash,role,salt) VALUES(?,?,?,?,?)";
}