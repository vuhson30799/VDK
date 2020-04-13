package packages.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

@Controller
public class HomeController {
    @GetMapping("/")
    public String home(){
        return "home";
    }

    @GetMapping("/home")
    public String test(Model model) {
        Locale locale = new Locale("vi");
        Calendar calendar = Calendar.getInstance(locale);
        model.addAttribute("year", calendar.get(Calendar.YEAR));
        model.addAttribute("month", convertNameMonth(calendar.get(Calendar.MONTH)));
        model.addAttribute("currentMonthCalendar", createCurrentCalendar(calendar));
        return "fragment/content :: status-content";
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

    private Integer[][] createCurrentCalendar(Calendar calendar) {
        int currentDayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        int currentDayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        int currentMonth = calendar.get(Calendar.MONTH);
        int currentYear = calendar.get(Calendar.WEEK_OF_YEAR);

        int dayOfLastMonth = findNumberOfLastMonth(currentMonth, currentYear);
        Integer[][] currentMonthCalendar = new Integer[6][8];
        int i = calendar.get(Calendar.WEEK_OF_MONTH), j = currentDayOfWeek;
        while (i >= 1) {
            while (j >= 1) {
                if (currentDayOfMonth < 1) {
                    currentMonthCalendar[i][j] = dayOfLastMonth--;
                }else {
                    currentMonthCalendar[i][j] = currentDayOfMonth--;
                }
                j--;
            }
            j = 7;
            i--;
        }

        currentDayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        i = calendar.get(Calendar.WEEK_OF_MONTH);
        j = currentDayOfWeek;
        int maximumDayOfMonth = calendar.getMaximum(Calendar.DAY_OF_MONTH);
        while (i <= 5) {
            while (j <= 7) {
                if (currentDayOfMonth > maximumDayOfMonth) {
                    currentDayOfMonth = 1;
                    currentMonthCalendar[i][j] = currentDayOfMonth++;
                } else {
                    currentMonthCalendar[i][j] = currentDayOfMonth++;
                }
                j++;
            }
            j = 1;
            i++;
        }
        return currentMonthCalendar;
    }
    private String convertNameMonth(int month) {
        String nameMonth;
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
