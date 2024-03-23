package com.yao.api1.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.yao.api1.model.Employee;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Long> {

}
