package org.taskntech.tech_flow.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.taskntech.tech_flow.data.TicketRepository;
import org.taskntech.tech_flow.models.Ticket;

import java.util.ArrayList;
import java.util.List;

@Service
public class TicketService {

        @Autowired // Used to inject dependencies
        private TicketRepository ticketRepository; // Used to access the repository

        // Create a new ticket
        public Ticket createTicket(Ticket ticket) {
                return ticketRepository.save(ticket);
        }

        // Read all tickets
        public List<Ticket> getAllTickets() { // returns a list type of all tickets called "getAllTickets"
                List<Ticket> tickets = new ArrayList<> {}; // creating an empty list to store the tickets
                ticketRepository.findAll().forEach(tickets::add); // Iterates over repo/database, returns all tickets and adds them to the list
                return tickets;

        }

        // Update a ticket
        public Ticket updateTicket(Ticket ticket) {
                if (ticketRepository.existsById(ticket.getTicketId())) { // checks if the ticket exists
                        return ticketRepository.save(ticket); // returns the updated ticket
                }
                return null; // Or throw exception
        }

        // Delete a ticket
        public void deleteTicket(int ticketId) {
                ticketRepository.deleteById(ticketId);
        }

        // Update ticket status
        public Ticket updateTicketStatus(Integer ticketId, String newStatus) {

        }

}
