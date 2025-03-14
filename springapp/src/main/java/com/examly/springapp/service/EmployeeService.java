package com.examly.springapp.service;

import com.examly.springapp.model.Employee;
import com.examly.springapp.repository.EmployeeRepo;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeRepo employeeRepo;

    public EmployeeService(EmployeeRepo employeeRepo) {
        this.employeeRepo = employeeRepo;
    }

    public Employee addEmployee(Employee employee) {
        return employeeRepo.save(employee);
    }

    public List<Employee> getEmployeesContaining(String searchTerm) {
        return employeeRepo.findByNameContainingIgnoreCaseOrDesignationContainingIgnoreCase(searchTerm, searchTerm);
    }


        public List<Employee> getEmployeesIsContaining(String searchTerm) {
            return employeeRepo.findByNameIsContainingIgnoreCase(searchTerm);
        }

        public List<Employee> findByNameStartsWith(String prefix) {
            return employeeRepo.findByNameStartsWithIgnoreCase(prefix);
            }

            public List<Employee> findByNameEndsWith(String suffix) {
                return employeeRepo.findByNameEndsWithIgnoreCase(suffix);
                }

                public List<Employee> findByDesignationContaining(String designation) {
                    return employeeRepo.findByDesignationContainingIgnoreCase(designation);
                }
            }