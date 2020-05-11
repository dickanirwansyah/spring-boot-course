package id.corp.project.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import id.corp.project.model.Employee;

@Repository
public class EmployeeRepository {

	public List<Employee> getEmployees(){
		List<Employee> employees = new ArrayList<>();
		employees.add(new Employee(1, "Muhammad Dicka Nirwansyah", "Java Developer"));
		employees.add(new Employee(2, "Irma Khairunnisa", "Teacher"));
		employees.add(new Employee(3, "Jamaludin Salam", "ReactJs Developer"));
		return employees;
	}
	
}
