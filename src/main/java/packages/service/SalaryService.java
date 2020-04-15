package packages.service;

import org.springframework.stereotype.Service;
import packages.model.Salary;

import java.util.List;

@Service
public interface SalaryService {
    List<Salary> findAll();
}
