/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common.enums;

/**
 * @author NGHIA
 */
// TaskStatus.java
public enum TaskStatus
{
    PENDING(1),
    IN_PROGRESS(2),
    COMPLETED(3);

    private final int value;

    TaskStatus(int value)
    {
        this.value = value;
    }

    public int getValue()
    {
        return value;
    }

    public static TaskStatus fromValue(int value)
    {
        for (TaskStatus status : TaskStatus.values())
        {
            if (status.value == value)
            {
                return status;
            }
        }
        throw new IllegalArgumentException("Invalid status value: " + value);
    }

    @Override
    public String toString()
    {
        return this.name();
    }

}
