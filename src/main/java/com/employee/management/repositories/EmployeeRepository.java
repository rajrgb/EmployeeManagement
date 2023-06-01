package com.employee.management.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.employee.management.models.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

}
