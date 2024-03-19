package KinoAPI.models;
import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.*;
import org.springframework.context.annotation.DependsOn;

@Entity
@Table(name = "Ticket")
@DependsOn("seat")
public class Ticket {

    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ticketId;

    @Getter   @Setter
    @ManyToOne
    @JoinColumn(name = "screeningId")
    private Screening screening;

    @Getter   @Setter
    @OneToOne
    @JoinColumn(name = "seatId")
    private Seat seatId;

    @Getter   @Setter
    private double price;

    @Getter   @Setter
    @Enumerated(EnumType.STRING)
    private TicketStatus status;

    @Getter   @Setter
    @Column(name = "seatNumber")
    private String seatNumber;


    public enum TicketStatus{
        Available, Booked, Unavailable

    }

}