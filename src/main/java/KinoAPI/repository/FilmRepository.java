package KinoAPI.repository;

import KinoAPI.models.Film;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FilmRepository extends JpaRepository<Film, Long> {

    @Override
    List<Film> findAll();
    Optional<Film> findByFilmId(Long filmId);
}
