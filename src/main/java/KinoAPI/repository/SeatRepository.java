package KinoAPI.repository;

import KinoAPI.models.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SeatRepository extends JpaRepository<Seat, Long> {

    @Override
    List<Seat> findAll();
}
