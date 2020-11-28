package Benco.TRMS.pojos;

import java.io.File;
import java.time.LocalDate;
import java.util.Date;

public class Event {
	
	private int id;
	
	private String type;
	
	private LocalDate startDate;
	
	private LocalDate endDate;
	
	private LocalDate requestDate;
	
	private double cost;
	
	private String grade;
	
//	private File presentation;
	
	private Employee emp;
	
	
	public Event() {
		super();
	}

	public Event(String type, LocalDate startDate, LocalDate endDate, LocalDate reqDate, double cost) {
		super();
		this.type = type;
		this.startDate = startDate;
		this.endDate = endDate;
		this.requestDate = reqDate;
		this.cost = cost;
	
	}
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}
	

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

//	public File getpresentation() {
//		return presentation;
//	}
//
//	public void setpresentation(File presentation) {
//		this.presentation = presentation;
//	}

	public Employee getEmp() {
		return emp;
	}

	public void setEmp(Employee emp) {
		this.emp = emp;
	}

	public LocalDate getRequestDate() {
		return requestDate;
	}

	public void setRequestDate(LocalDate requestDate) {
		this.requestDate = requestDate;
	}
}
