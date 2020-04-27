package packages.service;

import org.springframework.beans.factory.annotation.Autowired;
import packages.DTO.CalendarDTO;
import packages.model.TimeSheet;
import packages.repository.TimeSheetRepository;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class TimeSheetServiceImpl implements TimeSheetService {
    @Autowired
    private TimeSheetRepository timeSheetRepository;
    @Override
    public List<TimeSheet> findAll() {
        return timeSheetRepository.findAll();
    }

    @Override
    public TimeSheet findAllByStaffIdAndWorkDate(Long id, Date date) {
        return timeSheetRepository.findAllByStaff_IdAndWorkDate(id,date);
    }

    @Override
    public TimeSheet findByWorkDate(Date date) {
        return timeSheetRepository.findByWorkDate(date);
    }

    @Override
    public List<CalendarDTO> findWorkTimeOfStaff(Long id, int month, int year) {
        List<TimeSheet> timeSheets =  timeSheetRepository.findWorkTimeOfStaffByDay(id, month + 1, year);
        ArrayList<CalendarDTO> result = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        for (TimeSheet x :
                timeSheets) {
            calendar.setTime(x.getWorkDate());
            CalendarDTO calendarDTO = new CalendarDTO();
            LocalTime workTime = x.getWorkTime();
            LocalTime outsideTime = x.getOutsideTime();
            workTime = workTime.minus(outsideTime.getHour(), ChronoUnit.HOURS);
            workTime = workTime.minus(outsideTime.getMinute(), ChronoUnit.MINUTES);
            workTime = workTime.minus(outsideTime.getSecond(), ChronoUnit.SECONDS);
            calendarDTO.setDay(calendar.get(Calendar.DAY_OF_MONTH));
            calendarDTO.setWorkTime(workTime);
            calendarDTO.setStatus();
            result.add(calendarDTO);
        }
        return result;
    }
}
