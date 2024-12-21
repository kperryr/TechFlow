package org.taskntech.tech_flow.exceptions;

public class TicketNotFoundException extends RuntimeException {
        public TicketNotFoundException(String message) {
                super(message);
        }
}
