package com.examly.springapp.controller;

import com.examly.springapp.model.Employee;
import com.examly.springapp.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
            this.employeeService = employeeService;
            }

            @PostMapping
            public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee) {
                    try {
                                Employee savedEmployee = employeeService.addEmployee(employee);
                                        return ResponseEntity.status(HttpStatus.CREATED).body(savedEmployee);
                    } catch (Exception e) {
                                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
                    }
                }

                @GetMapping("/containing/{searchTerm}")
                public ResponseEntity<List<Employee>> getEmployeesContaining(@PathVariable String searchTerm) {
                    return ResponseEntity.ok(employeeService.getEmployeesContaining(searchTerm));
                }

                
                        

                        @GetMapping("/isContaining/{searchTerm}")
                        public ResponseEntity<List<Employee>> getEmployeesIsContaining(@PathVariable String searchTerm) {
                                return ResponseEntity.ok(employeeService.getEmployeesIsContaining(searchTerm));  
                                }

                                @GetMapping("/startsWith/{name}")
                                public ResponseEntity<List<Employee>> getEmployeesStartsWith(@PathVariable String name) {
                                        return ResponseEntity.ok(employeeService.findByNameStartsWith(name));
                                }

                                @GetMapping("/endsWith/{name}")
                                public ResponseEntity<List<Employee>> getEmployeesEndsWith(@PathVariable String name) {
                                        return ResponseEntity.ok(employeeService.findByNameEndsWith(name));
                                }

                                @GetMapping("/contains/{designation}")
                                public ResponseEntity<List<Employee>> getEmployeesByDesignation(@PathVariable String designation) {
                                        return ResponseEntity.ok(employeeService.findByDesignationContaining(designation));
                                }
                            }