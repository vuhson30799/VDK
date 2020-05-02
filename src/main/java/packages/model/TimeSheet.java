package packages.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@Table(name = "time_sheet", schema = "public")
public class TimeSheet {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "work_time",columnDefinition = "time default '00:00:00'")
    private LocalTime workTime;

    @Column(name = "outside_time")
    private LocalTime outsideTime;

    @Column(name = "work_date",columnDefinition = "time default '00:00:00'")
    private Date workDate;

    private String description;

    @Column(name = "applying_day_off")
    private boolean applyingDayOff;

    @ManyToOne(targetEntity = Staff.class)
    @JoinColumn(name = "id_staff",nullable = false)
    private Staff staff;

    public TimeSheet() {
        this.workTime = LocalTime.of(0,0,0,0);
        this.outsideTime = LocalTime.of(0,0,0,0);
    }
}
