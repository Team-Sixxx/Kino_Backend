package models;

import jakarta.persistence.*;

@Entity
@Table(name = "Ticket")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ticketId;

    @ManyToOne
    @JoinColumn(name = "screeningId")
    private Screening screening;

    private int seatId;

    private double price;

    @Enumerated(EnumType.STRING)
    private TicketStatus status;

    @Column(name = "seatNumber")
    private String seatNumber;


    public enum TicketStatus{
        Available, Booked, Cancelled

    }

}