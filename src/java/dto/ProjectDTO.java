package dto;

import entities.User;

public class ProjectDTO {

    private String name;
    private String description;
    private int createdBy;

    public ProjectDTO() {
    }

    public ProjectDTO(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public ProjectDTO(String name, String description, int createdBy) {
        this.name = name;
        this.description = description;
        this.createdBy = createdBy;
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

    public int getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(int createdBy) {
        this.createdBy = createdBy;
    }

}
