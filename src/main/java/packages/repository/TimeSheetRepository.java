package packages.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import packages.model.TimeSheet;

import java.util.Date;
import java.util.List;

public interface TimeSheetRepository extends JpaRepository<TimeSheet,Long> {
    TimeSheet findAllByStaff_IdAndWorkDate(Long id, Date date);
    TimeSheet findByWorkDate(Date date);
    @Query(value = "SELECT * " +
            "FROM public.time_sheet where id_staff = ?1 " +
            "and extract(month from work_date) = ?2 " +
            "and extract (year from work_date) = ?3 order by extract (day from work_date) ASC", nativeQuery = true)
    List<TimeSheet> findWorkTimeOfStaffByDay(Long id, int month, int year);
}
