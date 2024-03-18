package KinoAPI.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Theater")
public class Theater {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long theaterId;
@Getter
@Setter
    private int numberOfRows;
    @Getter
    @Setter
    private int seatsPerRow;


}