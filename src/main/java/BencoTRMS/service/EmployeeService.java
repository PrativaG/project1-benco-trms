package BencoTRMS.service;

import java.util.List;

import BencoTRMS.exception.TitleNotAvailableException;
import BencoTRMS.pojos.Employee;

public interface EmployeeService {
	
	public Employee createEmployee(Employee e) throws TitleNotAvailableException;
	
	public Employee displayEmployeeById(int id);
	
	public List<Employee> displayAllEmployees();
	
	public Employee displayEmployeeByEmail(String email);
	
	public boolean updateEmployee(Employee e);
	
	public boolean deleteEmployeeByd(int id);
}
