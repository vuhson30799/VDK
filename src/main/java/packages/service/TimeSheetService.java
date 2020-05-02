package packages.service;

import org.springframework.stereotype.Service;
import packages.DTO.CalendarDTO;
import packages.model.TimeSheet;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public interface TimeSheetService {
    List<TimeSheet> findAll();
    TimeSheet findAllByStaffIdAndWorkDate(Long id, Date date);
    TimeSheet findByWorkDate(Date date);
    Map<Integer,CalendarDTO> findWorkTimeOfStaff(Long id, int month, int year);
    void save(TimeSheet timeSheet);
}
