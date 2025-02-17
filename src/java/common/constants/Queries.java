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
}
