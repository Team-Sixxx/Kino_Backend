package KinoAPI.models;

import jakarta.persistence.*;
import org.springframework.context.annotation.DependsOn;

@Entity
@Table(name = "Ticket")
@DependsOn("seat")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ticketId;

    @ManyToOne
    @JoinColumn(name = "screeningId")
    private Screening screening;

    @OneToOne
    @JoinColumn(name = "seatId")
    private Seat seatId;

    private double price;

    @Enumerated(EnumType.STRING)
    private TicketStatus status;

    @Column(name = "seatNumber")
    private String seatNumber;


    public enum TicketStatus{
        Available, Booked, Cancelled

    }

}