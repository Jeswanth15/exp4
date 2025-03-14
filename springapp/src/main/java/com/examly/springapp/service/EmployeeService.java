package com.examly.springapp.service;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examly.springapp.model.Employee;
import com.examly.springapp.repository.EmployeeRepo;
@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepo employeeRepo;

    public Employee addEmployee(Employee employee) {
        return employeeRepo.save(employee);
    }

    public List<Employee> getAllEmployees() {
        return employeeRepo.findAll();
    }

    public Employee getEmployeeById(int id) {
        return employeeRepo.findById(id).orElse(null);
    }

    public List<Employee> getEmployeesByHireDate(Date hireDate) {
        return employeeRepo.findByHireDate(hireDate);
    }

    public List<String> getFirstThreeCharacterNames() {
        return employeeRepo.findAll().stream()
                .map(emp -> emp.getName().substring(0, Math.min(3, emp.getName().length())))
                        .collect(Collectors.toList());
            }
    }