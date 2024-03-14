package KinoAPI.repository;

import KinoAPI.models.Employee;
import KinoAPI.models.Film;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FilmRepository extends JpaRepository<Film, Long> {

    @Override
    List<Film> findAll();
}
