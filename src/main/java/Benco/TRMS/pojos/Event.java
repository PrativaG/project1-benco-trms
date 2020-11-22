package Benco.TRMS.pojos;

import java.io.File;
import java.time.LocalDate;
import java.util.Date;

public class Event {
	
	private String type;
	
	private LocalDate startDate;
	
	private LocalDate endDate;
	
	private double cost;
	
	private File grade;
	
	private Employee emp;
	
	
	public Event() {
		super();
	}

	public Event(String type, LocalDate startDate, LocalDate endDate, double cost, File grade) {
		super();
		this.type = type;
		this.startDate = startDate;
		this.endDate = endDate;
		this.cost = cost;
		this.grade = grade;
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

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public File getGrade() {
		return grade;
	}

	public void setGrade(File grade) {
		this.grade = grade;
	}

	public Employee getEmp() {
		return emp;
	}

	public void setEmp(Employee emp) {
		this.emp = emp;
	}
	
	
	
	
}
