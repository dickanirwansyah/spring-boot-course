package com.dicka.examplepagingsort.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "employee")
public class Employee implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String dept;
    private int salary;

    public static Employee create(String name, String dept, int salary){
        Employee employee = Employee.builder()
                .name(name)
                .dept(dept)
                .salary(salary)
                .build();
        return employee;
    }
}
