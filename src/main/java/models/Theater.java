package models;

import jakarta.persistence.*;

@Entity
@Table(name = "Theater")
public class Theater {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long theaterId;

    private int numberOfRows;

    private int seatsPerRow;

    // Getters and setters
}