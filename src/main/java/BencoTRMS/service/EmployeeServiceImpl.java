package BencoTRMS.service;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;

import org.apache.log4j.Logger;

import BencoTRMS.dao.EmployeeDaoImpl;
import BencoTRMS.exception.TitleNotAvailableException;
import BencoTRMS.pojos.Employee;

public class EmployeeServiceImpl implements EmployeeService {
	
	private EmployeeDaoImpl empDao = new EmployeeDaoImpl();
	
	Logger servicelog = Logger.getLogger(EmployeeServiceImpl.class.getName());
	@Override
	public Employee createEmployee(Employee e) throws TitleNotAvailableException {
		
		//first checking what the post is and allowing limited number
		//of direct supervisor, hod and coordinator
		Employee newEmp  = new Employee();
		String post = e.getTitle();
		
		int count = 0;
		
		if(post.equals("Direct Supervisor")) {
			count = empDao.countEmployeeByTitleAndDept(post, e.getDepartment());
			System.out.println(count);
			if( count == 2) {
				throw new TitleNotAvailableException();
			}
			if(count >= 0 && count < 2) {
				e = setupPassword(e);
				newEmp = empDao.insertEmployee(e);
			}
		}
		
		if(post.equals("Department Head") || post.equals("Benefit Coordinator") || post.equals("Direct Manager")) {
			count = empDao.countEmployeeByTitleAndDept(post, e.getDepartment());
			System.out.println(count);
			if( count >= 1) {
				throw new TitleNotAvailableException();
			}
			if(count < 1) {
				e = setupPassword(e);
				newEmp = empDao.insertEmployee(e);
			}
		}
		else {
		e = setupPassword(e);
		newEmp = empDao.insertEmployee(e);
		}
		
		return newEmp;
	}

	@Override
	public Employee displayEmployeeById(int id) {
		
		return empDao.selectById(id);
	}

	@Override
	public List<Employee> displayAllEmployees() {
		
		return null;
	}

	@Override
	public boolean updateEmployee(Employee e) {
		
		if(empDao.updateEmployee(e)) return true;
		
		return false;
	}

	@Override
	public boolean deleteEmployeeByd(int id) {
		
		return false;
	}
	
	private Employee setupPassword(Employee e) {
		
		//calling customized class PasswordHashing to hash the password
			try {
				
				String hashedPassword = PasswordHashing.hashPassword(e.getPassword());
				
				e.setPassword(hashedPassword);
				
				servicelog.info("Password was hasehd and assigned to employee successfully!");
			
			} catch (NoSuchAlgorithmException e1) {
				
				servicelog.error("NoSuchAlgorithmException at Service" +e);
				
				e1.printStackTrace();
			
			} catch (InvalidKeySpecException e1) {
				
				e1.printStackTrace();
			}
		return e;
	}

	@Override
	public Employee displayEmployeeByEmail(String email) {
		
		return empDao.selectByEmail(email);
	}
}
