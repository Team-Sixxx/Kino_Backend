package KinoAPI.models;

import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.*;
import org.springframework.context.annotation.DependsOn;

@Entity
@Table(name = "Ticket")
@DependsOn("Seat")
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
    @ManyToOne
    @JoinColumn(name = "seatId")
    private Seat seat;

    @Getter   @Setter
    private double price;

    @Getter   @Setter
    @Enumerated(EnumType.STRING)
    private TicketStatus status;

    public enum TicketStatus{
        Available, Reserved, Sold, Unavailable

    }

}