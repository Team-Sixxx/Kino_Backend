package KinoAPI.repository;

import KinoAPI.models.Film;
import KinoAPI.models.Screening;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface ScreeningRepository extends JpaRepository<Screening, Long> {

    @Override
    List<Screening> findAll();

    List<Screening> findByFilmFilmId(Long filmId);
    //List<Screening> findByStartTime(Date startDate);

    @Query("SELECT s FROM Screening s WHERE DATE(s.startTime) = :startDate")
    List<Screening> findByStartDate(@Param("startDate") Date startDate);



    //List<Screening> findByStartDate(Date startDate);
    //List<Screening> findByStartTimeBetween(LocalDate startDate, LocalDate endDate);
}
