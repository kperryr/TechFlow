package org.taskntech.tech_flow.controllers;

import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.taskntech.tech_flow.models.Ticket;
import org.taskntech.tech_flow.service.TicketService;

@Controller
@RequestMapping("/tickets")
public class ListTicketsController {

        @Autowired
        private TicketService ticketService;

        // List all tickets.
        @GetMapping
        public String listTickets(Model model) {
                model.addAttribute("tickets", ticketService.getAllTickets());
                return "tickets/list";
        }

        // Display the form to create a new ticket.
        @GetMapping("/create")
        public String displayCreateTicketForm(Model model) {
                model.addAttribute("ticket", new Ticket()); // Ensure the form has a ticket object
                return "tickets/create";
        }

        // Process the ticket creation form.
        @PostMapping("/create")
        public String processCreateTicketForm(
                @Valid @ModelAttribute("ticket") Ticket ticket,
                BindingResult bindingResult,
                Model model) {
                if (bindingResult.hasErrors()) {
                        model.addAttribute("errorMessage", "Please fix the errors in the form");
                        return "tickets/create";
                }

                try {
                        ticketService.createTicket(ticket);
                        return "redirect:/tickets";
                } catch (ValidationException e) {
                        model.addAttribute("errorMessage", e.getMessage());
                        return "tickets/create";
                }
        }

        // Setter for unit testing
        public void setTicketService(TicketService ticketService) {
                this.ticketService = ticketService;
        }
}