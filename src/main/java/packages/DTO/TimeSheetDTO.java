package packages.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class TimeSheetDTO {
    private LocalTime workTime;
    private LocalTime outsideTime;
    private String description;

}
