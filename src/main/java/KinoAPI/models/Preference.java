package KinoAPI.models;
import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.*;

@Entity
@Table(name = "Preference")
public class Preference {

    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long preferenceId;

    @Getter    @Setter
    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @Getter    @Setter
    private String category;

}