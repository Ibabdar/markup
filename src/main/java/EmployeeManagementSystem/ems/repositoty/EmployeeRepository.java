package EmployeeManagementSystem.ems.repositoty;

import org.springframework.data.jpa.repository.JpaRepository;

import EmployeeManagementSystem.ems.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long  > {
    
}
