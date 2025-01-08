package org.taskntech.tech_flow.controllers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.taskntech.tech_flow.models.PriorityValue;
import org.taskntech.tech_flow.models.Ticket;
import org.taskntech.tech_flow.service.TicketService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ListTicketsControllerTest {

        @Mock // creates a mock object of the specified type (TicketService in this case)
        private TicketService ticketService;

        @Mock // creates a mock object of the specified type (Model in this case)
        private Model model;

        // Injects the mocks (ticketService and model) into the controller object under test
        @InjectMocks
        private ListTicketsController controller;

        @Test
        public void listTicketsShouldReturnViewWithTickets() {
                // Arrange: Mock the behavior of ticketService to return an empty list of tickets
                List<Ticket> tickets = new ArrayList<>();
                when(ticketService.getAllTickets()).thenReturn(tickets);

                // Act: Call the method under test
                String result = controller.listTickets(model);

                // Assert: Verify the method returns the correct view name
                assertEquals("tickets/list", result, "Should return view with tickets");
        }

        @Test
        public void displayCreateFormShouldReturnCreateView() {
                // Arrange - Using mocked model
                // Model object is already being mocked with the @Mock annotation

                // Act: Call the method under test
                String result = controller.displayCreateTicketForm(model);

                // Assert: Verify the method returns the correct view name
                assertEquals("tickets/create", result, "Should return create form view");
        }

        @Test
        public void processFormShouldRedirectToListWhenValid() {
                // Arrange: Mock a valid Ticket and BindingResult
                Ticket testTicket = new Ticket("test", "test@email.com", "details", PriorityValue.LOW, "IT");
                when(ticketService.createTicket(any(Ticket.class))).thenReturn(testTicket);

                BindingResult bindingResult = mock(BindingResult.class);
                when(bindingResult.hasErrors()).thenReturn(false); // THis simulates no validation errors

                Model model = mock(Model.class);

                // Act: Call the method under test
                String result = controller.processCreateTicketForm(testTicket, bindingResult, model);

                // Assert: Verify the method returns the correct redirect URL
                assertEquals("redirect:/tickets", result, "Should redirect to tickets list");
        }

}