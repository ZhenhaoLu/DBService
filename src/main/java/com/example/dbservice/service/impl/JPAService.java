package com.example.dbservice.service.impl;

import com.example.dbservice.pojo.dao.EmployeeDAO;
import com.example.dbservice.pojo.entity.Departments;
import com.example.dbservice.pojo.entity.Employees;
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

    public EmployeeDAO findEmployeeById(String id){
        return new EmployeeDAO(employeeJPARepository.findById(id).orElseThrow());
    }

    public Integer insertEmployee(EmployeeDAO employeeDAO){
        return employeeJPARepository.save(IDBService.setEmployees(employeeDAO)).getId();
    }

    public EmployeeDAO updateEmployee(EmployeeDAO employeeDAO){
        Departments foreignKey = new Departments();
        foreignKey.setId(employeeDAO.getDept_id());
        employeeJPARepository.updateById(employeeDAO.getName(), employeeDAO.getAge(), employeeDAO.getSalary(),
                foreignKey, employeeDAO.getId());
        return new EmployeeDAO(employeeJPARepository.findById(employeeDAO.getId().toString()).orElseThrow());
    }

    public void deleteEmployee(String id){
        employeeJPARepository.deleteById(id);
    }
}
