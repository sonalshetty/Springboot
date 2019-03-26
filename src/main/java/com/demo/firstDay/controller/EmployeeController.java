package com.demo.firstDay.controller;

import com.demo.firstDay.model.Employee;
import com.demo.firstDay.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class EmployeeController {

    @Autowired
    EmployeeRepository employeeRepository;

    @PostMapping(value = "/addEmp")
    public Employee createEmployee(@RequestBody Employee empdata){
        return employeeRepository.save(empdata);
    }

    @GetMapping(value = "/getAllEmployees")
    public List<Employee> getEmployees(){
        return employeeRepository.findAll();
    }

    @GetMapping(value = "/getOneEmployee/{id}")
    public Optional<Employee> getOneEmployee(@PathVariable(value = "id") Long empid){
        return employeeRepository.findById(empid);
    }

    @DeleteMapping(value = "/deleteEmployee/{id}")
    public  String deleteEmployee(@PathVariable(value = "id") Long empid){
       Employee emp = employeeRepository.getOne(empid);
       employeeRepository.delete(emp);
       return "Data Deleted Successfully";
    }

    @PutMapping(value = "/updateEmployee/{id}")
    public Employee updateEmployee(@PathVariable(value = "id") Long empid, @RequestBody Employee empdata){
        Employee emp = employeeRepository.findById(empid).get();
        emp.setEmpName(empdata.getEmpName());
        emp.setDesignation(empdata.getDesignation());

        employeeRepository.save(emp);

        return emp;
    }
}
