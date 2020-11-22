package Benco.TRMS.pojos;

public class Department {
	
	private int deptId;
	
	private String deptName;
	

	public Department(String deptName) {
		super();
		this.deptName = deptName;
	}

	public int getDeptId() {
		return deptId;
	}

	public void setDeptId(int deptId) {
		this.deptId = deptId;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	
	

}
