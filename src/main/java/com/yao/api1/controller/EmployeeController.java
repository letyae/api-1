package com.yao.api1.controller;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.yao.api1.model.Employee;
import com.yao.api1.service.EmployeeService;


@RestController
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;
	
	/**
	 * Read - Recupere tous les employees
	 * @return - Un objet iterable d'employees 
	 * 
	 */
/*	@GetMapping("/employees")
	Iterable<Employee> getEmployees(){
		return employeeService.getEmployees();
	} */
	
	@GetMapping("/employees")
    public ResponseEntity<Iterable<Employee>> getEmployees() {
        Iterable<Employee> employees = employeeService.getEmployees();
        return new ResponseEntity<>(employees, HttpStatus.OK);
    } 
	
	
	
	/**
	 * Read - Recupere un employees 
	 * @param id : id de l'employee
	 * @return un objet Employee 
	 */
	@GetMapping("/employee/{id}")
	public Employee getEmployee(@PathVariable("id") final Long id) {
		Optional<Employee> employee = employeeService.getEmployee(id); 
		if(employee.isPresent()) {
			return employee.get();
		} else {
			return null;
		}
	}
	
	
	
	
	 
	/**
	 * Update - Update an existing employee
	 * @param id - The id of the employee to update
	 * @param employee - The employee object updated
	 * @return  new Employee
	 */
	@PutMapping("/employee/{id}")
	public Employee updateEmployee(@PathVariable("id") final Long id, @RequestBody Employee employee) {

		Optional<Employee> e = employeeService.getEmployee(id);
		if(e.isPresent()) {
			Employee currentEmployee = e.get();
			
			
			String firstName = employee.getFirstName();
			if(firstName != null) {
				currentEmployee.setFirstName(firstName);
			}
			
			String lastName = employee.getLastName();
			if(lastName != null) {
				currentEmployee.setLastName(lastName);
			}
			String mail = employee.getMail();
			if(mail != null) {
				currentEmployee.setMail(mail);
			}
			String password = employee.getPassword();
			if(password != null) {
				currentEmployee.setPassword(password);
			}
			employeeService.saveEmployee(currentEmployee);
			return currentEmployee;
		} else {
			return null;
		}
	}

	
	
	
	
	/**
	 * Creer - creer un nouveau  employee
	 * @param employee -  employee a creer
	 * @return   Employee creer
	 */
	@PostMapping("/employee")
	public Employee saveEmployee(@RequestBody Employee employee) {
		
		System.out.println("API: ********** _saveEmployeeController_ ********");
		Employee saveEmployee = employeeService.saveEmployee(employee);
		return saveEmployee;		
		} 
		
	/**
	 * Delete - Delete an employee
	  * @param id - The id of the employee to delete
	 */
	@DeleteMapping("/employee/{id}")
	public void deleteEmployee(@PathVariable("id") final Long id) {
		employeeService.deleteEmployee(id);
	}
		
}