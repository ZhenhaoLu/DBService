package com.example.dbservice.pojo.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table()
public class Departments {

    @Id
    private Integer id;
    @Column
    private String name;
    @OneToMany(mappedBy = "department", fetch = FetchType.EAGER)
    private List<Employees> employeesList;
}
