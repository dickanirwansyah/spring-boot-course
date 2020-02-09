package com.dicka.examplepagingsort.repository;

import com.dicka.examplepagingsort.entity.Employee;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query("SELECT DISTINCT e.dept FROM Employee e")
    List<String> findAllDepartments(Sort sort);

}
