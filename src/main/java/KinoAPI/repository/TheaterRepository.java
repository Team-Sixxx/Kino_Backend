package KinoAPI.repository;

import KinoAPI.models.Theater;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface TheaterRepository extends JpaRepository<Theater, Long> {

    @Override
    List<Theater> findAll();
}


