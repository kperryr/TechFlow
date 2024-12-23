package org.taskntech.tech_flow.controllers;

import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

        // Using a Try/Catch, originally tried BindingResult but didnt work properly
        @PostMapping("/create")
        public String processCreateTicketForm(@ModelAttribute Ticket ticket) {
                try {
                        ticketService.createTicket(ticket);
                        return "redirect:/tickets";
                } catch (ValidationException e) {
                        return "tickets/create";
                }
        }

        public void setTicketService(TicketService ticketService) {
                this.ticketService = ticketService;
        }
}