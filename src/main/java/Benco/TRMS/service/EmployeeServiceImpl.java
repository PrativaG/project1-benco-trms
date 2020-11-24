package Benco.TRMS.service;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;

import Benco.TRMS.dao.EmployeeDaoImpl;
import Benco.TRMS.pojos.Employee;
import Benco.TRMS.pojos.PasswordHashing;

public class EmployeeServiceImpl implements EmployeeService {
	
	private EmployeeDaoImpl empDao = new EmployeeDaoImpl();
	
	
	@Override
	public Employee createEmployee(Employee e) {
		
		//calling customized class PasswordHashing to hash the passowrd
		try {
			String hashedPassword = PasswordHashing.hashPassword(e.getPassword());
			System.out.println(hashedPassword);
			e.setPassword(hashedPassword);
		} catch (NoSuchAlgorithmException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InvalidKeySpecException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		Employee newEmp = empDao.insertEmployee(e);
		return newEmp;
	}

	@Override
	public Employee displayEmployeeById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Employee> displayAllEmployees() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean updateEmployee(Employee e) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteEmployeeByd(int id) {
		// TODO Auto-generated method stub
		return false;
	}

}
