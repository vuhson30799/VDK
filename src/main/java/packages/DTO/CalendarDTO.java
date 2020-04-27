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
    /**
     * status = 1 mean this staff works overtime
     * status = 2 mean this staff works on time
     * status = -1 mean this staff works less
     * status = 0 mean this day is not coming
     */
    private int status;

    public void setStatus() {
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
