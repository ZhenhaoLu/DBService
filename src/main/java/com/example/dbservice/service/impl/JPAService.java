package com.example.dbservice.service.impl;

import com.example.dbservice.pojo.dto.EmployeeDTO;
import com.example.dbservice.pojo.entity.Departments;
import com.example.dbservice.repository.EmployeeJPARepository;
import com.example.dbservice.service.IDBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JPAService implements IDBService{

    private final EmployeeJPARepository employeeJPARepository;

    @Autowired
    public JPAService(EmployeeJPARepository employeeJPARepository){
        this.employeeJPARepository = employeeJPARepository;
    }

    @Override
    public EmployeeDTO findEmployeeById(String id){
        return new EmployeeDTO(employeeJPARepository.findById(id).orElseThrow());
    }

    @Override
    public Integer insertEmployee(EmployeeDTO employeeDAO){
        return employeeJPARepository.save(IDBService.setEmployees(employeeDAO)).getId();
    }

    @Override
    public EmployeeDTO updateEmployee(EmployeeDTO employeeDAO){
        Departments foreignKey = new Departments();
        foreignKey.setId(employeeDAO.getDept_id());
        employeeJPARepository.updateById(employeeDAO.getName(), employeeDAO.getAge(), employeeDAO.getSalary(),
                foreignKey, employeeDAO.getId());
        return new EmployeeDTO(employeeJPARepository.findById(employeeDAO.getId().toString()).orElseThrow());
    }

    @Override
    public void deleteEmployee(String id){
        employeeJPARepository.deleteById(id);
    }

    @Override
    public Departments findDepartmentById(String id){ return null;}
}
