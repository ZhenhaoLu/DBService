package com.example.dbservice.repository;

import com.example.dbservice.pojo.entity.Departments;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class HibernateDepartmentRepository {

    private final EntityManager entityManager;

    @Autowired
    public HibernateDepartmentRepository(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    public Departments findByID(String department_id){
        Query query = entityManager.createQuery("select d from Departments d where d.id = ?1");
        query.setParameter(1, department_id);
        return (Departments) query.getSingleResult();
    }
}
