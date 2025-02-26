package common.constants;

//create new task
// login
public class Queries {

    private Queries() {
    }

    public static final String CREATE_PROJECT
            = "INSERT INTO dbo.Projects (name, description, createdBy) "
            + "VALUES (?, ?, ?) ";
    public static final String CREATE_TASK
            = "INSERT INTO dbo.Tasks (projectId, name, description, assignedTo, status, priority, dueDate) VALUES (?, ?, ?, ?, ?, ?, ?)";
    
    public static final String UPDATE_TASK
            = "UPDATE dbo.Tasks "
            + " SET projectId = ?, name = ?, description = ?, assignedTo = ?, status = ?, priority = ?, dueDate = ?"
            + " WHERE id = ?";
    
    public static final String GET_TASK_BY_ID
            = "SELECT * FROM dbo.Tasks WHERE id = ?";

    public static final String GET_USER_BY_NAME
            = "SELECT [id], [name], [email], [role] "
            + "FROM [Users] "
            + "WHERE name=? ";

    // login
    public static final String LOGIN
            = "SELECT [id], [name], [email], [passwordHash], [salt], [role] "
            + "FROM [Users] "
            + "WHERE email = ?";

    // getting all information after logging in
    public static final String GET_USER_INFO
            = "SELECT id, name, role, createdAt "
            + "FROM User "
            + "WHERE email = ? AND passwordHash = ?";
    
    
    public static final String GET_PROJECT_LIST = "SELECT id, name, description, createdBy, createdAt "
            + "FROM Projects "
            + "WHERE createdBy = ?";
    
    public static final String GET_TASK_LIST  = "SELECT [id], [name], [role], [createdAt] "
            + "FROM [User] "
            + "WHERE [email] = ? AND [passwordHash] = ?";

    // get project whose user evolve 
    public static final String GET_PROJECTS_BY_USER
            = "SELECT p.id, p.name, p.description, p.createdBy, p.createdAt FROM Projects p JOIN ProjectMembers pm ON p.id = pm.projectId WHERE pm.userId = ?;";

    public static final String REGISTER
            = "INSERT INTO Users(name,email,passwordHash,role,salt) "
            + "VALUES(?,?,?,?,?)";
    
    public static final String GET_TASKS_BY_PROJECT
            ="SELECT * FROM Tasks "
            + "WHERE projectId = ?";
    
    public static final String GET_PROJECT_BY_ID
            ="SELECT * "
            + "FROM [Projects] "
            + "WHERE id = ?";
    
    public static final String DELETE_TASK_BY_ID
            ="DELETE FROM Tasks WHERE id = ?";
    
    public static final String GET_TASK_WITH_ASSIGN_USER
            ="SELECT t.*, u.id AS userId, u.name AS userName, u.email AS userEmail " +
                       "FROM Tasks t " +
                       "LEFT JOIN ProjectMembers pm ON t.assignedTo = pm.id " +
                       "LEFT JOIN Users u ON pm.userId = u.id "+ 
                        "WHERE t.projectId = ?";
    
    public static final String ADD_USER_TO_PROJECT = "INSERT INTO ProjectMembers (projectId, userId) VALUES (?, ?)";
    
    public static final String GET_USER_NOT_IN_PROJECT = "SELECT u.* FROM Users u " +
                       "WHERE u.id NOT IN (SELECT pm.userId FROM ProjectMembers pm WHERE pm.projectId = ?)";

    public static final String GET_USER_IN_PROJECT = "SELECT u.*, pm.id AS teamMemberId" +
            " FROM Users u" +
            " JOIN ProjectMembers pm ON u.id = pm.userId" +
            " WHERE pm.projectId = ?";
    
    public static final String GET_PROJECT_ID_BY_TASK_ID 
            = "SELECT projectId "
            + "FROM Tasks "
            + "WHERE id = ?";
    
    public static final String GET_PROJECT_USER_IS_IN ="SELECT p.* FROM Projects p WHERE p.id IN (SELECT pm.projectId FROM ProjectMembers pm WHERE pm.userId = ?)";
    
}
