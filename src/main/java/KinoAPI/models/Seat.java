package KinoAPI.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.DependsOn;

@Entity
@Table(name = "Seat")
@DependsOn("Theater")
public class Seat {

    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SeatId")
    private Integer seatId;

    @Getter    @Setter
    @Column(name = "theaterId")
    private Long theaterId;

    @Getter    @Setter
    @Column(name = "RowNum")
    private Integer rowNum;
    @Getter    @Setter
    @Column(name = "SeatNumber")
    private Integer seatNumber;

    @Getter    @Setter
    @Column(name = "SeatStatus")
    private boolean status;

    @ManyToOne
    @JoinColumn(name = "theaterId", referencedColumnName = "theaterId", insertable = false, updatable = false)
    private Theater theater;
}
