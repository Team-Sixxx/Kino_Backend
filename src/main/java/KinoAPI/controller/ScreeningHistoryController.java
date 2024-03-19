package KinoAPI.controller;

import KinoAPI.models.ScreeningHistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import KinoAPI.repository.ScreeningHistoryRepository;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ScreeningHistoryController {

            private final ScreeningHistoryRepository screeningHistoryRepository;

            @Autowired
            public ScreeningHistoryController(ScreeningHistoryRepository screeningHistoryRepository) {
                this.screeningHistoryRepository = screeningHistoryRepository;
            }

            @PostMapping("/screeningHistories")
            public ResponseEntity<ScreeningHistory> createScreeningHistory(@RequestBody ScreeningHistory screeningHistory) {
                ScreeningHistory createdScreeningHistory = screeningHistoryRepository.save(screeningHistory);
                return new ResponseEntity<>(createdScreeningHistory, HttpStatus.CREATED);
            }

            @GetMapping("/screeningHistories")
            public ResponseEntity<Iterable<ScreeningHistory>> getAllScreeningHistories() {
                Iterable<ScreeningHistory> screeningHistories = screeningHistoryRepository.findAll();
                return new ResponseEntity<>(screeningHistories, HttpStatus.OK);
            }

            @GetMapping("/screeningHistories/{id}")
            public ResponseEntity<ScreeningHistory> getScreeningHistoryById(@PathVariable Long id) {
                Optional<ScreeningHistory> screeningHistory = screeningHistoryRepository.findById(id);
                return screeningHistory.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
            }

            @DeleteMapping("/screeningHistories/{id}")
            public ResponseEntity<HttpStatus> deleteScreeningHistory(@PathVariable Long id) {
                try {
                    screeningHistoryRepository.deleteById(id);
                    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
                } catch (Exception e) {
                    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
                }
            }
}
