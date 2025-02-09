/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.sql.Date;

/**
 *
 * @author anhki
 */
public class User
{
    private int id;
    private String name;
    private String email;
    private String passwordHash;
    private String role;
    private Date timeStamp;

    public User(int id, String name, String email, String passwordHash, String role, Date timeStamp) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.passwordHash = passwordHash;
        this.role = role;
        this.timeStamp = timeStamp;
    }
    
    public User(String name, String email, String passwordHash, String role) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.passwordHash = passwordHash;
        this.role = role;
        this.timeStamp = timeStamp;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public String getRole() {
        return role;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }
    
    
}
