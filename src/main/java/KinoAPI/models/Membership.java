package KinoAPI.models;
import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "Membership")
public class Membership {

    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long membershipId;

    @Getter    @Setter
    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @Getter    @Setter
    @Column(nullable = false)
    private Date startDate;

    @Getter    @Setter
    @Column(nullable = false)
    private Date endDate;

    @Getter    @Setter
    @Enumerated(EnumType.STRING)
    private MembershipStatus status;

    public enum MembershipStatus {
        ACTIVE,
        INACTIVE,
        CANCELLED
    }

    // Getters and setters
}