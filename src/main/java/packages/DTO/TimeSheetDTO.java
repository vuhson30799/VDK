package packages.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class TimeSheetDTO {
    private float workTime;
    private float outsideTime;
    private String description;

}
