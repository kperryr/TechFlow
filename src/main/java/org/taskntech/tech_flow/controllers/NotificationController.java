package org.taskntech.tech_flow.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;

//returns requests as they are (JSON) instead of being mapped to view
@RestController
public class NotificationController {

    //spring class that sends messages to websocket client
    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    //methods that takes a string and sends it to websocket
    public void sendNotification(String message) {
        messagingTemplate.convertAndSend("/topic/notifications", message);
    }
}
