    package com.examly.springapp.repository;

    import java.util.List;

    import org.springframework.data.jpa.repository.JpaRepository;
    import org.springframework.stereotype.Repository;

    import com.examly.springapp.model.Employee;

    @Repository
    public interface EmployeeRepo extends JpaRepository<Employee, Integer> {


    List<Employee> findByNameContainingIgnoreCaseOrDesignationContainingIgnoreCase(String name, String designation);

    List<Employee> findByNameIsContainingIgnoreCase(String searchTerm);
    List<Employee> findByNameStartsWithIgnoreCase(String prefix);
    List<Employee> findByNameEndsWithIgnoreCase(String suffix);
    List<Employee> findByDesignationContainingIgnoreCase(String designation);
    }