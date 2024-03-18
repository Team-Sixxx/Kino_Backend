package KinoAPI.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "ScreeningHistory")
public class ScreeningHistory {

    @Getter    @Id
    @ManyToOne
    @JoinColumn(name = "screeningId")
    private Screening screening;

    @Getter    @Setter
    @Column(nullable = false)
    private Date startTime;

    @Getter    @Setter
    @Column(nullable = false)
    private Date endTime;

    private int attendees;


}
