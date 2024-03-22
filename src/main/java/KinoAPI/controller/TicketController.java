package KinoAPI.controller;

import KinoAPI.models.Screening;
import KinoAPI.models.Seat;
import KinoAPI.models.Ticket;
import KinoAPI.repository.SeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import KinoAPI.repository.TicketRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class TicketController {

        private final TicketRepository ticketRepository;
        private final SeatRepository seatRepository;

        @Autowired
        public TicketController(TicketRepository ticketRepository, SeatRepository seatRepository) {
            this.ticketRepository = ticketRepository;
            this.seatRepository = seatRepository;
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

        @GetMapping("/tickets/screening/{id}")
        public ResponseEntity<?> getTicketsByScreeningId(@PathVariable Long id) {
            List<Ticket> tickets = ticketRepository.findAllByScreeningScreeningId(id);
            if (!tickets.isEmpty()) {
                return new ResponseEntity<>(tickets, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("No tickets found", HttpStatus.NOT_FOUND);
            }
        }

        @PutMapping("/tickets/{id}")
        public ResponseEntity<Ticket> updateTicket(@PathVariable Long id, @RequestBody Ticket ticketDetails) {
            Optional<Ticket> optionalTicket = ticketRepository.findById(id);
            if (optionalTicket.isPresent()) {
                Ticket ticket = optionalTicket.get();
                ticket.setSeat(ticketDetails.getSeat());
                ticket.setPrice(ticketDetails.getPrice());
                ticket.setStatus(ticketDetails.getStatus());
                Ticket updatedTicket = ticketRepository.save(ticket);
                return new ResponseEntity<>(updatedTicket, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }

    @PutMapping("/tickets/buy")
    public ResponseEntity<String> buyTickets(@RequestBody List<Seat> seats) {
        try {
            for (Seat seat : seats) {
                // Find the ticket associated with the provided seat
                List<Ticket> tickets = ticketRepository.findAllBySeat(seat);
                for (Ticket ticket : tickets) {
                    // Update the status of each ticket to "bought"
                    ticket.setStatus(Ticket.TicketStatus.Sold);
                }
                ticketRepository.saveAll(tickets);
            }
            return new ResponseEntity<>("Tickets bought successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error buying tickets: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/tickets/reserve")
    public ResponseEntity<String> reserveTickets(@RequestBody List<Seat> seats) {
        try {
            for (Seat seat : seats) {
                // Find the ticket associated with the provided seat
                List<Ticket> tickets = ticketRepository.findAllBySeat(seat);
                for (Ticket ticket : tickets) {
                    // Update the status of each ticket to "bought"
                    ticket.setStatus(Ticket.TicketStatus.Reserved);
                }
                ticketRepository.saveAll(tickets);
            }
            return new ResponseEntity<>("Tickets bought successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error buying tickets: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
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


    public List<Ticket> createTicketsForScreening(Screening screening) {
        List<Ticket> tickets = new ArrayList<>();
        List<Seat> seats = seatRepository.findByTheaterId(screening.getTheater().getTheaterId()); // Fetch seats by theaterId
        for (Seat seat : seats) {
            if (seat.isStatus()) { // Check if the seat is available
                Ticket ticket = new Ticket();
                ticket.setScreening(screening);
                ticket.setSeat(seat); // Set the seat directly, not just the ID
                ticket.setPrice(0);
                ticket.setStatus(Ticket.TicketStatus.Available);
                tickets.add(ticket);
            }
        }
        return ticketRepository.saveAll(tickets);
    }


}
