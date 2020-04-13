package packages.service;

import org.springframework.beans.factory.annotation.Autowired;
import packages.model.TimeSheet;
import packages.repository.TimeSheetRepository;

import java.util.Date;
import java.util.List;

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
}
