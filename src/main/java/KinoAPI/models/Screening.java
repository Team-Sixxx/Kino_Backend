package KinoAPI.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
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
    @JoinColumn(name = "filmId", unique = false) // Disable uniqueness constraint
    private Film film;

    @Getter    @Setter
    @ManyToOne
    @JoinColumn(name = "theaterId", unique = false) // Disable uniqueness constraint
    private Theater theater;

    @Getter    @Setter
    @Column(nullable = false)
    private Date startTime;

    @Getter    @Setter
    @Column(nullable = false)
    private Date endTime;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Ticket> tickets;

}

    // Getters and setters

