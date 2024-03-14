package KinoAPI.models;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "Screening")
public class Screening {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long screeningId;

    @ManyToOne
    @JoinColumn(name = "filmId")
    private Film film;

    @ManyToOne
    @JoinColumn(name = "theaterId")
    private Theater theater;

    @Column(nullable = false)
    private Date startTime;

    @Column(nullable = false)
    private Date endTime;

    private int numberOfSeats;

    // Getters and setters
}
