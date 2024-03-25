package KinoAPI.controller;

import KinoAPI.models.Seat;
import KinoAPI.models.Theater;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import KinoAPI.repository.TheaterRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class TheaterController {

    private final TheaterRepository theaterRepository;
    private final SeatController seatController;

    @Autowired
    public TheaterController(TheaterRepository theaterRepository, SeatController seatController) {
        this.theaterRepository = theaterRepository;
        this.seatController = seatController;
    }

    @GetMapping("/theater")
    public List<Theater> getAllTheaters() {
        return theaterRepository.findAll();
    }

    @PostMapping("/theater")
    public ResponseEntity<Theater> createTheater(@RequestBody Theater theater) {
        Theater createdTheater = theaterRepository.save(theater);
        // Opret sæder for det oprettede teater
        List<Seat> seats = seatController.createSeatsForTheater(createdTheater);
        // Du kan håndtere seats, hvis det er nødvendigt
        return new ResponseEntity<>(createdTheater, HttpStatus.CREATED);
    }

//    @PostMapping("/postTheater")
//    public ResponseEntity<Theater> createTheater(@RequestBody Theater theater) {
//        Theater createdTheater = theaterRepository.save(theater);
//        return new ResponseEntity<>(createdTheater, HttpStatus.CREATED);
//    }

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
