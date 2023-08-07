package EmployeeManagementSystem.ems.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import EmployeeManagementSystem.ems.exception.ResourceNotFoundException;
import EmployeeManagementSystem.ems.model.Employee;
import EmployeeManagementSystem.ems.repositoty.EmployeeRepository;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/")

public class EmployeeController {

	@Autowired
	private EmployeeRepository employeeRepository;
	
	// Get All Employees

	@GetMapping("/employees")
	public List<Employee> getAllEmployee(){
		return employeeRepository.findAll();
	}		
	
	// Create Employees rest api

	@PostMapping("/employees")
	public Employee createEmployee(@RequestBody Employee employee) {
		return employeeRepository.save(employee);
	}
	
	// Get Employee by Id rest api

	@GetMapping("/employees/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
		Employee employee = employeeRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee Not Exist with Id :" + id));
		return ResponseEntity.ok(employee);
	}
	
	
	// Delete Employee rest api

	@DeleteMapping("/employee/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteCustomer(@PathVariable Long id){
		Employee employee = employeeRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee Not Exist With Id :" + id));
		
		employeeRepository.delete(employee);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	} 
}
