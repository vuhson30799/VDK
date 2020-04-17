package packages.service;

import org.springframework.beans.factory.annotation.Autowired;
import packages.model.Department;
import packages.model.Staff;
import packages.repository.StaffRepository;

import java.util.List;

public class StaffServiceImpl implements StaffService {
    @Autowired
    private StaffRepository staffRepository;
    @Override
    public List<Staff> findAll() {
        return staffRepository.findAll();
    }

    @Override
    public Staff findById(Long id) {
        return staffRepository.findById(id).get();
    }

    @Override
    public void save(Staff staff) {
        staffRepository.save(staff);
    }

    @Override
    public void remove(Staff staff) {
        staffRepository.delete(staff);
    }

    @Override
    public List<Staff> findAllByDepartment(Department department) {
        return staffRepository.findAllByDepartment(department);
    }

    @Override
    public Staff findByEmail(String email) {
        return staffRepository.findByEmail(email);
    }


}
