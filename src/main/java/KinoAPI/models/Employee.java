package KinoAPI.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Long employeeId;

    @Column(nullable = false)
    @Getter @Setter
    private String name;
    @Getter @Setter
    private String role;
    @Getter @Setter
    private String email;
    @Getter @Setter
    private String phone;

    @Column(nullable = false)
    @Getter @Setter
    private String password;

}
