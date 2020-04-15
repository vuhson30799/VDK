package packages.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "salary", schema = "public")
public class Salary {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Size(min = 1,max = 12)
    @NotNull
    private int month;

    @Size(min = 2020)
    @NotNull
    private int year;

    @NotNull
    private float total;

    @ManyToOne(targetEntity = Staff.class)
    @JoinColumn(name = "id_staff",nullable = false)
    private Staff staff;
}
