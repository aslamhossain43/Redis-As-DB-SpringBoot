package com.springboot.redis.db.controllers;

import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.redis.db.entities.Employee;
import com.springboot.redis.db.services.EmployeeService;

@RestController
@RequestMapping(value = "/api/redis/employee")
public class EmployeeController {

	private static final Logger LOG = LoggerFactory.getLogger(EmployeeController.class);

	@Autowired
	EmployeeService service;

	// Save a new employee.
	// Url - http://localhost:10091/api/redis/employee
	@RequestMapping
	public String save() {
		Employee employee = new Employee();
		employee.setId(UUID.randomUUID().toString());
		employee.setName("Aslam");
		employee.setAge(20);
		employee.setSalary(35000.500);
		LOG.info("Saving the new employee to the redis.");
		service.save(employee);
		return "Successfully added. Employee with id= " + employee.getId();
	}

	// Get all employees.
	// Url - http://localhost:10091/api/redis/employee/getall
	@GetMapping("/getall")
	public Map<String, Employee> findAll() {
		LOG.info("Fetching all employees from the redis.");
		final Map<String, Employee> employeeMap = service.findAll();
		// Todo - If developers like they can sort the map (optional).
		return employeeMap;
	}

	// Get employee by id.
	// Url - http://localhost:10091/api/redis/employee/get/<employee_id>
	@GetMapping("/get/{id}")
	public Employee findById(@PathVariable("id") final String id) {
		LOG.info("Fetching employee with id= " + id);
		return service.findById(id);
	}

	// Delete employee by id.
	// Url - http://localhost:10091/api/redis/employee/delete/<employee_id>
	@RequestMapping("/delete/{id}")
	public Map<String, Employee> delete(@PathVariable("id") final String id) {
		LOG.info("Deleting employee with id= " + id);
		// Deleting the employee.
		service.delete(id);
		// Returning the all employees (post the deleted one).
		return findAll();
	}
}