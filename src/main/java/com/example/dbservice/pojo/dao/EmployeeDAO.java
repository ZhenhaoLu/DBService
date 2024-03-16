package com.example.dbservice.pojo.dao;

import com.example.dbservice.pojo.entity.Employees;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

//@Entity
@Data
@NoArgsConstructor
public class EmployeeDAO {
    private Integer id;
    private String name;
    private Integer age;
    private Integer salary;
    private Integer dept_id;

    public EmployeeDAO(Employees employees){
        this.id = employees.getId();
        this.name = employees.getName();
        this.age = employees.getAge();
        this.salary = employees.getSalary();
        this.dept_id = employees.getDepartment().getId();
    }
}
