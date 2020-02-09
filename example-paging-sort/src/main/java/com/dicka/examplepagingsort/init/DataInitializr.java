package com.dicka.examplepagingsort.init;

import com.dicka.examplepagingsort.entity.Employee;
import com.dicka.examplepagingsort.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
public class DataInitializr {

    @Autowired
    private EmployeeRepository employeeRepository;

    @PostConstruct
    void postConstruct(){
        employeeRepository.saveAll(createEmployees());
    }

    private List<Employee> createEmployees(){
        String[] departments = {"IT", "Sales", "Admin", "Account"};
        List<Employee> employees = new ArrayList<>();
        for (int i=0; i < 100; i++){
            employees.add(Employee.create(
                    RandomUtil.getFullName(),
                    RandomUtil.getAnyOf(departments),
                    RandomUtil.getInt(1, 10) * 1000));
        }
        return employees;
    }
}
