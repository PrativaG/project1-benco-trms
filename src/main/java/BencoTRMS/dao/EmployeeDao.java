package BencoTRMS.dao;

import java.util.List;

import BencoTRMS.pojos.Department;
import BencoTRMS.pojos.Employee;

public interface EmployeeDao {
	
	public Employee insertEmployee(Employee e);
	
	public Employee selectById(int id);
	
	public Employee selectByEmail(String email);
	
	public List<Employee> selectAllEmployees();
	
	public boolean updateEmployee(Employee e);
	
	public boolean deleteById(int id);
	
	public int countEmployeeByTitleAndDept(String title, Department dept);
}
