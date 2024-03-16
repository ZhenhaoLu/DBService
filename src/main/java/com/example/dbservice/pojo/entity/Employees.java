package com.example.dbservice.pojo.entity;

import jakarta.persistence.*;
import lombok.Data;



@Entity
@Table()
@Data
public class Employees {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String name;
    @Column
    private Integer age;
    @Column
    private Integer salary;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dept_id")
    private Departments department;
}
