package Benco.TRMS.dao;

import java.util.List;

import Benco.TRMS.pojos.Department;
import Benco.TRMS.pojos.Employee;

public interface EmployeeDao {
	
	public Employee insertEmployee(Employee e);
	
	public Employee selectById(int id);
	
	public Employee selectByEmail(String email);
	
	public List<Employee> selectAllEmployees();
	
	public boolean updateEmployee(Employee e);
	
	public boolean deleteById(int id);
	
	public int countEmployeeByTitleAndDept(String title, Department dept);
}
