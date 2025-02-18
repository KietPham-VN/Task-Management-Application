package common.constants;

public class Queries {

    //create new project
    public static final String CREATE_PROJECT = "INSERT INTO dbo.Projects (name, description, createdBy) VALUES (?, ?, ?)";
    //update project
    public static final String UPDATE_PROJECT = 
            "UPDATE dbo.Projects"
            + "SET name = ?, description = ?"
            + "WHERE name = ?";
    //create new task
    public static final String CREATE_TASK
            = "INSERT INTO dbo.Tasks (projectId, name, description, assignedTo, status, priority, dueDate) VALUES (?, ?, ?, ?, ?, ?, ?)";
    //get user by name
    public static final String GET_USER_BY_NAME
            = "SELECT id, name, email, role FROM dbo.Users WHERE name=?";
    //get project by name
    public static final String GET_PROJECT_BY_NAME
            = "SELECT id, name, description, createdBy FROM dbo.Projects WHERE name=?";
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
