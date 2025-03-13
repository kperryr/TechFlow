package org.taskntech.tech_flow.notifications;

import org.taskntech.tech_flow.models.Ticket;

//object of event that occurred
public class TicketUpdatedEvent {

    //ticket that was updated
    private final Ticket ticket;

    //constructor
    public TicketUpdatedEvent(Ticket ticket){
        this.ticket = ticket;
    }

    //getter
    public Ticket getTicket(){
        return ticket;
    }
}
