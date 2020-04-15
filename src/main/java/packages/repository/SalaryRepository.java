package packages.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import packages.model.Salary;

public interface SalaryRepository extends JpaRepository<Salary,Long> {
}
