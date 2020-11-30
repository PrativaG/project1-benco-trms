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
	
	private String description;
	
//	private File presentation;
	
	private double eligibleAmount;
	
	private String reason;//for rejection and added amount
	
	private String dsApproval;
	
	private String coordinatorApproval;
	
	private String hodApproval;
	
	private Employee emp;
		
	
	public Event() {
		super();
	}

	public Event(String type, LocalDate startDate, LocalDate endDate, LocalDate reqDate, double cost, String desc) {
		super();
		this.type = type;
		this.startDate = startDate;
		this.endDate = endDate;
		this.requestDate = reqDate;
		this.cost = cost;
		this.description = desc;
	}
	
	public Event(String type, LocalDate startDate, LocalDate endDate, LocalDate reqDate, double cost, String desc, Employee emp) {
		super();
		this.type = type;
		this.startDate = startDate;
		this.endDate = endDate;
		this.requestDate = reqDate;
		this.cost = cost;
		this.description = desc;
		this.emp = emp;
	}
	
	
	public Event(int id, double cost, String desc) {
		super();
		this.id = id;
		this.cost = cost;
		this.description = desc;
		
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getEligibleAmount() {
		return eligibleAmount;
	}

	public void setEligibleAmount(double eligibleAmount) {
		this.eligibleAmount = eligibleAmount;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getDsApproval() {
		return dsApproval;
	}

	public void setDsApproval(String dsApproval) {
		this.dsApproval = dsApproval;
	}

	public String getCoordinatorApproval() {
		return coordinatorApproval;
	}

	public void setCoordinatorApproval(String coordinatorApproval) {
		this.coordinatorApproval = coordinatorApproval;
	}

	public String getHodApproval() {
		return hodApproval;
	}

	public void setHodApproval(String hodApproval) {
		this.hodApproval = hodApproval;
	}

	
	
	
}
