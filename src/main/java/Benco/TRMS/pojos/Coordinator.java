package Benco.TRMS.pojos;

public class Coordinator extends Employee {
	
	private int coordinatorId;
	
	private final int totalApprovalHours = 48;

	
	private Employee emp;
	
	

	public Coordinator() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Coordinator(String firstName, String lastName, Department department, String contact, String email,
			String password) {
		super(firstName, lastName, department, contact, email, password);
		// TODO Auto-generated constructor stub
	}

	public Coordinator(Employee emp) {
		super();
		this.emp = emp;
	}

	public int getCoordinatorId() {
		return coordinatorId;
	}

	public void setCoordinatorId(int coordinatorId) {
		this.coordinatorId = coordinatorId;
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
