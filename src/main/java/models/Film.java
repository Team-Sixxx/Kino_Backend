package models;

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

    private int ageRating;

    @Column(nullable = false)
    private Date startTime;

    @Column(nullable = false)
    private Date endDate;

    private boolean extraScreenings;

    @Column(nullable = false)
    private Date releaseDate;


}
