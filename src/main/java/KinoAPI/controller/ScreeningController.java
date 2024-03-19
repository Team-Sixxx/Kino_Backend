package KinoAPI.controller;

import KinoAPI.models.Screening;
import KinoAPI.repository.ScreeningRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ScreeningController {

        private final ScreeningRepository screeningRepository;

        @Autowired
        public ScreeningController(ScreeningRepository screeningRepository) {
            this.screeningRepository = screeningRepository;
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

        @PostMapping("/screenings")
        public ResponseEntity<Screening> createScreening(@RequestBody Screening screening) {
            Screening createdScreening = screeningRepository.save(screening);
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

}
