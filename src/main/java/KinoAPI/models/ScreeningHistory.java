package KinoAPI.models;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "ScreeningHistory")
public class ScreeningHistory {

    @Id
    @ManyToOne
    @JoinColumn(name = "screeningId")
    private Screening screening;

    @Column(nullable = false)
    private Date startTime;

    @Column(nullable = false)
    private Date endTime;

    private int attendees;


}
