package entities;

import java.sql.Date;

public class Task {
    private int _id;
    private int _projectId;
    private String _name;
    private String _description;
    private User _assignedTo;
    private String _status;
    private String _priority;
    private Date _dueDate;
    private Date _createdAt;

    public Task() {
    }

    public Task(int _id, int _projectId, String _name, String _description, User _assignedTo, String _status, String _priority, Date _dueDate, Date _createdAt) {
        this._id = _id;
        this._projectId = _projectId;
        this._name = _name;
        this._description = _description;
        this._assignedTo = _assignedTo;
        this._status = _status;
        this._priority = _priority;
        this._dueDate = _dueDate;
        this._createdAt = _createdAt;
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

    public String getName() {
        return _name;
    }

    public void setName(String _name) {
        this._name = _name;
    }

    public String getDescription() {
        return _description;
    }

    public void setDescription(String _description) {
        this._description = _description;
    }

    public User getAssignedTo() {
        return _assignedTo;
    }

    public void setAssignedTo(User _assignedTo) {
        this._assignedTo = _assignedTo;
    }

    public String getStatus() {
        return _status;
    }

    public void setStatus(String _status) {
        this._status = _status;
    }

    public String getPriority() {
        return _priority;
    }

    public void setPriority(String _priority) {
        this._priority = _priority;
    }

    public Date getDueDate() {
        return _dueDate;
    }

    public void setDueDate(Date _dueDate) {
        this._dueDate = _dueDate;
    }

    public Date getCreatedAt() {
        return _createdAt;
    }

    public void setCreatedAt(Date _createdAt) {
        this._createdAt = _createdAt;
    }
    
    
}
