package KinoAPI.controller;

import KinoAPI.models.Theater;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import KinoAPI.services.TheaterService;
import KinoAPI.repository.TheaterRepository;



@RestController
@RequestMapping("/api")
public class TheaterController {

    private final TheaterService theaterService;
    private final TheaterRepository theaterRepository;
    @Autowired
    public TheaterController(TheaterService theaterService, TheaterRepository theaterRepository) {
        this.theaterService = theaterService;
        this.theaterRepository = theaterRepository;
    }

    @PostMapping("/postTheater")
    public ResponseEntity<Theater> createTheater(@RequestBody Theater theater) {
        Theater createdTheater = theaterService.createTheater(theater);
        return new ResponseEntity<>(createdTheater, HttpStatus.CREATED);
    }

    @GetMapping("/theaters")
    public ResponseEntity<Page<Theater>> getTheaters(Pageable pageable) {
        Page<Theater> theaters = theaterRepository.findAll(pageable);
        return new ResponseEntity<>(theaters, HttpStatus.OK);
    }
}
