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
public class ProjectMembers {
    private int _id;
    private int _projectId;
    private int _userId;

    public ProjectMembers() {
    }

    public int getId() {
        return _id;
    }

    public void setId(int _id) {
        this._id = _id;
    }

    public int getProjectId() {
        return _projectId;
    }

    public void setProjectId(int _projectId) {
        this._projectId = _projectId;
    }

    public int getUserId() {
        return _userId;
    }

    public void setUserId(int _userId) {
        this._userId = _userId;
    }
    
    
}
