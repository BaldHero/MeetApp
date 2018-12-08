package pl.sda.meetapp.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Builder
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String firstName;

    private String lastName;

    private String password;

    private String email;

    @Enumerated(EnumType.STRING)
    private Department department;

    @ManyToMany
    @JoinTable(name = "EMPLOYEE_MEETING",
            joinColumns = {@JoinColumn(name = "EMPLOYEE_ID")},
            inverseJoinColumns = {@JoinColumn(name = "MEETING_ID")})
    private List<Meeting> meetings;

    //...
}
