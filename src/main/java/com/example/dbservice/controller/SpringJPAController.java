package com.example.dbservice.controller;

import com.example.dbservice.pojo.dao.EmployeeDAO;
import com.example.dbservice.service.impl.JPAService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/jpa")
public class SpringJPAController {

    private final JPAService jpaService;

    @Autowired
    public SpringJPAController(JPAService jpaService){
        this.jpaService = jpaService;
    }

    @GetMapping("/employee/{id}")
    public ResponseEntity<EmployeeDAO> findEmployeeById(@PathVariable String id){
        return new ResponseEntity<>(this.jpaService.findEmployeeById(id), HttpStatus.OK);
    }

    @PostMapping("/employee")
    public ResponseEntity<String> createNewEmployee(@RequestBody EmployeeDAO e){
        Integer res = this.jpaService.insertEmployee(e);
        return new ResponseEntity<>(res.toString(), HttpStatus.OK);
    }

    @PutMapping("/employee")
    public ResponseEntity<EmployeeDAO> updateEmployee(@RequestBody EmployeeDAO e){
        return new ResponseEntity<>(this.jpaService.updateEmployee(e), HttpStatus.OK);
    }

    @DeleteMapping("/employee/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable String id){
        this.jpaService.deleteEmployee(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
