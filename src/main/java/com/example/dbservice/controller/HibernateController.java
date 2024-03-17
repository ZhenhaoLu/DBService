package com.example.dbservice.controller;

import com.example.dbservice.pojo.dto.EmployeeDTO;
import com.example.dbservice.service.IDBService;
import com.example.dbservice.service.impl.HibernateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hibernate")
public class HibernateController {

    private final IDBService hibernateService;
    @Autowired
    public HibernateController(HibernateService hibernateService){
        this.hibernateService = hibernateService;
    }

    @GetMapping("/employee/{id}")
    public ResponseEntity<EmployeeDTO> findEmployeeById(@PathVariable String id){
        return new ResponseEntity<>(hibernateService.findEmployeeById(id), HttpStatus.OK);
    }

    @PostMapping("/employee")
    public ResponseEntity<String> createNewEmployee(@RequestBody EmployeeDTO employeeDAO){
        Integer res = hibernateService.insertEmployee(employeeDAO);
        return new ResponseEntity<>(res.toString(), HttpStatus.OK);
    }

    @PutMapping("/employee")
    public ResponseEntity<EmployeeDTO> updateEmployee(@RequestBody EmployeeDTO employeeDAO){
        return new ResponseEntity<>(hibernateService.updateEmployee(employeeDAO), HttpStatus.OK);
    }

    @DeleteMapping("/employee/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable String id){
        hibernateService.deleteEmployee(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/department/{id}")
    public ResponseEntity<String> findDepartmentById(@PathVariable String id){
        return new ResponseEntity<>(hibernateService.findDepartmentById(id).getName(), HttpStatus.OK);
    }
}
