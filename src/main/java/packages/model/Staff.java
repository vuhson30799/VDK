package packages.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "staff",schema = "public")
public class Staff {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @NotNull
    @Column(name = "salary_per_hour")
    private float salaryPerHour;

    @ManyToOne(targetEntity = Department.class)
    @JoinColumn(name = "id_department", nullable = false)
    private Department department;

    @OneToMany(targetEntity = Salary.class)
    private List<Salary> salaryList;

    @OneToMany(targetEntity = TimeSheet.class)
    private List<TimeSheet> timeSheetList;

}
