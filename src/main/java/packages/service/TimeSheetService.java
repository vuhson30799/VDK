package packages.service;

import org.springframework.stereotype.Service;
import packages.model.TimeSheet;

import java.util.List;

@Service
public interface TimeSheetService {
    List<TimeSheet> findAll();
}
