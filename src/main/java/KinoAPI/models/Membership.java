package KinoAPI.models;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "Membership")
public class Membership {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long membershipId;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @Column(nullable = false)
    private Date startDate;

    @Column(nullable = false)
    private Date endDate;

    @Enumerated(EnumType.STRING)
    private MembershipStatus status;

    public enum MembershipStatus {
        ACTIVE,
        INACTIVE,
        CANCELLED
    }

    // Getters and setters
}