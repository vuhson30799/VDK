package packages.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import packages.DTO.DayOffFormDTO;
import packages.DTO.StatusDayDTO;
import packages.DTO.TimeSheetDTO;
import packages.model.Staff;
import packages.model.TimeSheet;
import packages.model.User;
import packages.service.StaffService;
import packages.service.TimeSheetService;
import packages.service.UserService;

import java.security.Principal;
import java.util.Calendar;
import java.util.List;

@RestController
public class RestDataController {
    private final UserService userService;
    private final StaffService staffService;
    private final TimeSheetService timeSheetService;

    @Autowired
    public RestDataController(UserService userService, StaffService staffService, TimeSheetService timeSheetService) {
        this.userService = userService;
        this.staffService = staffService;
        this.timeSheetService = timeSheetService;
    }

    @GetMapping(value = "/header/get-username", produces = "application/json")
    public ResponseEntity<String> getUsername(@RequestParam(value = "username") String username) {
        User user = userService.findByUserName(username);
        if (user != null) {
            Staff staff = staffService.findByEmail(user.getEmail());
            if (staff != null) {
                return new ResponseEntity<>(staff.getLastName(), HttpStatus.OK);
            }
        }
        return new ResponseEntity<>("", HttpStatus.NOT_FOUND);
    }

    @PostMapping(value = "/content/get-status-day", produces = "application/json")
    public ResponseEntity<StatusDayDTO> getStatusDay(@RequestBody StatusDayDTO statusDayDTO){
        User user = userService.findByUserName(statusDayDTO.getUsername());
        Calendar calendar = Calendar.getInstance();
        calendar.set(statusDayDTO.getYear(),statusDayDTO.getMonth(),statusDayDTO.getDay());
        if (user != null) {
            Staff staff = staffService.findByEmail(user.getEmail());
            if (staff != null) {
                TimeSheet timeSheet = timeSheetService.findAllByStaffIdAndWorkDate(staff.getId(), calendar.getTime());
                if (timeSheet != null) {
                    TimeSheetDTO timeSheetDTO = new TimeSheetDTO(timeSheet.getWorkTime(),timeSheet.getOutsideTime(),timeSheet.getDescription());
                    statusDayDTO.setTimeSheet(timeSheetDTO);
                }

            }
        }
        return new ResponseEntity<>(statusDayDTO,HttpStatus.OK);
    }

    @PostMapping(value = "/content/apply-day-off", produces = "application/json")
    public ResponseEntity<Void> applyingDayOff(@RequestBody DayOffFormDTO dayOffFormDTO) {
        User user = userService.findByUserName(dayOffFormDTO.getUsername());
        if (user != null) {
            Staff staff = staffService.findByEmail(user.getEmail());
            TimeSheet timeSheet = new TimeSheet();
            timeSheet.setStaff(staff);
            timeSheet.setApplyingDayOff(true);
            timeSheet.setDescription(dayOffFormDTO.getDescription());
            timeSheet.setWorkDate(dayOffFormDTO.getDate());
            timeSheetService.save(timeSheet);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);

    }

}
