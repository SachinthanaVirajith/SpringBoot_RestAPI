package net.javaguides.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.javaguides.springboot.model.Employee;
 
public interface EmployeeRepositary extends JpaRepository<Employee, Long>{

}
