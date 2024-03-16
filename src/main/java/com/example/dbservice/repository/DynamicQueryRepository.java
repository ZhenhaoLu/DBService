package com.example.dbservice.repository;

import com.example.dbservice.pojo.dto.DynamicQueryDTO;
import com.example.dbservice.pojo.entity.Departments;
import com.example.dbservice.pojo.entity.Employees;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DynamicQueryRepository {
    private final EntityManager entityManager;
    private final CriteriaBuilder criteriaBuilder;

    @Autowired
    public DynamicQueryRepository(EntityManager entityManager){
        this.entityManager = entityManager;
        this.criteriaBuilder = entityManager.getCriteriaBuilder();
    }

    public List<DynamicQueryDTO> specialSearch(Integer age, String departmentName){
        CriteriaQuery<DynamicQueryDTO> query = this.criteriaBuilder.createQuery(DynamicQueryDTO.class);
        Root<Employees> employeesRoot = query.from(Employees.class);
        Join<Employees, Departments> eDJoin = employeesRoot.join("department", JoinType.INNER);
        Predicate ageCondition = this.criteriaBuilder.greaterThan(employeesRoot.get("age"), age);
        Predicate deptNameCondition = this.criteriaBuilder.equal(eDJoin.get("name"), departmentName);
        Predicate finalCondition = this.criteriaBuilder.and(ageCondition, deptNameCondition);
        query.multiselect(employeesRoot.get("name").alias("employeeName"),
                employeesRoot.get("age").alias("employeeAge"), employeesRoot.get("salary").alias("employeeSalary"),
                eDJoin.get("name").alias("departmentName")).where(finalCondition);
        return this.entityManager.createQuery(query).getResultList();
    }
}
