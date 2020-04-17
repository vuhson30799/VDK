package packages.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "department", schema = "public")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "department_generator")
    @SequenceGenerator(name = "department_generator",sequenceName = "department_id_seq", allocationSize = 1)
    private Long id;

    @NotNull
    private String name;

    @OneToMany
    private List<Staff> staffList;
}
