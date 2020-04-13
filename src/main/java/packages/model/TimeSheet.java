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
@Table(name = "time_sheet", schema = "public")
public class TimeSheet {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(name = "work_time")
    private float workTime;

    @NotNull
    @Column(name = "outside_time")
    private float outsideTime;

    @Column(name = "work_date")
    private Date workDate;

    private String description;

    @ManyToOne(targetEntity = Staff.class)
    @JoinColumn(name = "id_staff",nullable = false)
    private Staff staff;
}
