package Benco.TRMS.pojos;

public class HOD extends Employee {
	
	private int hodId;
	
	private final int totalApprovalHours = 72;
	
	private Employee emp;
	
	

	public HOD() {
		super();
		// TODO Auto-generated constructor stub
	}

//	public HOD(String firstName, String lastName, Department department, String contact, String email,
//			String password) {
//		super(firstName, lastName, department, contact, email, password);
//		// TODO Auto-generated constructor stub
//	}

	public HOD(Employee emp) {
		super();
		this.emp = emp;
	}

	public int getHodId() {
		return hodId;
	}

	public void setHodId(int hodId) {
		this.hodId = hodId;
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
