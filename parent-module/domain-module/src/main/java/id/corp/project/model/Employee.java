package id.corp.project.model;

import java.io.Serializable;

public class Employee implements Serializable{

	private int id;
	private String name;
	private String message;
	
	public Employee() {
		
	}
	
	public Employee(int id, String name, String message) {
		super();
		this.id = id;
		this.name = name;
		this.message = message;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", message=" + message + "]";
	}

	
}

