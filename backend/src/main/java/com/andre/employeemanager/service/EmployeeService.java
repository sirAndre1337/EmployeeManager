package com.andre.employeemanager.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.andre.employeemanager.exception.UserNotFoundException;
import com.andre.employeemanager.model.Employee;
import com.andre.employeemanager.repository.EmployeeRepository;

@Service
public class EmployeeService {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	public Employee findEmployeeById(Long id) {
		Optional<Employee> employee = employeeRepository.findById(id);
		return employee.orElseThrow(()-> new UserNotFoundException("User by id " + id + " was not found"));
	}
	
	public Employee addEmployee(Employee employee) {
		employee.setId(null);
		employee.setEmployeeCode(UUID.randomUUID().toString());
		return employeeRepository.save(employee);
	}
	
	public List<Employee> findAll() {
		return employeeRepository.findAll();
	}
	
	public Employee updateEmployee(Employee employee) {
		findEmployeeById(employee.getId());
		return employeeRepository.save(employee);
	}
	
	public void deleteEmployee(Long id) {
		findEmployeeById(id);
		employeeRepository.deleteById(id);
	}

}
