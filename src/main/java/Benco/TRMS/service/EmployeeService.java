package Benco.TRMS.service;

import java.util.List;

import Benco.TRMS.pojos.Employee;

public interface EmployeeService {
	
	public Employee createEmployee(Employee e);
	
	public Employee displayEmployeeById(int id);
	
	public List<Employee> displayAllEmployees();
	
	public boolean updateEmployee(Employee e);
	
	public boolean deleteEmployeeByd(int id);
}
