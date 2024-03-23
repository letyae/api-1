package com.yao.api1.service;


import com.yao.api1.repository.EmployeeRepository;
import lombok.Data;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yao.api1.model.Employee;

@Data
@Service
public class EmployeeService {
	
    @Autowired
	private EmployeeRepository employeeRepository; 
    
    public Optional<Employee> getEmployee(final Long id){
    	   return employeeRepository.findById(id);    	
           }
    
    public Iterable<Employee> getEmployees(){
    	System.out.println("**************************API:_get Employees*******************");
    	if(employeeRepository != null)
 	      return employeeRepository.findAll();
    	return null;
 	   }
    
    
    public void deleteEmployee(final Long id){
 	    employeeRepository.deleteById(id);
        } 
    
    
    public Employee saveEmployee(Employee employee){
    	System.out.println("**************************API:_Save_EmployeeService_*******************");

    	Employee savedEmployee = employeeRepository.save(employee); 
    	return savedEmployee;
        }
	
}