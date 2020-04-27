package packages.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StatusDayDTO {
    private String username;
    private int month;
    private int year;
    private int day;
    private TimeSheetDTO timeSheet;
}
