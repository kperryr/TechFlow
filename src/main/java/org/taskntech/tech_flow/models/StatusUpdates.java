package org.taskntech.tech_flow.models;

public enum StatusUpdates {

        // Enum for the status updates
        NOT_STARTED("Not Started"),
        IN_PROGRESS("In Progress"),
        DELAYED("Delayed"),
        RESOLVED("Resolved"),
        CLOSED("Closed");

        // The string to be displayed
        private final String displayStatus;

        // Constructor for the enum
        StatusUpdates(String displayStatus) {
                this.displayStatus = displayStatus;
        }

        // Getter for the display status
        public String getDisplayStatus() {
                return displayStatus;
        }
}