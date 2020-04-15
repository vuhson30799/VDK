package packages.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import packages.model.TimeSheet;

public interface TimeSheetRepository extends JpaRepository<TimeSheet,Long> {
}
