package packages.service;

import org.springframework.stereotype.Service;
import packages.model.Department;
import packages.model.Staff;

import java.util.List;

@Service
public interface StaffService {
    List<Staff> findAll();
    Staff findById(Long id);
    void save(Staff staff);
    void remove(Staff staff);
    List<Staff> findAllByDepartment(Department department);
}
