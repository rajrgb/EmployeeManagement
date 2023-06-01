package com.employee.management.models;

import java.util.List;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Employee {
@Id
private int id;
private String name;
private String role;

private String joinDate;
private String status; //available, onleave

@OneToOne(cascade = CascadeType.ALL, orphanRemoval=true)
private Leave leave;  



public Employee() {
	super();
	// TODO Auto-generated constructor stub
}
public Employee(int id, String name, String role, String joinDate, Leave leave, String status) {
	super();
	this.id = id;
	this.name = name;
	this.role = role;
	this.joinDate = joinDate;
	this.leave = leave;
	this.status=status;
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
public String getRole() {
	return role;
}
public void setRole(String role) {
	this.role = role;
}
public String getJoinDate() {
	return joinDate;
}
public void setJoinDate(String joinDate) {
	this.joinDate = joinDate;
}
public String getStatus() {
	return status;
}
public void setStatus(String status) {
	this.status = status;
}
public Leave getLeave() {
	return leave;
}
public void setLeave(Leave leave) {
	this.leave = leave;
}


@Override
public String toString() {
	return "Employee [id=" + id + ", name=" + name + ", role=" + role + ", joinDate=" + joinDate + ", leave=" + leave
			+ ", status="+ status+ "]";
}




}
