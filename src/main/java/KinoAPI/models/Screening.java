package KinoAPI.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.DependsOn;
import java.util.List;
import java.util.Date;

@Entity
@Table(name = "Screening")
public class Screening {

    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long screeningId;

    @Getter    @Setter
    @ManyToOne
    @JoinColumn(name = "filmId")
    private Film film;

    @Getter    @Setter
    @ManyToOne
    @JoinColumn(name = "theaterId")
    private Theater theater;

    @Getter    @Setter
    @Column(nullable = false)
    private Date startTime;

    @Getter    @Setter
    @Column(nullable = false)
    private Date endTime;

    @PostPersist
    public void createTickets() {
        EntityManager entityManager = Persistence.createEntityManagerFactory("yourPersistenceUnitName").createEntityManager();
        entityManager.getTransaction().begin();
        try {
            List<Seat> seats = entityManager.createQuery("SELECT s FROM Seat s WHERE s.theaterId = :theaterId", Seat.class)
                    .setParameter("theaterId", this.theater.getTheaterId())
                    .getResultList();
            for (Seat seat : seats) {
                Ticket ticket = new Ticket();
                ticket.setScreening(this);
                ticket.setSeatId(seat);
                ticket.setPrice(calculateTicketPrice()); // You need to implement this method
                ticket.setStatus(Ticket.TicketStatus.Available);
                ticket.setSeatNumber(seat.getSeatNumber().toString());
                entityManager.persist(ticket);
            }
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        } finally {
            entityManager.close();
        }
    }

    private double calculateTicketPrice() {
        // Implement ticket price calculation logic here
        return 0.0; // Placeholder return value
    }
    private int numberOfSeats;

    // Getters and setters
}
