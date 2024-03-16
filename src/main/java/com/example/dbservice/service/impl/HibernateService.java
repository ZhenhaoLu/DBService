package com.example.dbservice.service.impl;

import com.example.dbservice.pojo.dao.EmployeeDAO;
import com.example.dbservice.pojo.entity.Departments;
import com.example.dbservice.pojo.entity.Employees;
import com.example.dbservice.repository.HibernateDepartmentRepository;
import com.example.dbservice.repository.HibernateEmployeeRepository;
import com.example.dbservice.service.IDBService;
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

    public EmployeeDAO findEmployeeById(String id){
        Employees res = this.emRepository.findByID(id);
        return new EmployeeDAO(res);
    }

    public Integer insertEmployee(EmployeeDAO employeeDAO){
        Employees e = IDBService.setEmployees(employeeDAO);
        return this.emRepository.insertEmployee(e);
    }

    public EmployeeDAO updateEmployee(EmployeeDAO employeeDAO){
        Employees e = IDBService.setEmployees(employeeDAO);
        e.setId(employeeDAO.getId());
        e = this.emRepository.updateEmployee(e);
        return new EmployeeDAO(e);
    }

    public void deleteEmployee(String id){
        this.emRepository.deleteEmployee(id);
    }


    // For test
    public Departments findDepartmentById(String id){
        return this.departmentRepository.findByID(id);
    }

//    public static Employees setEmployees(EmployeeDAO employeeDAO){
//        Employees e = new Employees();
//        e.setAge(employeeDAO.getAge());
//        e.setName(employeeDAO.getName());
//        e.setSalary(employeeDAO.getSalary());
//        // I can also search database to get all info of the departments, but it takes longer time
//        // if error, both methods will throw errors (Constraint Violate or no result found)
//        Departments dept_id = new Departments();
//        dept_id.setId(employeeDAO.getDept_id());
//        e.setDepartment(dept_id);
//        return e;
//    }
}
