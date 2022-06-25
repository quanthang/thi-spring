package com.example.quanthispringboot.api;

import com.example.quanthispringboot.api.EmployeeApi;
import com.example.quanthispringboot.entity.Employee;
import com.example.quanthispringboot.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping(path = "api/v1/employees")
public class EmployeeApi {
    @Autowired
    EmployeeService employeeService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Employee>> findAll() {
        return ResponseEntity.ok(employeeService.findAll());
    }

    @RequestMapping(method = RequestMethod.GET, path = "{id}")
    public ResponseEntity<?> findById(@PathVariable int id) {
        Optional<Employee> employee = employeeService.findById(id);
        if (!employee.isPresent()) {
            ResponseEntity.badRequest().build();// khoong co du lieu tra ve
        }
        return ResponseEntity.ok(employee.get());//cos du lieu tra ve
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Employee> save(@RequestBody Employee employee) {
        return ResponseEntity.ok(employeeService.save(employee));
    }

    @RequestMapping(method = RequestMethod.PUT, path = "{id}")
    public ResponseEntity<Employee> update(@PathVariable int id, @RequestBody Employee updateRoad) {
        Optional<Employee> employee = employeeService.findById(id);
        if (!employee.isPresent()) {
            ResponseEntity.badRequest().build();
        }
        Employee exitsEmployee = employee.get();
        exitsEmployee.setName(updateRoad.getName());
        exitsEmployee.setWage(updateRoad.getWage());
        return ResponseEntity.ok(employeeService.save(exitsEmployee));
    }
}
