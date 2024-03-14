package KinoAPI.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Seat")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SeatId")
    private Long seatId;

    @Column(name = "theaterId")
    private Long theaterId;

    @Column(name = "RowNumber")
    private Integer rowNumber;

    @Column(name = "SeatNumber")
    private Integer seatNumber;

    @Column(name = "Status")
    private String status;


    @ManyToOne
    @JoinColumn(name = "theaterId", referencedColumnName = "theaterId", insertable = false, updatable = false)
    private Theater theater;
}
