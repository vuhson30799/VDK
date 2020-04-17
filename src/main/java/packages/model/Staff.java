package packages.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "staff",schema = "public")
public class Staff {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "staff_generator")
    @SequenceGenerator(name = "staff_generator",sequenceName = "staff_id_seq", allocationSize = 1)
    private Long id;

    @Column(name = "first_name")
    @NotNull(message = "First name can not be null")
    private String firstName;

    @Column(name = "last_name")
    @NotNull(message = "Last name can not be null")
    private String lastName;

    @NotNull(message = "Salary can not be null")
    @Column(name = "salary_per_hour")
    private float salaryPerHour;

    @Email(message = "Email has wrong prototype")
    private String email;

    @ManyToOne(targetEntity = Department.class)
    @JoinColumn(name = "id_department", nullable = false)
    private Department department;

    @OneToMany(targetEntity = Salary.class)
    private List<Salary> salaryList;

    @OneToMany(targetEntity = TimeSheet.class)
    private List<TimeSheet> timeSheetList;

}
