package packages.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import packages.model.Department;

public interface DepartmentRepository extends JpaRepository<Department,Long> {
}
