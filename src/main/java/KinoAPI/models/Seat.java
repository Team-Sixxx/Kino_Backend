package KinoAPI.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.DependsOn;

@Entity
@Table(name = "Seat")
@DependsOn("Theater")
public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SeatId")
    private Integer seatId;

    @Column(name = "theaterId")
    private Long theaterId;

    @Column(name = "RowNum")
    private Integer rowNum;

    @Column(name = "SeatNumber")
    private Integer seatNumber;

    @Column(name = "Status")
    private String status;


    @ManyToOne
    @JoinColumn(name = "theaterId", referencedColumnName = "theaterId", insertable = false, updatable = false)
    private Theater theater;
}
