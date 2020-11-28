package Benco.TRMS.service;

import java.util.List;

import Benco.TRMS.exception.TitleNotAvailableException;
import Benco.TRMS.pojos.Employee;

public interface EmployeeService {
	
	public Employee createEmployee(Employee e) throws TitleNotAvailableException;
	
	public Employee displayEmployeeById(int id);
	
	public List<Employee> displayAllEmployees();
	
	public Employee displayEmployeeByEmail(String email);
	
	public boolean updateEmployee(Employee e);
	
	public boolean deleteEmployeeByd(int id);
}
