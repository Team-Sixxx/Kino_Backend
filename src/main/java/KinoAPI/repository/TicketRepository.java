package KinoAPI.repository;

import KinoAPI.models.Seat;
import KinoAPI.models.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Long> {

    @Override
    List<Ticket> findAll();

    List<Ticket> findAllBySeat(Seat seat);

    List<Ticket> findAllByScreeningScreeningId(Long screeningId);
}
