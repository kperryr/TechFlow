package org.taskntech.tech_flow.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.ui.Model;
import org.taskntech.tech_flow.controllers.ListTicketsController;
import org.taskntech.tech_flow.models.Ticket;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

class ListTicketsControllerTest {

        @Test
        public void listTicketsShouldReturnViewWithTickets() {
                // Arrange - Setup the test environment
                ListTicketsController controller = new ListTicketsController(); // Calls the controller method directly
                Model model = new SimpleModel() {}; // created a mock model called "SimpleModel" that implements Model
                // Act - Call the method to test listTickets, store in variable "result"
                String result = controller.listTickets(model);
                // Assert - Checks the rsults
                assertEquals("tickets/list", result, "Should return view with tickets");
        }

        @Test
        public void displayCreateFormShouldReturnCreateView() {
                // Arrange
                ListTicketsController controller = new ListTicketsController();
                Model model = new SimpleModel();
                // Act
                String result = controller.displayCreateTicketForm(model);
                // Assert
                assertEquals("tickets/create", result, "Should return create form view");
        }

        @Test
        public void processFormShouldRedirectToListWhenValid() {
                // Arrange
                ListTicketsController controller = new ListTicketsController();
                Ticket ticket = new Ticket("test", "test@email.com", "details", 1, "IT");
                // Act
                String result = controller.processCreateTicketForm(ticket);
                // Assert
                assertEquals("redirect:/tickets", result, "Should redirect after valid submission");
        }


        // Learned that instead of listing the exact datatype expected, you can use ? to represent ANY data type.
        private class SimpleModel implements Model {
                @Override
                public Model addAttribute(String name, Object value) {
                        return this;
                }

                @Override
                public Model addAllAttributes(Collection<?> attributeValues) {
                        return this;
                }

                @Override
                public Model addAllAttributes(Map<String, ?> attributes) {
                        return this;
                }

                @Override
                public Model mergeAttributes(Map<String, ?> attributes) {
                        return this;
                }

                @Override
                public boolean containsAttribute(String attributeName) {
                        return false;
                }

                @Override
                public Object getAttribute(String attributeName) {
                        return null;
                }

                @Override
                public Map<String, Object> asMap() {
                        return new HashMap<>();
                }

                @Override
                public Model addAttribute(Object attributeValue) {
                        return this;
                }
        }
}