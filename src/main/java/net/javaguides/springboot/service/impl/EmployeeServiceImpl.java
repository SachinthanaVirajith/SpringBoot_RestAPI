package net.javaguides.springboot.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import net.javaguides.springboot.exception.ResourceNotFoundException;
import net.javaguides.springboot.model.Employee;
import net.javaguides.springboot.repository.EmployeeRepositary;
import net.javaguides.springboot.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	private EmployeeRepositary employeeRepository;
	
	public EmployeeServiceImpl(EmployeeRepositary employeeRepository) {
		super();
		this.employeeRepository = employeeRepository;
	}

	@Override
	public Employee saveEmployee(Employee employee) {
		System.out.println("Saved");
		return employeeRepository.save(employee);
	}

	@Override
	public List<Employee> getAllEmployees() {
		return employeeRepository.findAll();
	}

	@Override
	public Employee getEmployeeById(long id) {
		Optional<Employee> employee = employeeRepository.findById(id);
		if(employee.isPresent()) {
			return employee.get();
		}else {
			throw new ResourceNotFoundException("Employee", "Id", id);
		}
	}

	@Override
	public Employee updateEmployee(Employee employee, long id) {
		
		// check whether employee with given id is exist or not 
		Employee existingEmployee = employeeRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("Employee", "Id", id));
		
		existingEmployee.setFirstName(employee.getFirstName());
		existingEmployee.setLastName(employee.getLastName());
		existingEmployee.setEmail(employee.getEmail());
		
		// save existing employee to db
		employeeRepository.save(existingEmployee);
		return existingEmployee;
	}

	@Override
	public void deleteEmployee(long id) {
		
		// check whether a employee exist in DB or not
		employeeRepository.findById(id).orElseThrow(() -> 
					new ResourceNotFoundException("Employee", "Id", id));		
		employeeRepository.deleteById(id);
	}

}
