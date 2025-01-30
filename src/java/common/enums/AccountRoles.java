package common.enums;

public enum AccountRoles
{
    PROJECT_MANAGER("PROJECT_MANAGER"),
    TEAM_MEMBER("TEAM_MEMBER");

    private final String roleName;

    AccountRoles(String roleName)
    {
        this.roleName = roleName;
    }

    public String getRoleName()
    {
        return roleName;
    }
}
