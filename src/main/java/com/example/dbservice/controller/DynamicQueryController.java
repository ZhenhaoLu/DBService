package com.example.dbservice.controller;

import com.example.dbservice.pojo.dto.DynamicQueryDTO;
import com.example.dbservice.service.impl.DynamicQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/criteria")
public class DynamicQueryController {

    private final DynamicQueryService dynamicQueryService;

    @Autowired
    public DynamicQueryController(DynamicQueryService dynamicQueryService){
        this.dynamicQueryService = dynamicQueryService;
    }

    @GetMapping("/employee/{age}/{name}")
    public ResponseEntity<List<DynamicQueryDTO>> findEmployeeById(@PathVariable Integer age, @PathVariable String name){
        return new ResponseEntity<>(this.dynamicQueryService.specialSearch(age, name), HttpStatus.OK);
    }
}
