package com.bhavesh.employee_management.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.bhavesh.employee_management.model.Department;

public interface DepartmentRepository extends JpaRepository<Department, Integer>{
} 