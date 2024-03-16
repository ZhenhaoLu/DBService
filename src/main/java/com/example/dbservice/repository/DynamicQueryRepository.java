package com.example.dbservice.repository;

import com.example.dbservice.pojo.dao.TupleDTO;
import com.example.dbservice.pojo.entity.Departments;
import com.example.dbservice.pojo.entity.Employees;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Parameter;
import jakarta.persistence.Tuple;
import jakarta.persistence.criteria.*;
import jakarta.persistence.metamodel.EntityType;
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

    public List<TupleDTO> specialSearch(Integer age, String departmentName){
        CriteriaQuery<TupleDTO> query = this.criteriaBuilder.createQuery(TupleDTO.class);
        Root<Employees> employeesRoot = query.from(Employees.class);
        Join<Employees, Departments> eDJoin = employeesRoot.join("department", JoinType.INNER);
        Predicate ageCondition = this.criteriaBuilder.greaterThan(employeesRoot.get("age"), age);
        Predicate deptNameCondition = this.criteriaBuilder.equal(eDJoin.get("name"), departmentName);
        Predicate finalCondition = this.criteriaBuilder.and(ageCondition, deptNameCondition);
        query.multiselect(employeesRoot.get("name").alias("employeeName"),
                employeesRoot.get("age").alias("employeeAge"), employeesRoot.get("salary").alias("employeeSalary"),
                eDJoin.get("name").alias("departmentName")).where(finalCondition);
//        CriteriaQuery<Tuple> qq = this.criteriaBuilder.createTupleQuery();
//        Root<Employees> employeesRoot = qq.from(Employees.class);
//        Root<Departments> departmentsRoot = qq.from(Departments.class);
//        qq.multiselect(employeesRoot, departmentsRoot);
//        System.out.println("Here");
//        System.out.println(this.entityManager.createQuery(qq).getResultList().get(0).get("salary"));
//        return null;
        return this.entityManager.createQuery(query).getResultList();
    }
}
