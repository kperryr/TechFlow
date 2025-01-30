package org.taskntech.tech_flow.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
//import jakarta.validation.constraints.Size;
//import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
public class Ticket extends AbstractEntity {

    private LocalDateTime statusLastUpdated;  // Timestamp of last status change
    private StatusUpdates previousStatus;     // Stores the previous status value
    private LocalDateTime lastEdited;


    @SequenceGenerator(name="seq_id_ticket", allocationSize=1)
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE,generator="seq_id_ticket")
    private int ticketId;

    // UPDATE - Had to increase message details length
    @NotBlank
    @Size(min = 3, max = 255, message = "Name must be between 3 and 255 characters")
    private String details;

    // UPDATE - Changed from int to PriorityValue enum for proper enum handling
    @NotNull(message = "Priority level is required")
    @Enumerated(EnumType.STRING)
    private PriorityValue priority;



    // UPDATE - Changed from String to StatusUpdates enum for proper enum handling
    @Enumerated(EnumType.STRING)
    private StatusUpdates status;

    //NO declarative are needed
    private final LocalDateTime dateSubmitted;

    @NotBlank
    @Size(min = 2, max = 15, message = "Department name must be between 2 and 15 characters" )
    private String clientDepartment;

    //edit after core features are done
    private String notes;

    // UPDATE - Updated constructor to use PriorityValue enum instead of int
    public Ticket(String name, String email, String details, PriorityValue priority, String clientDepartment) {
        super(name, email); //declares superclass values
        this.details = details;
        this.priority = priority;
        this.clientDepartment = clientDepartment;
        this.status = StatusUpdates.NOT_STARTED;  // Initialize status
        this.previousStatus = null;               // Initialize previousStatus
        this.statusLastUpdated = LocalDateTime.now(); // Initialize statusLastUpdated
        this.dateSubmitted = LocalDateTime.now();
    }

    // Added a No-argument constructor
    // UPDATED - to initialize previousStatus and statusLastUpdated
    public Ticket() {
        super("", "");
        this.priority = PriorityValue.LOW;
        this.status = StatusUpdates.NOT_STARTED;
        this.previousStatus = null; // Initialize previousStatus to null when creating a new ticket for the first time
        this.statusLastUpdated = LocalDateTime.now(); // Initialize statusLastUpdated to the current timestamp
        this.dateSubmitted = LocalDateTime.now();
    }


    //Setters and Getters

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public int getTicketId() {
        return ticketId;
    }

    // UPDATE - Added a setter for ticketId for updating
    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

    // UPDATE - Changed to return PriorityValue enum instead of int
    public PriorityValue getPriority() {
        return priority;
    }

    // UPDATE - Changed to accept PriorityValue enum instead of int
    public void setPriority(PriorityValue priority) {
        this.priority = priority;
    }

    // UPDATE - Changed to return StatusUpdates enum instead of String
    public StatusUpdates getStatus() {
        return status;
    }

    // UPDATE - Changed to store enum directly instead of string value
    public void setStatus(StatusUpdates status) {
        this.status = status;
    }

    public LocalDateTime getDateSubmitted() {
        return dateSubmitted;
    }

    // UPDATE - to handle null dates
    public String getDateString(LocalDateTime date) {
        if (date == null) { // check if the date is null
            return "N/A";
        }
        DateTimeFormatter dateFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        return date.format(dateFormatObj);
    }

    public String getClientDepartment() {
        return clientDepartment;
    }

    public void setClientDepartment(String clientDepartment) {
        this.clientDepartment = clientDepartment;
    }

    public LocalDateTime getLastEdited() {
        return lastEdited;
    }

    public void setLastEdited() {
        this.lastEdited= LocalDateTime.now();
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public void setStatusLastUpdated(LocalDateTime statusLastUpdated) {
        this.statusLastUpdated = statusLastUpdated;
    }

    public void setPreviousStatus(StatusUpdates previousStatus) {
        this.previousStatus = previousStatus;
    }

    // UPDATE - added getters for status updates
    public StatusUpdates getPreviousStatus() {
        return previousStatus;
    }

    // UPDATE - added getters for localdatetime
    public LocalDateTime getStatusLastUpdated() {
        return statusLastUpdated;
    }
}