package common.constants;

//create new task
// login
public class Queries {

    private Queries() {
    }

    public static final String CREATE_PROJECT
            = "INSERT INTO dbo.Projects (name, description, createdBy) "
            + "VALUES (?, ?, ?) ";
    public static final String UPDATE_PROJECT
            = "UPDATE dbo.Projects"
            + "SET name = ?, description = ?"
            + "WHERE name = ?";
    public static final String CREATE_TASK
            = "INSERT INTO dbo.Tasks (projectId, name, description, assignedTo, status, priority, dueDate) VALUES (?, ?, ?, ?, ?, ?, ?)";

    public static final String GET_USER_BY_NAME
            = "SELECT [id], [name], [email], [role] "
            + "FROM [Users] "
            + "WHERE name=? ";

    // login
    public static final String LOGIN
            = "SELECT [id], [passwordHash], [salt], [role] "
            + "FROM [Users] "
            + "WHERE email = ?";

    // getting all information after logging in
    public static final String GET_USER_INFO
            = "SELECT [id], [name], [role], [createdAt] "
            + "FROM [User] "
            + "WHERE [email] = ? AND [passwordHash] = ?";

    // get project whose user evolve 
    public static final String GET_PROJECTS_BY_USER
            = "SELECT * "
            + "FROM [Projects] "
            + "WHERE [createdBy] = ?";

    public static final String REGISTER
            = "INSERT INTO Users(name,email,passwordHash,role,salt) "
            + "VALUES(?,?,?,?,?)";
}
