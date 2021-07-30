package Benco.TRMS.testDao;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import BencoTRMS.dao.EmployeeDao;
import BencoTRMS.dao.EmployeeDaoImpl;
import BencoTRMS.pojos.Department;
import BencoTRMS.pojos.Employee;
import BencoTRMS.util.ConnectionUtil;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeDaoImplTest {
	
	@Mock
	private ConnectionUtil conUtil;
	@Mock
	private Connection con;
	
	private Connection realCon;
	
	private PreparedStatement stmt;
	
	private PreparedStatement trueStmt;
	
	private PreparedStatement spy;
	
	private EmployeeDaoImpl empDao;
	
	private Employee emp;
	
	@Mock
	private Department dept;
	
	

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		//getting new connection
		realCon = new ConnectionUtil().getConnection();
		
		//setting dao with mocked connectionutil
		empDao = new EmployeeDaoImpl();
		empDao.setConnUtil(conUtil);
		
		Mockito.when(emp.getDepartment()).thenReturn(Department.FINANCE);
		
		emp = new Employee("Mighty", "Jackson", dept, "123456789", "mj@mail.com", "password", "Manager" );
	}

	@After
	public void tearDown() throws Exception {
		
		realCon.close();
	}

	
	
	public void createSpyConnection(String sql) throws SQLException {
		
		//creating a real statement from a connection
		stmt = realCon.prepareStatement(sql);
		
		//spying on that real statement
		spy = Mockito.spy(stmt);
		
		//mocking connection and util
		Mockito.when(conUtil.getConnection()).thenReturn(con);
		Mockito.when(con.prepareStatement(sql)).thenReturn(spy);
	}
	
//	@Test
//	public void testInsertEmployee() {
//		
//		String sql = "insert into employe (first_name, last_name, email, contact, password, dept)"
//					+ " values (?, ? , ? , ?, ?, department(?));";
//		
//		try {
//			
//			createSpyConnection(sql);
//			
//		}catch(SQLException e) {
//			e.printStackTrace();
//		}
//		
//		try {
//			
//			System.out.println("Form test: " +emp.getEmployeeId());
//			
//			Employee dummy = empDao.insertEmployee(emp);
//			
//			Mockito.verify(spy).setString(1, emp.getFirstName());
//			Mockito.verify(spy).setString(2, emp.getLastName());
//			Mockito.verify(spy).setString(3, emp.getEmail());
//			Mockito.verify(spy).setString(4, emp.getContact());
//			Mockito.verify(spy).setString(5, emp.getPassword());
//			Mockito.verify(spy).setString(6, String.valueOf(emp.getDepartment()));
//			
//			Mockito.verify(spy).executeUpdate();
//			
//			assertEquals(emp, dummy);
//			
//		}catch(SQLException e) {
//			e.printStackTrace();
//		}
//	}
	
//	@Test
//	public void testSelectById() {
//		
//		String sql = "insert into employe (first_name, last_name, email, contact, password, dept)"
//					+ " values (?, ? , ? , ?, ?, department(?));";
//		try {
//			
//			trueStmt = realCon.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
//			
//			trueStmt.setString(1, emp.getFirstName());
//			trueStmt.setString(2, emp.getLastName());
//			trueStmt.setString(3, emp.getEmail());
//			trueStmt.setString(4, emp.getContact());
//			trueStmt.setString(5, emp.getPassword());
//			trueStmt.setString(6, String.valueOf(emp.getDepartment()));
//			
//			 trueStmt.executeUpdate();
//			
//			 ResultSet rs_id = trueStmt.getGeneratedKeys();
//			 rs_id.next();
//			 emp.setEmployeeId(rs_id.getInt(1));
//			 
//			assertTrue(" inserting dummy employee not successful", 1 == trueStmt.executeUpdate());
//
//			
//		}catch(SQLException se) {
//			se.printStackTrace();
//		}
//		
//		sql = "select * from employe where emp_id = ?;";
//		
//		try {
//			
//			createSpyConnection(sql);
//			
//		}catch(SQLException e) {
//			
//			e.printStackTrace();
//			
//		}
//		
//		try {
//			//test was failing when dummy was put after executing query
//			Employee dummy  = empDao.selectById(emp.getEmployeeId());
//			
//			Mockito.verify(spy).setInt(1, emp.getEmployeeId());
//			Mockito.verify(spy).executeQuery();
//			
//			assertEquals("Couldn't retreive proper employee", dummy.getEmployeeId(), emp.getEmployeeId());
//		}catch (SQLException e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
//		
//	}
//	
	@Test
	public void testSelectAllEmployees() {
		
		String sql = "select count(*) from employe;";
		int totalEmp = -1;
		
		try {
			
			trueStmt = realCon.prepareStatement(sql);
			
			ResultSet rs = trueStmt.executeQuery();
			
			rs.next();
			
			totalEmp = rs.getInt(1);
			
		} catch (SQLException e) {
			fail("SQL exception occured " +e);
		}
		
		sql = "select * from employe;";
		
		try {
			
			createSpyConnection(sql);
			
		}catch(SQLException e) {
			
			e.printStackTrace();
			
		}
		
		try {
			
			List<Employee> allEmployee = empDao.selectAllEmployees();
			
			verify(spy).executeQuery();
			
			assertTrue("Can't retreive all employees", totalEmp == allEmployee.size());
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Test 
	public void testUpdateEmployee() {
		
		String sql = "insert into employe (first_name, last_name, email, contact, password, dept)"
				+ " values (?, ? , ? , ?, ?, department(?));";
		try {
			
			trueStmt = realCon.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			
			trueStmt.setString(1, emp.getFirstName());
			trueStmt.setString(2, emp.getLastName());
			trueStmt.setString(3, emp.getEmail());
			trueStmt.setString(4, emp.getContact());
			trueStmt.setString(5, emp.getPassword());
			trueStmt.setString(6, String.valueOf(emp.getDepartment()));
			
			 trueStmt.executeUpdate();
			
			 ResultSet rs_id = trueStmt.getGeneratedKeys();
			 rs_id.next();
			 emp.setEmployeeId(rs_id.getInt(1));
			 
			assertTrue(" inserting dummy employee not successful", 1 == trueStmt.executeUpdate());

			
		}catch(SQLException se) {
			se.printStackTrace();
		}
		
		sql = "update employe set first_name = ?, last_name = ?, email = ?, "
				+ "contact = ?, (department)dept = ? where emp_id = ?;";
		
		try {
			
			createSpyConnection(sql);
			
		}catch(SQLException e) {
			
			e.printStackTrace();
			
		}
		
		try {
			emp.setFirstName("Changed");
			emp.setLastName("Or not");
			
			boolean isUpdated = empDao.updateEmployee(emp);
			
			verify(spy).setString(1, emp.getFirstName());
			verify(spy).setString(2, emp.getLastName());
			verify(spy).setString(3, emp.getEmail());
			verify(spy).setString(4, emp.getContact());
			verify(spy).setString(6, String.valueOf(emp.getDepartment()));
			verify(spy).setInt(6, emp.getEmployeeId());
			
			verify(spy).executeUpdate();
			
			assertTrue("Update employee failed", isUpdated);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void testDeleteById() {
		String sql = "insert into employe (first_name, last_name, email, contact, password, dept)"
				+ " values (?, ? , ? , ?, ?, department(?));";
		try {
			
			trueStmt = realCon.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			
			trueStmt.setString(1, emp.getFirstName());
			trueStmt.setString(2, emp.getLastName());
			trueStmt.setString(3, emp.getEmail());
			trueStmt.setString(4, emp.getContact());
			trueStmt.setString(5, emp.getPassword());
			trueStmt.setString(6, String.valueOf(emp.getDepartment()));
			
			 trueStmt.executeUpdate();
			
			 ResultSet rs_id = trueStmt.getGeneratedKeys();
			 rs_id.next();
			 emp.setEmployeeId(rs_id.getInt(1));
			 
			assertTrue(" inserting dummy employee not successful", 1 == trueStmt.executeUpdate());

			
		}catch(SQLException se) {
			se.printStackTrace();
		}
		
		sql = "delete from employe where emp_id = ?;";
		
		try {
			
			createSpyConnection(sql);
			
		}catch(SQLException e) {
			
			e.printStackTrace();
			
		}
		
		try {
			
			boolean isDeleted = empDao.deleteById(emp.getEmployeeId());
			
			verify(spy).setInt(1, emp.getEmployeeId());
			
			verify(spy).executeUpdate();
			
			assertTrue("Deletion of employee not successful!", isDeleted);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		
	}
	
	@Test
	public void testDeleteByWrongId() {
		String sql = "delete from employe where emp_id = ?;";
		
		try {
			
			createSpyConnection(sql);
			
		}catch(SQLException e) {
			
			e.printStackTrace();
			
		}
		
		try {
			
			boolean isDeleted = empDao.deleteById(1000);
			
			verify(spy).setInt(1, 1000);
			
			verify(spy).executeUpdate();
			
			assertFalse("Deletion of employee not successful!", isDeleted);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		
	}

}
