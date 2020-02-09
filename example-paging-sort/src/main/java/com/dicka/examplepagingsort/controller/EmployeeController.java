package com.dicka.examplepagingsort.controller;

import com.dicka.examplepagingsort.entity.Employee;
import com.dicka.examplepagingsort.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping(value = "/departments")
    public String getDepartments(@SortDefault(sort = "dept", direction = Sort.Direction.ASC)
                                 Sort sort, Model model){
        List<String> depts = employeeRepository.findAllDepartments(sort);
        model.addAttribute("depts", depts);
        return "dept-page";
    }

    @GetMapping(value = "/employees")
    public String getEmployees(@PageableDefault(size = 20, sort = "id")Pageable pageable,
                               Model model){
        Page<Employee> page = employeeRepository.findAll(pageable);
        List<Sort.Order> sortOrders = page.getSort().stream().collect(Collectors.toList());
        if (sortOrders.size() > 0){
            Sort.Order order = sortOrders.get(0);
            model.addAttribute("sortProperty", order.getProperty());
            model.addAttribute("sortDesc", order.getDirection() == Sort.Direction.DESC);
        }
        model.addAttribute("page", page);
        return "employee-page";
    }
}
