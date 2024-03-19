package KinoAPI.controller;

import KinoAPI.models.Seat;
import KinoAPI.repository.SeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
@RestController
@RequestMapping("/api")
public class SeatController {

        private final SeatRepository seatRepository;

        @Autowired
        public SeatController(SeatRepository seatRepository) {
            this.seatRepository = seatRepository;
        }

        @PostMapping("/seats")
        public ResponseEntity<Seat> createSeat(@RequestBody Seat seat) {
            Seat createdSeat = seatRepository.save(seat);
            return new ResponseEntity<>(createdSeat, HttpStatus.CREATED);
        }

        @GetMapping("/seats")
        public ResponseEntity<Iterable<Seat>> getAllSeats() {
            Iterable<Seat> seats = seatRepository.findAll();
            return new ResponseEntity<>(seats, HttpStatus.OK);
        }

        @GetMapping("/seats/{id}")
        public ResponseEntity<Seat> getSeatById(@PathVariable Long id) {
            Optional<Seat> seat = seatRepository.findById(id);
            return seat.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        }

        @PutMapping("/seats/{id}")
        public ResponseEntity<Seat> updateSeat(@PathVariable Long id, @RequestBody Seat seatDetails) {
            Optional<Seat> optionalSeat = seatRepository.findById(id);
            if (optionalSeat.isPresent()) {
                Seat seat = optionalSeat.get();
                seat.setRowNum(seatDetails.getRowNum());
                seat.setSeatNumber(seatDetails.getSeatNumber());
                seat.setTheaterId(seatDetails.getTheaterId());
                Seat updatedSeat = seatRepository.save(seat);
                return new ResponseEntity<>(updatedSeat, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }

        @DeleteMapping("/seats/{id}")
        public ResponseEntity<HttpStatus> deleteSeat(@PathVariable Long id) {
            try {
                seatRepository.deleteById(id);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
}
