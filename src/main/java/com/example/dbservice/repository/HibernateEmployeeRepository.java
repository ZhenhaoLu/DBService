package com.example.dbservice.repository;

import com.example.dbservice.pojo.dao.EmployeeDAO;
import com.example.dbservice.pojo.entity.Departments;
import com.example.dbservice.pojo.entity.Employees;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class HibernateEmployeeRepository {

    private final EntityManager entityManager;

    @Autowired
    public HibernateEmployeeRepository(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    public Employees findByID(String employee_id){
       Query query = entityManager.createQuery("select e from Employees e where e.id = ?1");
       query.setParameter(1, employee_id);
       Employees res = (Employees) query.getSingleResult();
       return res;
    }

    @Transactional
    public Integer insertEmployee(Employees e){
        entityManager.persist(e);
        return e.getId();
    }

    @Transactional
    public Employees updateEmployee(Employees e){
        return entityManager.merge(e);
    }

    @Transactional
    public void deleteEmployee(String employee_id){
        entityManager.remove(findByID(employee_id));
    }
}