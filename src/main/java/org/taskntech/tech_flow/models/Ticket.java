package org.taskntech.tech_flow.models;

import jakarta.persistence.*;
//import jakarta.validation.constraints.Size;
//import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
public class Ticket extends AbstractEntity {

    @Id
    @GeneratedValue
    private int ticketId;

    //@NotBlank
    //@Size(min = 3, max = 30, message = "Name must be between 3 and 30 characters")
    private String details;

    //@NotNull(message= "Priority level is required")
    private int priority;

    // UPDATED - Enum for the ticket status
    @Enumerated(EnumType.STRING)
    private StatusUpdates status = StatusUpdates.NOT_STARTED; // Default status

    //NO declarative are needed
    //Going to switch to java.sql.timestamp
    private String dateSubmitted;

    //@NotBlank
    //@Size(min = 2, max = 15, message = "Department name must be between 2 and 15 characters" )
    private String clientDepartment;

    //NO declarative are needed
    //Going to switch to java.sql.timestamp
    private String lastEdited;

    //edit after core features are done
    private String notes;

    public Ticket(String name, String email, String details, int priority, String clientDepartment){
        super(name,email);
        this.details= details;
        this.priority=priority;
        this.clientDepartment=clientDepartment;
        setDateSubmitted();
    }

    // Added a No-argument constructor
    public Ticket() {
        super("", ""); // Set default values for abstract entity fields
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public int getTicketId() {
        return ticketId;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    // UPDATED - Since status is now an enum
    public StatusUpdates getStatus() {
        return status;
    }

    // UPDATED - Since status is now an enum
    public void setStatus(StatusUpdates status) {
        this.status = status;
        setLastEdited(); // Update the last edited timestamp when status changes
    }

    public String getDateSubmitted() {
        return dateSubmitted;
    }

    private void setDateSubmitted() {

        LocalDateTime dateObj = LocalDateTime.now();
        DateTimeFormatter dateFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedDate = dateObj.format(dateFormatObj);
        this.dateSubmitted = formattedDate;
    }

    public String getClientDepartment() {
        return clientDepartment;
    }

    public void setClientDepartment(String clientDepartment) {
        this.clientDepartment = clientDepartment;
    }

    public String getLastEdited() {
        return lastEdited;
    }

    public void setLastEdited() {
        LocalDateTime dateObj = LocalDateTime.now();
        DateTimeFormatter dateFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedDate = dateObj.format(dateFormatObj);
        this.lastEdited= formattedDate;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }


}
