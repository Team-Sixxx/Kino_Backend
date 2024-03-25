package KinoAPI.services;

import KinoAPI.models.Theater;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import KinoAPI.repository.TheaterRepository;

@Service
public class TheaterService {

    private final TheaterRepository theaterRepository;

    @Autowired
    public TheaterService(TheaterRepository theaterRepository) {
        this.theaterRepository = theaterRepository;
    }

    public Theater createTheater(Theater theater) {
        return theaterRepository.save(theater);
    }
}