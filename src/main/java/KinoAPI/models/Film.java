package KinoAPI.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "Film")
@Getter @Setter
public class Film {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long filmId;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    private String genre;

    @Column(name = "rating") // Renamed column from ageRating to rating
    private int ageRating; // Keeping this field for backward compatibility

    @Column(name = "filmDuration") // Renamed column from duration to filmDuration
    private int duration; // New column name

    @Column(nullable = false)
    private Date startTime;

    @Column(nullable = false)
    private Date endDate;

    @Column(nullable = false)
    private String movieTrailer;

    private boolean extraScreenings;

    @Column(nullable = false)
    private Date releaseDate;
}
