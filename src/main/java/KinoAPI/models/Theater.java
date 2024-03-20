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

}
