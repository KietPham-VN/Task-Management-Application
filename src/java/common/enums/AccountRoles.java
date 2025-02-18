package common.enums;

public enum AccountRoles
{
    PROJECT_MANAGER("Project Manager"),
    TEAM_MEMBER("Team Member");

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
