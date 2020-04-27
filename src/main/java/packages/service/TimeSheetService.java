package packages.service;

import org.springframework.stereotype.Service;
import packages.DTO.CalendarDTO;
import packages.model.TimeSheet;

import java.util.Date;
import java.util.List;

@Service
public interface TimeSheetService {
    List<TimeSheet> findAll();
    TimeSheet findAllByStaffIdAndWorkDate(Long id, Date date);
    TimeSheet findByWorkDate(Date date);
    List<CalendarDTO> findWorkTimeOfStaff(Long id, int month, int year);
}
