package entities;

import common.enums.TaskPriority;
import common.enums.TaskStatus;
import java.sql.Date;

public class Tasks
{
    private int _id;
    private int _projectId;
    private String name;
    private String description;
    private int _assidnedTo;
    private TaskStatus _status;
    private TaskPriority _priority;
    private Date _dueDate;
    private Date _createdAt;

    public Tasks() {
    }

    public int getId() {
        return _id;
    }

    public int getProjectId() {
        return _projectId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getAssidnedTo() {
        return _assidnedTo;
    }

    public TaskStatus getStatus() {
        return _status;
    }

    public TaskPriority getPriority() {
        return _priority;
    }

    public Date getDueDate() {
        return _dueDate;
    }

    public Date getCreatedAt() {
        return _createdAt;
    }

    public void setId(int _id) {
        this._id = _id;
    }

    public void setProjectId(int _projectId) {
        this._projectId = _projectId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAssidnedTo(int _assidnedTo) {
        this._assidnedTo = _assidnedTo;
    }

    public void setStatus(TaskStatus _status) {
        this._status = _status;
    }

    public void setPriority(TaskPriority _priority) {
        this._priority = _priority;
    }

    public void setDueDate(Date _dueDate) {
        this._dueDate = _dueDate;
    }

    public void setCreatedAt(Date _createdAt) {
        this._createdAt = _createdAt;
    }
    
    
}
