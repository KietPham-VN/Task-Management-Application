package common.enums;

public enum AccountRole
{
    PROJECT_MANAGER("PROJECT_MANAGER"),
    TEAM_MEMBER("TEAM_MEMBER");

    private final String roleName;

    AccountRole(String roleName)
    {
        this.roleName = roleName;
    }

    public String getRoleName()
    {
        return roleName;
    }
}
