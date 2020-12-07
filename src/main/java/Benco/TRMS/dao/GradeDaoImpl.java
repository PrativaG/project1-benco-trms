package Benco.TRMS.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Benco.TRMS.pojos.Grade;
import Benco.TRMS.util.ConnectionUtil;

public class GradeDaoImpl implements GradeDao{
	
	private ConnectionUtil conUtil = new ConnectionUtil();
	
	private PreparedStatement ps;
	
	public void setConnUtil(ConnectionUtil conUtil) {
		this.conUtil = conUtil;
	}

	@Override
	public Grade insertGrade(Grade g) {
		
		try(Connection con = conUtil.getConnection()){
			
			String sql = "insert into grade (g_type, file, status) values (?, ?, ?);";
			
			ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			
			ps.setString(1, g.getType());
			ps.setBytes(2, g.getFile());
			ps.setString(3, "Pending");
			
			ps.executeUpdate();
			
			ResultSet r = ps.getGeneratedKeys();
			 
			 r.next();
			 
			 g.setGradeID(r.getInt(1));
			
			return g;
			
		}catch (SQLException se) {
			se.printStackTrace();
		}
		
		return null;
	}

	@Override
	public Grade updateGrade(Grade g) {
		
		try(Connection con = conUtil.getConnection()){
			
			String sql = "update grade set g_type = ?, file = ?, status = ?"
					+ " where grade_id = ?;";
			
			ps = con.prepareStatement(sql);
			
			ps.setString(1, g.getType());
			ps.setBytes(2, g.getFile());
			ps.setString(3, g.getStatus());
			ps.setInt(4, g.getGradeID());
			
			 ps.executeUpdate();
			 
			
			
			return g;
			
		}catch (SQLException se) {
			se.printStackTrace();
		}
		return null;
	}

	@Override
	public Grade selectGradeById(int id) {
		try(Connection con = conUtil.getConnection()){
			
			String sql = "select * from grade "
					+ " where grade_id = ?;";
			
			ps = con.prepareStatement(sql);
			
			ps.setInt(1, id);

			ResultSet rs = ps.executeQuery();
			
			Grade g = new Grade();
			
			while(rs.next()) {
				g.setGradeID(rs.getInt(1));
				g.setType(rs.getString(2));
				g.setFile(rs.getBytes(3));
				g.setStatus(rs.getString(4));
			}
			return g;
			
		}catch (SQLException se) {
			se.printStackTrace();
		}
		
		return null;
	}

	@Override
	public Grade selectGradeByEventId(int eventId) {
		try(Connection con = conUtil.getConnection()){
			
			String sql = "select * from grade "
					+ " where grade_id = ?;";
			
			ps = con.prepareStatement(sql);
			
			ps.setInt(1, eventId);

			ResultSet rs = ps.executeQuery();
			
			Grade g = new Grade();
			
			while(rs.next()) {
				g.setGradeID(rs.getInt(1));
				g.setType(rs.getString(2));
				g.setFile(rs.getBytes(3));
				g.setStatus(rs.getString(4));
			}
			return g;
			
		}catch (SQLException se) {
			se.printStackTrace();
		}
		
		return null;
	}
}


