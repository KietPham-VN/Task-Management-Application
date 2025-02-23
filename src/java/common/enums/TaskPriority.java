/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common.enums;

/**
 * @author NGHIA
 */
public enum TaskPriority
{
    LOW("Low"), MEDIUM("Medium"), HIGH("High");

    private final String priority;

    TaskPriority(String priority)
    {
        this.priority = priority;
    }

    public String getTaskPriority()
    {
        return this.priority;
    }
}
