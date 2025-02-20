
package entities;

import dto.ProjectDTO;
import java.sql.Date;


public class Project
{
    private int projectId;
    private String name;
    private String description;
    private int createBy;
    private Date createAt;

    public Project() {
    }
    

    public Project(String name, String description, int createBy) {
        this.name = name;
        this.description = description;
        this.createBy = createBy;
    }

    public Project(int projectId, String name, String description, int createBy, Date createAt) {
        this.projectId = projectId;
        this.name = name;
        this.description = description;
        this.createBy = createBy;
        this.createAt = createAt;
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

    public int getCreateBy() {
        return createBy;
    }

    public void setCreateBy(int createBy) {
        this.createBy = createBy;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }
    
    
}
