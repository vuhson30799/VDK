package packages.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "timeSheet", schema = "public")
public class TimeSheet {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private float workTime;

    @NotNull
    private float outsideTime;

    private Date workDate;

    private String description;

    @ManyToOne(targetEntity = Staff.class)
    @JoinColumn(name = "id_staff",nullable = false)
    private Staff staff;
}
