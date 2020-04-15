package packages.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import packages.model.Department;
import packages.model.Staff;

import java.util.List;

public interface StaffRepository extends JpaRepository<Staff,Long> {
    List<Staff> findAllByDepartment(Department department);
}
