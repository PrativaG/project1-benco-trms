package Benco.TRMS.pojos;

public class Employee {
	
	private int employeeId;
	
	private String firstName;
	
	private String lastName;
		
	private String email;
	
	private String password;
	
	private  String contact;
	
	private Department department; // enum vs class
		
	
	public Employee() {
		super();
	}

	public Employee(String firstName, String lastName, Department department, String contact, String email,
			String password) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.department = department;
		this.email = email;
		this.password = password;
		this.contact = contact;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	
}
