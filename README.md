# DataBase CRUD

### Configuration
* Database: PostgreSQL with existing tables and data (see "Database Initialization.txt" for database initialization)
* There are some example requests in example.txt
* The design of Rest API is in RestAPI.txt

### Program Introduction

This program implemented the following functions(all of them are about employees since the method of CRUD for 
both tables are similar):

* Database CRUD with hibernate + entity manager
  * get the information of an employee with id (return DTO)
  * insert a new employee (Custom new id is not allowed, return the new id)
  * update current employee with id (return updated employee's DTO)
  * delete an employee with id (Nothing returned if success)
* Database CRUD with Spring JPA Repository
  * Similar to Hibernate above, using @Query and @Modifying
  * Since JPA Repository doesn't accept "insert into", I used extended method "JPARepository.save()" instead (Although native SQL also works)
* Dynamic Query with JPA Criteria Query
  * A single search query: "select e.name, e.age, e.salary, d.name from employees e inner join departments d on e.dept_id = d.id where e.age > {age} and d.name = {department_name}"

