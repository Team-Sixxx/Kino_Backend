package KinoAPI.repository;

import KinoAPI.models.Film;
import KinoAPI.models.Screening;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScreeningRepository extends JpaRepository<Screening, Long> {

    @Override
    List<Screening> findAll();

    List<Screening> findByFilmFilmId(Long filmId);
}
