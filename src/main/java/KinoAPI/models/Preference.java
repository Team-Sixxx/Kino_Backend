package KinoAPI.models;

import jakarta.persistence.*;

@Entity
@Table(name = "Preference")
public class Preference {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long preferenceId;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    private String category;

}