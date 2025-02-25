package dto;

import java.sql.Date;

public class TaskDTO {
    private int id;
    private int projectId;
    private String name;
    private String description;
    private int assignedTo;
    private String status;
    private String priority;
    private Date dueDate;

    public TaskDTO() {
    }

    public TaskDTO(String name, String description, int assignedTo, String status, String priority, Date dueDate) {
        this.name = name;
        this.description = description;
        this.assignedTo = assignedTo;
        this.status = status;
        this.priority = priority;
        this.dueDate = dueDate;
    }

    public TaskDTO(int id, int projectId, String name, String description, int assignedTo, String status, String priority, Date dueDate) {
        this.id = id;
        this.projectId = projectId;
        this.name = name;
        this.description = description;
        this.assignedTo = assignedTo;
        this.status = status;
        this.priority = priority;
        this.dueDate = dueDate;
    }


    public TaskDTO(int projectId, String name, String description, int assignedTo, String status, String priority, Date dueDate) {
        this.projectId = projectId;
        this.name = name;
        this.description = description;
        this.assignedTo = assignedTo;
        this.status = status;
        this.priority = priority;
        this.dueDate = dueDate;
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(int assignedTo) {
        this.assignedTo = assignedTo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }
    
    
}
