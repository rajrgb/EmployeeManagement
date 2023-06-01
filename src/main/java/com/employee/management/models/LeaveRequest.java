package com.employee.management.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class LeaveRequest {
@Id
@GeneratedValue
private int id;

private String request;



public LeaveRequest() {
	super();
	// TODO Auto-generated constructor stub
}

public LeaveRequest(int id, String request) {
	super();
	this.id = id;
	this.request = request;
}

public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public String getRequest() {
	return request;
}

public void setRequest(String request) {
	this.request = request;
}





@Override
public String toString() {
	return "LeaveRequest [id=" + id + ", request=" + request  + "]";
}



}
