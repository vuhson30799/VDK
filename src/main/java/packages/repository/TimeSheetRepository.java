package packages.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import packages.model.TimeSheet;

import java.util.Date;
import java.util.List;

public interface TimeSheetRepository extends JpaRepository<TimeSheet,Long> {
    TimeSheet findAllByStaff_IdAndWorkDate(Long id, Date date);
    TimeSheet findByWorkDate(Date date);
}
