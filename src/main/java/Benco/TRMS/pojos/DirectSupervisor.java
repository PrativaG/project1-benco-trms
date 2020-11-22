package Benco.TRMS.pojos;

public class DirectSupervisor extends Employee {
	
	private int supervisorId;
	
	private final int totalApprovalHours = 168;
	
	private Employee emp;

	public DirectSupervisor() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DirectSupervisor( String firstName, String lastName, Department department, String contact, String email,
			String password) {
		super(firstName, lastName, department, contact, email, password);
	}

	public DirectSupervisor(Employee emp) {
		super();
		this.emp = emp;
	}

	public int getSupervisorId() {
		return supervisorId;
	}

	public void setSupervisorId(int supervisorId) {
		this.supervisorId = supervisorId;
	}

	public Employee getEmp() {
		return emp;
	}

	public void setEmp(Employee emp) {
		this.emp = emp;
	}

	public int getTotalApprovalHours() {
		return totalApprovalHours;
	}
	
	
}
