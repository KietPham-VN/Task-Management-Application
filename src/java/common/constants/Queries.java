package common.constants;

public class Queries
{
    // login
    public static final String LOGIN
            = "SELECT [hashed_password], [salt], [role] "
            + "FROM [Users] "
            + "WHERE email = ?";
       
    
    // geting all infor after logging in
    public static final String GET_USER_INFO
            = "SELECT id, name, role, createdAt "
            + "FROM User "
            + "WHERE email = ? AND passwordHash = ?";
}