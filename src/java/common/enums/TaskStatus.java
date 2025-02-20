/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common.enums;

/**
 * @author NGHIA
 */
public enum TaskStatus
{
    PENDING("Pending"), IN_PROGRESS("In Progress"), COMPLETED("Completed");

    private final String status;

    TaskStatus(String status)
    {
        this.status = status;
    }

    public String getTaskStatus()
    {
        return this.status;
    }
}
