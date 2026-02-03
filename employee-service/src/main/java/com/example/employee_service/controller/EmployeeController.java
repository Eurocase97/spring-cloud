package com.example.employee_service.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.example.employee_service.model.Employee;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.employee_service.repository.EmployeeRepository;

import java.net.URI;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/employees")
public class EmployeeController {

    private final EmployeeRepository employeeRepository;

    @GetMapping
    public ResponseEntity<List<Employee>> findAll() {
        return ResponseEntity.ok(employeeRepository.findAll());
    }

    @GetMapping("/department/{id}")
    public ResponseEntity<List<Employee>> findAllByDepartmentId(@PathVariable Long id) {
        return ResponseEntity.ok(employeeRepository.findByDepartmentId(id));
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody Employee employee) {
        employeeRepository.create(employee);
        return ResponseEntity.created(URI.create("/api/v1/employees/".concat(employee.getId().toString()))).build();
    }


}