package KinoAPI.controller;

import KinoAPI.models.Film;
import KinoAPI.models.Screening;
import KinoAPI.models.Seat;
import KinoAPI.models.Ticket;
import KinoAPI.repository.FilmRepository;
import KinoAPI.repository.ScreeningRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

@RestController
@RequestMapping("/api")
public class ScreeningController {

        private final ScreeningRepository screeningRepository;
        private final TicketController ticketController;
    private final FilmRepository filmRepository;

        @Autowired
        public ScreeningController(ScreeningRepository screeningRepository, TicketController ticketController, FilmRepository filmRepository) {
            this.screeningRepository = screeningRepository;
            this.ticketController = ticketController;
            this.filmRepository = filmRepository;
        }

        @GetMapping("/screenings")
        public List<Screening> getAllScreenings() {
            return screeningRepository.findAll();
        }

/*    @GetMapping("/screenings/{id}")
    public ResponseEntity<List<Screening>> getScreenings(
            @PathVariable(required = false) Long id,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date startDate
    ) {
        // Check if id is null
        if (id == null) {
            // If id is null, retrieve all screenings for the specified start date
            if (startDate != null) {
                List<Screening> screenings = screeningRepository.findByStartDate(startDate);
                if (screenings.isEmpty()) {
                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                } else {
                    return new ResponseEntity<>(screenings, HttpStatus.OK);
                }
            } else {
                // If startDate is null, retrieve all screenings
                List<Screening> screenings = screeningRepository.findAll();
                if (screenings.isEmpty()) {
                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                } else {
                    return new ResponseEntity<>(screenings, HttpStatus.OK);
                }
            }
        } else {
            // If id is not null, retrieve the screening by id
            Optional<Screening> screening = screeningRepository.findById(id);
            return screening.map(value -> new ResponseEntity<>(Collections.singletonList(value), HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        }
    }*/




    @GetMapping("/film/screenings")
    public ResponseEntity<?> getScreeningsByFilmId(@RequestParam(required = false) Long id, @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate) {
        if (id == null) {
            // If id is null, retrieve all screenings for the specified start date
            if (startDate != null) {
                // Convert LocalDate to Date with time set to beginning of the day
                Date startDateTime = Date.from(startDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
                List<Screening> screenings = screeningRepository.findByStartDate(startDateTime);
                if (!screenings.isEmpty()) {
                    return new ResponseEntity<>(screenings, HttpStatus.OK);
                } else {
                    return new ResponseEntity<>("No screenings found", HttpStatus.NOT_FOUND);
                }
            } else {
                return new ResponseEntity<>("Start date not provided", HttpStatus.BAD_REQUEST);
            }
        } else {
            // If id is not null, retrieve the screenings by film id
            Optional<Film> film = filmRepository.findByFilmId(id);
            if (film.isPresent()) {
                List<Screening> screenings = screeningRepository.findByFilmFilmId(id);
                if (!screenings.isEmpty()) {
                    return new ResponseEntity<>(screenings, HttpStatus.OK);
                } else {
                    return new ResponseEntity<>("No screenings found for the film", HttpStatus.NOT_FOUND);
                }
            } else {
                return new ResponseEntity<>("Film not found", HttpStatus.NOT_FOUND);
            }
        }
    }






/*    @GetMapping("/film/screenings/{id}")
    public ResponseEntity<?> getScreeningByFilmId(@PathVariable Long id,  @RequestParam(required = false) Date startDate) {
        List<Screening> screenings = screeningRepository.findByFilmFilmId(id);

        if (!screenings.isEmpty()) {
            return new ResponseEntity<>(screenings, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No screenings found", HttpStatus.NOT_FOUND);
        }
    }*/



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
