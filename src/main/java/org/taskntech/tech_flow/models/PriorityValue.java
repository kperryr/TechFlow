package org.taskntech.tech_flow.models;

public enum PriorityValue {

    HIGH(2),
    MEDIUM(1),
    LOW(0);

    private final int displayPriority;

    private PriorityValue(int value) {
        this.displayPriority = value;
    }

    public int getPriority() {
        return displayPriority;
    }

    @Override
    public String toString() {
        return this.name(); // Returns "HIGH", "MEDIUM", "LOW"
    }
}