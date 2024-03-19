package KinoAPI.repository;

import KinoAPI.models.ScreeningHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScreeningHistoryRepository extends JpaRepository<ScreeningHistory, Long> {

    @Override
    List<ScreeningHistory> findAll();
}
