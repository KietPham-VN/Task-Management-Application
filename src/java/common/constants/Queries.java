package common.constants;

public class Queries
{
    public static final String createProject() {
        String query = "INSERT INTO dbo.Projects (name, description, createdBy) VALUES (?, ?, ?)";
        return query;
    }
    
    public static final String getUserByName() {
        String query = "SELECT id, name, email, role FROM dbo.Users WHERE name=?";
        return query;
    }
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
    
    public static final String GET_PROJECT_LIST = "SELECT id, name, description, createdBy, createdAt"
            + "FROM Projects"
            + "WHERE createdBy = ?";
    
    public static final String GET_TASK_LIST = "SELECT ";
}
