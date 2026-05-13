package com.bhavesh.employee_management.controller;

import com.bhavesh.employee_management.model.Department;
import com.bhavesh.employee_management.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/departments")
public class DepartmentController {

    @Autowired
    private DepartmentRepository departmentRepository;

    @PostMapping("/add")
    public ResponseEntity<Department> add(@RequestBody Department dept) {
        return new ResponseEntity<>(departmentRepository.save(dept), HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public List<Department> getAll() {
        return departmentRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Department> getById(@PathVariable Integer id) {
        Optional<Department> dept = departmentRepository.findById(id);
        return dept.map(d -> new ResponseEntity<>(d, HttpStatus.OK))
                   .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Department> update(@PathVariable Integer id,
                                              @RequestBody Department updated) {
        Optional<Department> existing = departmentRepository.findById(id);
        if (existing.isPresent()) {
            Department dept = existing.get();
            dept.setName(updated.getName());
            dept.setLocation(updated.getLocation());
            return new ResponseEntity<>(departmentRepository.save(dept), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) {
        if (departmentRepository.findById(id).isPresent()) {
            departmentRepository.deleteById(id);
            return new ResponseEntity<>("Department deleted!", HttpStatus.OK);
        }
        return new ResponseEntity<>("Not found!", HttpStatus.NOT_FOUND);
    }
}