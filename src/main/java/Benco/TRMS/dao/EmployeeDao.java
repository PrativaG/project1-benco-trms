package Benco.TRMS.dao;

import java.util.List;

import Benco.TRMS.pojos.Employee;

public interface EmployeeDao {
	
	public Employee insertEmployee(Employee e);
	
	public Employee selectById(int id);
	
	public List<Employee> selectAllEmployees();
	
	public boolean updateEmployee(Employee e);
	
	public boolean deleteById(int id);
}
