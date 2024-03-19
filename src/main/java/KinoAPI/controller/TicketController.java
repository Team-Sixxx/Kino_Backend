package KinoAPI.controller;

import KinoAPI.models.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import KinoAPI.repository.TicketRepository;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class TicketController {

        private final TicketRepository ticketRepository;

        @Autowired
        public TicketController(TicketRepository ticketRepository) {
            this.ticketRepository = ticketRepository;
        }

        @PostMapping("/tickets")
        public ResponseEntity<Ticket> createTicket(@RequestBody Ticket ticket) {
            Ticket createdTicket = ticketRepository.save(ticket);
            return new ResponseEntity<>(createdTicket, HttpStatus.CREATED);
        }

        @GetMapping("/tickets")
        public ResponseEntity<Iterable<Ticket>> getAllTickets() {
            Iterable<Ticket> tickets = ticketRepository.findAll();
            return new ResponseEntity<>(tickets, HttpStatus.OK);
        }

        @GetMapping("/tickets/{id}")
        public ResponseEntity<Ticket> getTicketById(@PathVariable Long id) {
            Optional<Ticket> ticket = ticketRepository.findById(id);
            return ticket.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        }

        @PutMapping("/tickets/{id}")
        public ResponseEntity<Ticket> updateTicket(@PathVariable Long id, @RequestBody Ticket ticketDetails) {
            Optional<Ticket> optionalTicket = ticketRepository.findById(id);
            if (optionalTicket.isPresent()) {
                Ticket ticket = optionalTicket.get();
                ticket.setSeatId(ticketDetails.getSeatId());
                ticket.setPrice(ticketDetails.getPrice());
                ticket.setStatus(ticketDetails.getStatus());
                ticket.setSeatNumber(ticketDetails.getSeatNumber());
                Ticket updatedTicket = ticketRepository.save(ticket);
                return new ResponseEntity<>(updatedTicket, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }

        @DeleteMapping("/tickets/{id}")
        public ResponseEntity<HttpStatus> deleteTicket(@PathVariable Long id) {
            try {
                ticketRepository.deleteById(id);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
}
