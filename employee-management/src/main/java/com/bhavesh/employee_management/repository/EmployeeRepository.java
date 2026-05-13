package com.bhavesh.employee_management.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bhavesh.employee_management.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer>{

    List<Employee> findByDepartmentId(Integer departmentId); //gets employee by department
} 