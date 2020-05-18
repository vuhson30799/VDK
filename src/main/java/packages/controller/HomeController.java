package packages.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import packages.DTO.CalendarDTO;
import packages.constant.BusinessConstant;
import packages.model.Staff;
import packages.model.User;
import packages.service.StaffService;
import packages.service.TimeSheetService;
import packages.service.UserService;

import java.security.Principal;
import java.time.LocalTime;
import java.util.*;

@Controller
public class HomeController {
    private TimeSheetService timeSheetService;
    private UserService userService;
    private StaffService staffService;

    @Autowired
    public HomeController (TimeSheetService timeSheetService, UserService userService, StaffService staffService) {
        this.timeSheetService = timeSheetService;
        this.userService = userService;
        this.staffService = staffService;
    }
    @GetMapping("/")
    public String home(){
        return "home";
    }

    @GetMapping("/home")
    public String test(Model model,
                       Principal principal,
                       @RequestParam(value = "month")Optional<Integer> month,
                       @RequestParam(value = "year")Optional<Integer> year) {
        int monthCalendar = month.orElse(-1);
        int yearCalendar = year.orElse(-1);
        User user = userService.findByUserName(principal.getName());
        if (user != null) {
            Staff staff = staffService.findByEmail(user.getEmail());
            if (staff != null) {
                Locale locale = new Locale("vi");
                Calendar calendar = Calendar.getInstance(locale);
                if (monthCalendar != -1 && yearCalendar != -1) {
                    calendar.set(yearCalendar, monthCalendar, 1);
                }
                //Month in Calendar is one less than real month
                model.addAttribute("isLastMonthExist",isLastMonthExist(calendar.get(Calendar.MONTH),staff.getId()));
                model.addAttribute("isNextMonthExist", isNextMonthExist(calendar.get(Calendar.MONTH) + 2,staff.getId()));
                model.addAttribute("year", calendar.get(Calendar.YEAR));
                model.addAttribute("month", convertNameMonth(calendar.get(Calendar.MONTH)));
                model.addAttribute("currentMonthCalendar", createCurrentCalendar(calendar,staff.getId()));
            }
        }

        return "fragment/content :: status-content";
    }

    private boolean isNextMonthExist(Integer month, Long id) {
        return timeSheetService.isTimeSheetOfMonthExist(month, id);
    }

    private boolean isLastMonthExist(Integer month, Long id) {
        return timeSheetService.isTimeSheetOfMonthExist(month, id);
    }

    private int findNumberOfLastMonth(int currentMonth, int currentYear){
        //Month in Calendar is one less than real month
        switch (currentMonth) {
            case 4:
            case 6:
            case 9:
            case 11:
                return 30;
            case 2:
                if (currentYear % 4 == 0 && currentYear % 100 != 0 || currentYear % 400 == 0) {
                    return 29;
                } else {
                    return 28;
                }
            default:
                return 31;
        }
    }

    private CalendarDTO[][] createCurrentCalendar(Calendar calendar, Long staffID) {
        int currentDayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        int currentDayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        int currentMonth = calendar.get(Calendar.MONTH);
        int currentYear = calendar.get(Calendar.YEAR);

        int dayOfLastMonth = findNumberOfLastMonth(currentMonth, currentYear);
        CalendarDTO[][] currentMonthCalendar = new CalendarDTO[6][8];
        List<CalendarDTO> statusCalendar = timeSheetService.findWorkTimeOfStaff(staffID,currentMonth,currentYear);

        int startDefiniteDay = statusCalendar.get(0).getDay();
        int endDefiniteDay = startDefiniteDay + statusCalendar.size() - 1;
        int i = calendar.get(Calendar.WEEK_OF_MONTH);
        int j = currentDayOfWeek;
        while (i >= 1) {
            while (j >= 1) {
                if (currentDayOfMonth < 1) {
                    currentMonthCalendar[i][j] = new CalendarDTO(dayOfLastMonth--);
                }else {
                    currentMonthCalendar[i][j] = currentDayOfMonth <= endDefiniteDay && currentDayOfMonth >= startDefiniteDay ? statusCalendar.get(currentDayOfMonth - startDefiniteDay) : new CalendarDTO(currentDayOfMonth);
                    currentDayOfMonth--;
                }
                j--;
            }
            j = 7;
            i--;
        }
        currentDayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        i = calendar.get(Calendar.WEEK_OF_MONTH);
        j = currentDayOfWeek;
        int maximumDayOfMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        boolean activated = false;
        while (i <= 5) {
            while (j <= 7) {
                if (currentDayOfMonth > maximumDayOfMonth || activated) {
                    currentDayOfMonth = 1;
                    currentMonthCalendar[i][j] = new CalendarDTO(currentDayOfMonth);
                    activated = true;
                } else {
                    currentMonthCalendar[i][j] = currentDayOfMonth <= endDefiniteDay && currentDayOfMonth >= startDefiniteDay ? statusCalendar.get(currentDayOfMonth - startDefiniteDay) : new CalendarDTO(currentDayOfMonth);

                }
                currentDayOfMonth++;
                j++;
            }
            j = 1;
            i++;
        }
        return currentMonthCalendar;
    }
    private String convertNameMonth(int month) {
        switch (month) {
            case 0:
                return "January";
            case 1:
                return "February";
            case 2:
                return "March";
            case 3:
                return "April";
            case 4:
                return "May";
            case 5:
                return "June";
            case 6:
                return "July";
            case 7:
                return "August";
            case 8:
                return "September";
            case 9:
                return "October";
            case 10:
                return "November";
            default:
                return "December";
        }
    }
}
