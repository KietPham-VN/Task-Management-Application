package entities;

import java.sql.Date;

public class User
{

    private int _id;
    private String _name;
    private String _email;
    private String _passwordHash;
    private String _salt;
    private String _role;
    private Date _timeStamp;
    private int teamMemberId;

    public int getId()
    {
        return _id;
    }
    
    public User(int id,String _name, String _email, String _passwordHash, String _role, String _salt) {
        this._id = id;
        this._name = _name;
        this._email = _email;
        this._passwordHash = _passwordHash;
        this._salt = _salt;
        this._role = _role;
    }

    public User(String name, String email, String passwordHash, String role, String salt) {
        this._name = name;
        this._email = email;
        this._passwordHash = passwordHash;
        this._salt = salt;
        this._role = role;
    }

    public User() {
    }

    public void setId(int id)
    {
        this._id = id;
    }

    public String getName()
    {
        return _name;
    }

    public void setName(String name)
    {
        this._name = name;
    }

    public String getEmail()
    {
        return _email;
    }

    public void setEmail(String email)
    {
        this._email = email;
    }

    public String getPasswordHash()
    {
        return _passwordHash;
    }

    public void setPasswordHash(String passwordHash)
    {
        this._passwordHash = passwordHash;
    }

    public String getSalt()
    {
        return _salt;
    }

    public void setSalt(String salt)
    {
        this._salt = salt;
    }

    public String getRole()
    {
        return _role;
    }

    public void setRole(String _role)
    {
        this._role = _role;
    }

    public Date getTimeStamp()
    {
        return _timeStamp;
    }

    public void setTimeStamp(Date _timeStamp)
    {
        this._timeStamp = _timeStamp;
    }

    public void setTeamMemberId(int teamMemberId) {
        this.teamMemberId = teamMemberId;
    }

    public int getTeamMemberId() {
        return teamMemberId;
    }

}
