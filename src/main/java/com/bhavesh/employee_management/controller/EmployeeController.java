package com.bhavesh.employee_management.controller;

import com.bhavesh.employee_management.model.Employee;
import com.bhavesh.employee_management.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @PostMapping("/add")
    public ResponseEntity<Employee> add(@RequestBody Employee employee) {
        return new ResponseEntity<>(employeeRepository.save(employee), HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public List<Employee> getAll() {
        return employeeRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getById(@PathVariable Integer id) {
        Optional<Employee> emp = employeeRepository.findById(id);
        return emp.map(e -> new ResponseEntity<>(e, HttpStatus.OK))
                  .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/department/{deptId}")
    public List<Employee> getByDepartment(@PathVariable Integer deptId) {
        return employeeRepository.findByDepartmentId(deptId);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Employee> update(@PathVariable Integer id,
                                            @RequestBody Employee updated) {
        Optional<Employee> existing = employeeRepository.findById(id);
        if (existing.isPresent()) {
            Employee emp = existing.get();
            emp.setName(updated.getName());
            emp.setEmail(updated.getEmail());
            emp.setRole(updated.getRole());
            emp.setSalary(updated.getSalary());
            emp.setDepartment(updated.getDepartment());
            return new ResponseEntity<>(employeeRepository.save(emp), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) {
        if (employeeRepository.findById(id).isPresent()) {
            employeeRepository.deleteById(id);
            return new ResponseEntity<>("Employee deleted!", HttpStatus.OK);
        }
        return new ResponseEntity<>("Not found!", HttpStatus.NOT_FOUND);
    }
}