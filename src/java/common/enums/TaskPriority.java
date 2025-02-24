/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common.enums;

/**
 * @author NGHIA
 */
// TaskPriority.java
public enum TaskPriority {
    LOW(1),
    MEDIUM(2),
    HIGH(3);

    private final int value;

    TaskPriority(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static TaskPriority fromValue(int value) {
        for (TaskPriority priority : TaskPriority.values()) {
            if (priority.value == value) {
                return priority;
            }
        }
        throw new IllegalArgumentException("Invalid priority value: " + value);
    }
}
