package org.taskntech.tech_flow.controllers;

import jakarta.validation.Valid;
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

        @GetMapping
        public String listTickets(Model model) {
                model.addAttribute("tickets", ticketService.getAllTickets());
                return "tickets/list";
        }

        // Display the create ticket form
        @GetMapping("/create")
        public String displayCreateTicketForm(Model model) {
                // model.addAttribute("ticket", new Ticket());
                return "tickets/create";
        }

        // Used Springs BindingResult with @Valid instead of Try/Catch
        @PostMapping("/create")
        public String processCreateTicketForm(@ModelAttribute @Valid Ticket ticket,
                                              BindingResult result) {
                if (result.hasErrors()) { // If validation fails...
                        return "tickets/create"; // stay on the form page...
                }
                ticketService.createTicket(ticket); // Save the valid ticket
                return "redirect:/tickets"; // Then redirect to the ticket list
        }
}