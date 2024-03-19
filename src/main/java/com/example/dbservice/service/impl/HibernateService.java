package com.example.dbservice.service.impl;

import com.example.dbservice.pojo.dto.EmployeeDTO;
import com.example.dbservice.pojo.entity.Departments;
import com.example.dbservice.pojo.entity.Employees;
import com.example.dbservice.repository.HibernateDepartmentRepository;
import com.example.dbservice.repository.HibernateEmployeeRepository;
import com.example.dbservice.service.IDBService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HibernateService implements IDBService {

    private final HibernateEmployeeRepository emRepository;
    private final HibernateDepartmentRepository departmentRepository;
    @Autowired
    public HibernateService(HibernateEmployeeRepository emRepository, HibernateDepartmentRepository departmentRepository){
        this.emRepository = emRepository;
        this.departmentRepository = departmentRepository;
    }

    public EmployeeDTO findEmployeeById(String id){
        Employees res = this.emRepository.findByID(id);
        return new EmployeeDTO(res);
    }

    @Transactional
    public Integer insertEmployee(EmployeeDTO employeeDAO){
        Employees e = IDBService.setEmployees(employeeDAO);
        return this.emRepository.insertEmployee(e);
    }

    @Transactional
    public EmployeeDTO updateEmployee(EmployeeDTO employeeDAO){
        Employees e = IDBService.setEmployees(employeeDAO);
        e.setId(employeeDAO.getId());
        e = this.emRepository.updateEmployee(e);
        return new EmployeeDTO(e);
    }

    @Transactional
    public void deleteEmployee(String id){
        this.emRepository.deleteEmployee(id);
    }


    // For test
    public Departments findDepartmentById(String id){
        return this.departmentRepository.findByID(id);
    }
}
