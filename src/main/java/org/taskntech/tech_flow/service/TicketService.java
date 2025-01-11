package org.taskntech.tech_flow.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.taskntech.tech_flow.data.TicketRepository;
import org.taskntech.tech_flow.exceptions.TicketNotFoundException;
import org.taskntech.tech_flow.models.StatusUpdates;
import org.taskntech.tech_flow.models.Ticket;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TicketService {

        @Autowired // Used to inject dependencies
        private TicketRepository ticketRepository; // Used to access the repository

        // Add setter for testing
        public void setTicketRepository(TicketRepository ticketRepository) {
                this.ticketRepository = ticketRepository;
        }

        // Create a new ticket
        public Ticket createTicket(Ticket ticket) {
                return ticketRepository.save(ticket);
        }

        // Read all tickets
        public List<Ticket> getAllTickets() { // returns a list type of all tickets called "getAllTickets"
                List<Ticket> tickets = new ArrayList<>(); // creating an empty list to store the tickets
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
        public void deleteTicket(Integer ticketId) {
                ticketRepository.deleteById(ticketId);
        }

        // Update ticket status
        // Using optional<> since it returns null or the ticket
        public Ticket updateTicketStatus(Integer ticketId, StatusUpdates newStatus) {
                // Fetch the ticket by its Id
                Optional<Ticket> retrievedTicket = ticketRepository.findById(ticketId);

                // Check if the ticket exists
                if (retrievedTicket.isPresent()) {
                        Ticket ticket = retrievedTicket.get();
                        // Update the status
                        ticket.setStatus(newStatus);
                        ticket.setLastEdited();
                        return ticketRepository.save(ticket);
                } else {
                        // Created a custom exception folder to handle all exceptions
                        throw new TicketNotFoundException("Ticket not found with ID: " + ticketId);
                }
        }

        // Add or update note
        public Ticket addOrUpdateNote(Integer ticketId, String note) {
                // Fetch the ticket by its ticketId
                Optional<Ticket> retrievedTicket = ticketRepository.findById(ticketId);

                // Check if the ticket exists
                if (retrievedTicket.isPresent()) {
                        Ticket ticket = retrievedTicket.get();
                        // Update the note
                        ticket.setNotes(note);
                        return ticketRepository.save(ticket);
                } else {
                        // throw a customer exception
                        throw new TicketNotFoundException("Ticket not found with ID: " + ticketId);
                }
        }

        // Update ticket priority
        public Ticket updateTicketPriority(Integer ticketId, int newPriority) {
                // Fetch the ticket by its ticketId
                Optional<Ticket> retrievedTicket = ticketRepository.findById(ticketId);

                // Check if ticket exists
                if (retrievedTicket.isPresent()) {
                        Ticket ticket = retrievedTicket.get(); // assigning the ticket object to the ticket variable
                        ticket.setPriority(newPriority);
                        return ticketRepository.save(ticket);
                } else {
                        throw new TicketNotFoundException("Ticket not found with ID: " + ticketId);
                }
        }

        // Update client department
        public Ticket updateClientDepartment(Integer ticketId, String newClientDepartment) {
                // Fetch the ticket details
                Optional<Ticket> retrievedTicket = ticketRepository.findById(ticketId);

                // Check if ticket exist
                if (retrievedTicket.isPresent()) {
                        Ticket ticket = retrievedTicket.get(); // if present, we hold it under "ticket"
                        ticket.setClientDepartment(newClientDepartment);
                        return ticketRepository.save(ticket);
                } else {
                        throw new TicketNotFoundException("Ticket not found with ID: " + ticketId);
                }
        }

        // Update ticket details
        public Ticket updateTicketDetails(Integer ticketId, String newTicketDetails) {
                // Fetch the ticket detials
                Optional<Ticket> retrievedTicket = ticketRepository.findById(ticketId);

                // Check if ticket exist
                if (retrievedTicket.isPresent()) {
                        Ticket ticket = retrievedTicket.get();
                        ticket.setDetails(newTicketDetails);
                        return ticketRepository.save(ticket);
                } else {
                        throw new TicketNotFoundException("Ticket not found with ID: " + ticketId);
                }
        }

        public Ticket findTicketById(Integer ticketId) {
                return ticketRepository.findById(ticketId).orElse(null);
        }
}