package common.constants;

public class Queries
{

    public static final String CREATE_PROJECT
            = "INSERT INTO dbo.Projects (name, description, createdBy) "
            + "VALUES (?, ?, ?) ";

    public static final String GET_USER_BY_NAME
            = "SELECT [id], [name], [email], [role] "
            + "FROM [Users] "
            + "WHERE name=? ";

    // login
    public static final String LOGIN
            = "SELECT [id], [passwordHash], [salt], [role] "
            + "FROM [Users] "
            + "WHERE email = ?";

    // geting all infor after logging in
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
