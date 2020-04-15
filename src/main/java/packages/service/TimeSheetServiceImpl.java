package packages.service;

import org.springframework.beans.factory.annotation.Autowired;
import packages.model.TimeSheet;
import packages.repository.TimeSheetRepository;

import java.util.List;

public class TimeSheetServiceImpl implements TimeSheetService {
    @Autowired
    private TimeSheetRepository timeSheetRepository;
    @Override
    public List<TimeSheet> findAll() {
        return timeSheetRepository.findAll();
    }
}
