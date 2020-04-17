package packages.service;

import org.springframework.stereotype.Service;
import packages.model.Department;

import java.util.List;
@Service
public interface DepartmentService {
    List<Department> findAll();
    Department findById(Long id);
    Department findByName(String name);
    void save(Department department);
    void delete(Department department);
}
