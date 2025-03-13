package org.taskntech.tech_flow.notifications;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.taskntech.tech_flow.controllers.NotificationController;


@Component
public class TicketUpdateListener {

    private final NotificationController notificationController;

    public TicketUpdateListener(NotificationController notificationController){
        this.notificationController = notificationController;
    }

    //when event TicketUpdateEvent is triggered
    @EventListener
    public void handleTicketUpdatedEvent(TicketUpdatedEvent event){

        String message = "Ticket Updated: ID " + event.getTicket().getTicketId();
        notificationController.sendNotification(message);

    }

}
