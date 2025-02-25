/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author NGHIA
 */
public class AuthenticatedUser {
    private int _id;
    private String _name;
    private String _email;
    private String _role;

    public int getId() {
        return _id;
    }

    public String getName() {
        return _name;
    }

    public String getEmail() {
        return _email;
    }

    public String getRole() {
        return _role;
    }

    public AuthenticatedUser() {
    }

    public AuthenticatedUser(User user){
        this._id=user.getId();
        this._email=user.getEmail();
        this._name=user.getName();
        this._role=user.getRole();
    }
}
