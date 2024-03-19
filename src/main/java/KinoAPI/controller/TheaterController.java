package KinoAPI.controller;

import KinoAPI.models.Theater;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import KinoAPI.repository.TheaterRepository;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class TheaterController {

    private final TheaterRepository theaterRepository;

    @Autowired
    public TheaterController(TheaterRepository theaterRepository) {
        this.theaterRepository = theaterRepository;
    }

    @PostMapping("/theaters")
    public ResponseEntity<Theater> createTheater(@RequestBody Theater theater) {
        Theater createdTheater = theaterRepository.save(theater);
        return new ResponseEntity<>(createdTheater, HttpStatus.CREATED);
    }

    @GetMapping("/theaters")
    public ResponseEntity<Page<Theater>> getAllTheaters(Pageable pageable) {
        Page<Theater> theaters = theaterRepository.findAll(pageable);
        return new ResponseEntity<>(theaters, HttpStatus.OK);
    }

    @GetMapping("/theaters/{id}")
    public ResponseEntity<Theater> getTheaterById(@PathVariable Long id) {
        Optional<Theater> theater = theaterRepository.findById(id);
        return theater.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/theaters/{id}")
    public ResponseEntity<Theater> updateTheater(@PathVariable Long id, @RequestBody Theater theaterDetails) {
        Optional<Theater> optionalTheater = theaterRepository.findById(id);
        if (optionalTheater.isPresent()) {
            Theater theater = optionalTheater.get();
            theater.setNumberOfRows(theaterDetails.getNumberOfRows());
            theater.setSeatsPerRow(theaterDetails.getSeatsPerRow());
            // Set other fields if needed
            Theater updatedTheater = theaterRepository.save(theater);
            return new ResponseEntity<>(updatedTheater, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/theaters/{id}")
    public ResponseEntity<HttpStatus> deleteTheater(@PathVariable Long id) {
        try {
            theaterRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
