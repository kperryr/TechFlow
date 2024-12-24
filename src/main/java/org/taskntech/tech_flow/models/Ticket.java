package org.taskntech.tech_flow.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
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

    //find declarative
    //edit after core features are done
    private String status;//edit enum

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

    public String getStatus() {
        return status;
    }

    /*public void setStatus(String status) { needs to be editted after enum is done
        this.status = status;
    }*/

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
