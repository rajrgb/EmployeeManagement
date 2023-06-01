package com.employee.management.models;



import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="leaveDetails")
public class Leave {
@Id
@GeneratedValue
private int id;

private int earnedLeave;
private int sickLeave;





public Leave() {
	super();
	// TODO Auto-generated constructor stub
}

public Leave( int earnedLeave, int sickLeave) {
	super();
	this.earnedLeave = earnedLeave;
	this.sickLeave = sickLeave;
}

public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public int getEarnedLeave() {
	return earnedLeave;
}

public void setEarnedLeave(int earnedLeave) {
	this.earnedLeave = earnedLeave;
}

public int getSickLeave() {
	return sickLeave;
}

public void setSickLeave(int sickLeave) {
	this.sickLeave = sickLeave;
}


@Override
public String toString() {
	return "Leave [id=" + id + ", earnedLeave=" + earnedLeave + ", sickLeave=" + sickLeave
			 + ", employee=" +  "]";
}

}
