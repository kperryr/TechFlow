package org.taskntech.tech_flow.service;

import jakarta.validation.ValidationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.taskntech.tech_flow.data.TicketRepository;
import org.taskntech.tech_flow.models.PriorityValue;
import org.taskntech.tech_flow.models.StatusUpdates;
import org.taskntech.tech_flow.models.Ticket;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TicketServiceTest {

        @Mock
        private TicketRepository ticketRepository;

        @InjectMocks
        private TicketService ticketService;

        private Ticket testTicket;

        @BeforeEach
        void setUp() {
                testTicket = new Ticket("Test User", "test@email.com", "Test Details", PriorityValue.LOW, "IT");
                testTicket.setTicketId(1);
        }

        @Test
        void validStatusTransition_NotStartedToInProgress_ShouldSucceed() {
                testTicket.setStatus(StatusUpdates.NOT_STARTED);
                when(ticketRepository.findById(1)).thenReturn(Optional.of(testTicket));
                when(ticketRepository.save(any(Ticket.class))).thenReturn(testTicket);

                Ticket updatedTicket = ticketService.updateTicketStatus(1, StatusUpdates.IN_PROGRESS);
                assertEquals(StatusUpdates.IN_PROGRESS, updatedTicket.getStatus());
        }


}