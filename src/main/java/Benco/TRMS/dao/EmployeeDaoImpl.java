package Benco.TRMS.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


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
			
			String sql = "insert into employe (first_name, last_name, email, contact, password, dept)"
					+ " values (?, ? , ? , ?, ?, department(?));";
			
			ps = con.prepareStatement(sql);
			
			ps.setString(1, e.getFirstName());
			ps.setString(2, e.getLastName());
			ps.setString(3, e.getEmail());
			ps.setString(4, e.getContact());
			ps.setString(5, e.getPassword());
			ps.setString(6, String.valueOf(e.getDepartment()));
			
			ps.executeUpdate();
			
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
				e.setContact(rs.getString(6));
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
				e.setContact(rs.getString(6));
				
				allEmployee.add(e);
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
					+ "contact = ?, dept_id = ? where emp_id = ?;";
			
			ps = con.prepareStatement(sql);
			
			ps.setString(1, e.getFirstName());
			ps.setString(2, e.getLastName());
			ps.setString(3, e.getEmail());
			ps.setString(4, e.getContact());
			ps.setString(5, String.valueOf(e.getDepartment()));
			ps.setInt(6, e.getEmployeeId());
			
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

}
