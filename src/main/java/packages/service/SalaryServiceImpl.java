package packages.service;

import org.springframework.beans.factory.annotation.Autowired;
import packages.model.Salary;
import packages.repository.SalaryRepository;

import java.util.List;

public class SalaryServiceImpl implements SalaryService {
    @Autowired
    private SalaryRepository salaryRepository;

    @Override
    public List<Salary> findAll() {
        return salaryRepository.findAll();
    }
}
