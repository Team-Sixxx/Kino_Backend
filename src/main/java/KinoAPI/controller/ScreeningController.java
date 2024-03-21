package KinoAPI.controller;

import KinoAPI.models.Screening;
import KinoAPI.models.Seat;
import KinoAPI.models.Ticket;
import KinoAPI.repository.ScreeningRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ScreeningController {

        private final ScreeningRepository screeningRepository;
        private final TicketController ticketController;

        @Autowired
        public ScreeningController(ScreeningRepository screeningRepository, TicketController ticketController) {
            this.screeningRepository = screeningRepository;
            this.ticketController = ticketController;
        }

        @GetMapping("/screenings")
        public List<Screening> getAllScreenings() {
            return screeningRepository.findAll();
        }

        @GetMapping("/screenings/{id}")
        public ResponseEntity<Screening> getScreeningById(@PathVariable Long id) {
            Optional<Screening> screening = screeningRepository.findById(id);
            return screening.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        }


    @GetMapping("/film/screenings/{id}")
    public ResponseEntity<?> getScreeningByFilmId(@PathVariable Long id) {
        List<Screening> screenings = screeningRepository.findByFilmFilmId(id);

        if (!screenings.isEmpty()) {
            return new ResponseEntity<>(screenings, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No screenings found", HttpStatus.NOT_FOUND);
        }
    }



    @PostMapping("/screenings")
    public ResponseEntity<Screening> createScreening(@RequestBody Screening screening) throws NoSuchFieldException {
        Screening createdScreening = screeningRepository.save(screening);
        List<Ticket> tickets = ticketController.createTicketsForScreening(createdScreening);
        return new ResponseEntity<>(createdScreening, HttpStatus.CREATED);
    }

    @PutMapping("/screenings/{id}")
        public ResponseEntity<Screening> updateScreening(@PathVariable Long id, @RequestBody Screening screeningDetails) {
            Optional<Screening> optionalScreening = screeningRepository.findById(id);
            if (optionalScreening.isPresent()) {
                Screening screening = optionalScreening.get();
                screening.setFilm(screeningDetails.getFilm());
                screening.setTheater(screeningDetails.getTheater());
                screening.setStartTime(screeningDetails.getStartTime());
                // Set other fields if needed
                Screening updatedScreening = screeningRepository.save(screening);
                return new ResponseEntity<>(updatedScreening, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }

        @DeleteMapping("/screenings/{id}")
        public ResponseEntity<HttpStatus> deleteScreening(@PathVariable Long id) {
            try {
                screeningRepository.deleteById(id);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }


    private double calculateTicketPrice() {
        // Implement ticket price calculation logic here
        return 0.0; // Placeholder return value
    }
}
