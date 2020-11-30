package Benco.TRMS.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Benco.TRMS.pojos.Department;
import Benco.TRMS.pojos.Employee;
import Benco.TRMS.util.ConnectionUtil;
import kotlin.collections.ArrayDeque;

public class EmployeeDaoImpl implements EmployeeDao {
	
	private ConnectionUtil conUtil = new ConnectionUtil();
	
	private PreparedStatement ps;
	
	public void setConnUtil(ConnectionUtil conUtil) {
		this.conUtil = conUtil;
	}

	@Override
	public Employee insertEmployee(Employee e) {
		
		try(Connection con = conUtil.getConnection()){
			
			String sql = "insert into employe (first_name, last_name, email, contact, password, dept, title, remainingClaimAmt)"
					+ " values (?, ? , ? , ?, ?, department(?), ?, ?);";
			
			ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			
			ps.setString(1, e.getFirstName());
			ps.setString(2, e.getLastName());
			ps.setString(3, e.getEmail());
			ps.setString(4, e.getContact());
			ps.setString(5, e.getPassword());
			ps.setString(6, String.valueOf(e.getDepartment()));
			ps.setString(7, e.getTitle());
			ps.setDouble(8, 1000.00);
			
			ps.executeUpdate();
			
			ResultSet rs = ps.getGeneratedKeys();
			
			rs.next();
			
			int id = rs.getInt(1);
			
			e.setEmployeeId(id);
			
			return e;
			
		}catch (SQLException se) {
			se.printStackTrace();
		}
		return null;
	}

	@Override
	public Employee selectById(int id) {
		
		Employee e = new Employee();
		
		try(Connection con = conUtil.getConnection()){
			String sql = "select * from employe where emp_id = ?;";
			
			ps = con.prepareStatement(sql);
			
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				e.setEmployeeId(id);
				e.setFirstName(rs.getString(2));
				e.setLastName(rs.getString(3));
				e.setEmail(rs.getString(4));
				e.setPassword(rs.getString(5));
				e.setContact(rs.getString(6));
				e.setDepartment(Department.valueOf(rs.getString(7)));
				e.setTitle(rs.getString(8));
				e.setRemainingClaimAmt(rs.getDouble(9));
			}
			
			
		}catch(SQLException se) {
			se.printStackTrace();
		}
		return e;
	}

	@Override
	public List<Employee> selectAllEmployees() {
		
		List<Employee> allEmployee = new ArrayList<>();
		
		try(Connection con = conUtil.getConnection()){
			String sql = "select * from employe;";
			
			ps = con.prepareStatement(sql);
			
			ResultSet rs  = ps.executeQuery();
			
			while(rs.next()) {
				Employee e = new Employee();
				
				e.setEmployeeId(rs.getInt(1));
				e.setFirstName(rs.getString(2));
				e.setLastName(rs.getString(3));
				e.setEmail(rs.getString(4));
				e.setPassword(rs.getString(5));
				e.setContact(rs.getString(6));
				e.setDepartment(Department.valueOf(rs.getString(7)));
				e.setTitle(rs.getString(8));
				e.setRemainingClaimAmt(rs.getDouble(9));
			}
			
		}catch(SQLException se) {
			se.printStackTrace();
		}
		
		return allEmployee;
	}

	@Override
	public boolean updateEmployee(Employee e) {
		
		try(Connection con = conUtil.getConnection()){
			
			String sql = "update employe set first_name = ?, last_name = ?, email = ?, "
					+ "contact = ?, dept = department(?), remainingclaimamt = ? where emp_id = ?;";
			
			ps = con.prepareStatement(sql);
			
			ps.setString(1, e.getFirstName());
			ps.setString(2, e.getLastName());
			ps.setString(3, e.getEmail());
			ps.setString(4, e.getContact());
//			ps.setString(5, e.getPassword());
			ps.setString(5, String.valueOf(e.getDepartment()));
			ps.setDouble(6, e.getRemainingClaimAmt());
			ps.setInt(7, e.getEmployeeId());
			
			ps.executeUpdate();
			
			return true;
		}catch(SQLException se) {
			se.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean deleteById(int id) {
		
		try(Connection con = conUtil.getConnection()){
			
			String sql = "delete from employe where emp_id = ?;";
			
			ps = con.prepareStatement(sql);
			
			ps.setInt(1, id);
			
			int i = ps.executeUpdate();
			
			if(i == 1) return true;
			
			
		}catch(SQLException se) {
			se.printStackTrace();
		}
		return false;
	}
	
	@Override
	public int countEmployeeByTitleAndDept(String title, Department dep) {
		int count = -1;
		
		String sql = "select count(*) from employe where" +
				" title = ? and dept = department(?);";
		
		try(Connection con = conUtil.getConnection()){
			
			
			ps = con.prepareStatement(sql);
			ps.setString(1, title);
			ps.setString(2, String.valueOf(dep));
			
			ResultSet rs = ps.executeQuery();
			
				if(rs.next()) {
					count = rs.getInt(1);
				}else {
					count = 0;
				}
				
			
		}catch(SQLException se) {
			
			se.printStackTrace();
		}

		return count;
	}

	@Override
	public Employee selectByEmail(String email) {
		Employee e = new Employee();
		
		try(Connection con = conUtil.getConnection()){
			String sql = "select * from employe where email = ?;";
			
			ps = con.prepareStatement(sql);
			
			ps.setString(1, email);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				e.setEmployeeId(rs.getInt(1));
				e.setFirstName(rs.getString(2));
				e.setLastName(rs.getString(3));
				e.setEmail(rs.getString(4));
				e.setPassword(rs.getString(5));
				e.setContact(rs.getString(6));
				e.setDepartment(Department.valueOf(rs.getString(7)));
				e.setTitle(rs.getString(8));
				e.setRemainingClaimAmt(rs.getDouble(9));
			}
			
			
		}catch(SQLException se) {
			se.printStackTrace();
		}
		return e;
	}
	
	

}
