package com.example.dbservice.repository;

import com.example.dbservice.pojo.entity.Departments;
import com.example.dbservice.pojo.entity.Employees;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Transactional
public interface EmployeeJPARepository extends JpaRepository<Employees, Integer> {
    @Query("select e from Employees e where e.id = :employee_id")
    Optional<Employees> findById(@Param("employee_id") String employee_id);

    @Modifying
    @Query("update Employees e set e.name = :name, e.age = :age, e.salary = :salary, e.department = :dept_id where e.id = :employee_id")
    void updateById(@Param("name") String name, @Param("age") Integer age,
                                   @Param("salary") Integer salary, @Param("dept_id") Departments dept_id,
                                   @Param("employee_id") Integer employee_id);

    @Modifying
    @Query("delete from Employees e where e.id = :employee_id")
    void deleteById(@Param("employee_id") String employee_id);
}
