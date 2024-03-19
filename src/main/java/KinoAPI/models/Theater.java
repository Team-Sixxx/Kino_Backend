package KinoAPI.models;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Theater")
public class Theater {

    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long theaterId;

    @Getter
    @Setter
    @Column(name = "NumberOfRows")
    private int numberOfRows;

    @Getter
    @Setter
    @Column(name = "SeatsPerRow")
    private int seatsPerRow;

    @PostPersist
    public void createSeats() {
        EntityManager entityManager = Persistence.createEntityManagerFactory("yourPersistenceUnitName").createEntityManager();
        entityManager.getTransaction().begin();
        try {
            for (int i = 1; i <= numberOfRows; i++) {
                for (int j = 1; j <= seatsPerRow; j++) {
                    Seat seat = new Seat();
                    seat.setTheaterId(this.theaterId);
                    seat.setRowNum(i);
                    seat.setSeatNumber(j);
                    seat.setStatus(true);
                    entityManager.persist(seat);
                }
            }
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        } finally {
            entityManager.close();
        }
    }
}
