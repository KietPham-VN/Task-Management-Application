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

    public int getId()
    {
        return _id;
    }

    public User(String _name, String _email, String _passwordHash, String _role, String _salt) {
        this._name = _name;
        this._email = _email;
        this._passwordHash = _passwordHash;
        this._salt = _salt;
        this._role = _role;
    }

    public User() {
    }

    public void setId(int _id)
    {
        this._id = _id;
    }

    public String getName()
    {
        return _name;
    }

    public void setName(String _name)
    {
        this._name = _name;
    }

    public String getEmail()
    {
        return _email;
    }

    public void setEmail(String _email)
    {
        this._email = _email;
    }

    public String getPasswordHash()
    {
        return _passwordHash;
    }

    public void setPasswordHash(String _passwordHash)
    {
        this._passwordHash = _passwordHash;
    }

    public String getSalt()
    {
        return _salt;
    }

    public void setSalt(String _salt)
    {
        this._salt = _salt;
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

}
