package packages.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import packages.constant.BusinessConstant;

import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CalendarDTO {
    private int day;
    private LocalTime workTime;
    private boolean applyingDayOff;
    private boolean passed;
    /**
     * status = 1 mean this staff works overtime
     * status = 2 mean this staff works on time
     * status = -1 mean this staff works less
     * status = null mean this day is not coming
     * status = 3 mean this is a day off
     * status = 4 mean this will be a day off
     */
    private int status;

    public void setStatus() {
        if (isApplyingDayOff() && passed) {
            this.status = 3;
            return;
        }
        if (isApplyingDayOff() && !passed) {
            this.status = 4;
            return;
        }
        if (this.workTime.isBefore(BusinessConstant.ON_TIME_START)) {
            this.status = -1;
            return;
        }
        if (this.workTime.isAfter(BusinessConstant.ON_TIME_END)) {
            this.status = 1;
            return;
        }
        this.status = 2;

    }

    public CalendarDTO(int day) {
        this.day = day;
    }
}
