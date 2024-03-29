package BencoTRMS.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import BencoTRMS.pojos.Department;
import BencoTRMS.pojos.Employee;
import BencoTRMS.pojos.Event;
import BencoTRMS.pojos.Grade;
import BencoTRMS.util.ConnectionUtil;

public class EventDaoImpl implements EventDao{
	
	private ConnectionUtil conUtil = new ConnectionUtil();
	
	private PreparedStatement ps;
	
	private EmployeeDaoImpl empDao = new EmployeeDaoImpl();
	private GradeDaoImpl gradeDao = new GradeDaoImpl();
	
	public void setConnUtil(ConnectionUtil connUtil) {
		this.conUtil = connUtil;
	}
	
	@Override
	public Event insertEvent(Event e) {
		
		try(Connection con = conUtil.getConnection()){
			
			String sql = "insert into emp_event (event_cost, event_type, start_date, end_date, "
					+ 	"request_date, description, ds_approval, hod_approval, coordinator_approval, emp_id, eligible_amount)" +
						" values(?, ?, date(?), date(?), date(?), ?, ?, ?, ?, ?, ?);";
			
			ps =con.prepareStatement(sql);
			
			ps.setDouble(1, e.getCost());
			ps.setString(2, e.getType());
			ps.setDate(3, Date.valueOf(e.getStartDate()));
			ps.setDate(4, Date.valueOf(e.getEndDate()));
			ps.setDate(5, Date.valueOf(e.getRequestDate()));
			ps.setString(6,  e.getDescription());
			ps.setString(7, "Pending");
			ps.setString(8, "Pending");			
			ps.setString(9, "Pending");	
			ps.setInt(10,  e.getEmp().getEmployeeId());
			ps.setDouble(11, e.getEligibleAmount());

			ps.executeUpdate();
			
			return e;

		}catch(SQLException s) {
			s.printStackTrace();
		}
		return null;
	}

	@Override
	public Event selectById(int id) {
		
		try(Connection con = conUtil.getConnection()){
			
			String sql = "select * from emp_event" +
						" where event_id = ? ;";
			
			ps =con.prepareStatement(sql);
			
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			
			Event e = new Event();
			
			while(rs.next()) {
				e.setId(rs.getInt(1));
				e.setCost(rs.getDouble(2));
				e.setType(rs.getString(3));
				e.setRequestDate((rs.getDate(4)).toLocalDate());
				e.setStartDate((rs.getDate(5)).toLocalDate());
				e.setEndDate((rs.getDate(6)).toLocalDate());
				e.setDescription(rs.getString(7));
				e.setReason(rs.getString(8));
				e.setEligibleAmount(rs.getDouble(9));
				e.setDsApproval(rs.getString(10));
				e.setHodApproval(rs.getString(11));
				e.setCoordinatorApproval(rs.getString(12));
				
				Employee em = empDao.selectById(rs.getInt(13));
				e.setEmp(em);
				
				Grade g = gradeDao.selectGradeById(rs.getInt(14));
				e.setGrade(g);
			}
			return e;

		}catch(SQLException s) {
			s.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Event> selectAllEvent() {
		List<Event> allEvents = new ArrayList<>();
		
		try(Connection con = conUtil.getConnection()){
			
			String sql = "select * from emp_event;"; 
			
			ps =con.prepareStatement(sql);
				
			ResultSet rs = ps.executeQuery();	
			
			while(rs.next()) {
				Event e = new Event();
				
				e.setId(rs.getInt(1));
				e.setCost(rs.getDouble(2));
				e.setType(rs.getString(3));
				e.setRequestDate((rs.getDate(4)).toLocalDate());
				e.setStartDate((rs.getDate(5)).toLocalDate());
				e.setEndDate((rs.getDate(6)).toLocalDate());
				e.setDescription(rs.getString(7));
				e.setReason(rs.getString(8));
				e.setEligibleAmount(rs.getDouble(9));
				e.setDsApproval(rs.getString(10));
				e.setHodApproval(rs.getString(11));
				e.setCoordinatorApproval(rs.getString(12));
				
				Employee em = empDao.selectById(rs.getInt(13));
				e.setEmp(em);
				
				Grade g = gradeDao.selectGradeById(rs.getInt(14));
				e.setGrade(g);
				
				allEvents.add(e);
			}
			
		}catch(SQLException s) {
			s.printStackTrace();
		}
		return allEvents;
	}

	@Override
	public List<Event> selectEventByDept(Department dept) {
		List<Event> allEvents = new ArrayList<>();
		
		try(Connection con = conUtil.getConnection()){
			
			String sql = "select * from emp_event ev, employe em"
					+ " where ev.emp_id = em.emp_id"
					+ " and em.dept = department(?);"; 
			
			ps =con.prepareStatement(sql);
			
			ps.setString(1, String.valueOf(dept));
				
			ResultSet rs = ps.executeQuery();	
			
			while(rs.next()) {
				Event e = new Event();
				
				e.setId(rs.getInt(1));
				e.setCost(rs.getDouble(2));
				e.setType(rs.getString(3));
				e.setRequestDate((rs.getDate(4)).toLocalDate());
				e.setStartDate((rs.getDate(5)).toLocalDate());
				e.setEndDate((rs.getDate(6)).toLocalDate());
				e.setDescription(rs.getString(7));
				e.setReason(rs.getString(8));
				e.setEligibleAmount(rs.getDouble(9));
				e.setDsApproval(rs.getString(10));
				e.setHodApproval(rs.getString(11));
				e.setCoordinatorApproval(rs.getString(12));
				
				Employee em = empDao.selectById(rs.getInt(13));
				e.setEmp(em);
				
				Grade g = gradeDao.selectGradeById(rs.getInt(14));
				e.setGrade(g);
				
				allEvents.add(e);
			}
			
		}catch(SQLException s) {
			s.printStackTrace();
		}
		return allEvents;
	}
	
	@Override
	public List<Event> selectEventByTtitle(String title) {
		List<Event> allEvents = new ArrayList<>();
		
		try(Connection con = conUtil.getConnection()){
			
			String sql = "select * from emp_event ev, employe em"
					+ " where ev.emp_id = em.emp_id"
					+ " and em.title = ?;"; 
			
			ps =con.prepareStatement(sql);
			
			ps.setString(1, title);
				
			ResultSet rs = ps.executeQuery();	
			
			while(rs.next()) {
				Event e = new Event();
				
				e.setId(rs.getInt(1));
				e.setCost(rs.getDouble(2));
				e.setType(rs.getString(3));
				e.setRequestDate((rs.getDate(4)).toLocalDate());
				e.setStartDate((rs.getDate(5)).toLocalDate());
				e.setEndDate((rs.getDate(6)).toLocalDate());
				e.setDescription(rs.getString(7));
				e.setReason(rs.getString(8));
				e.setEligibleAmount(rs.getDouble(9));
				e.setDsApproval(rs.getString(10));
				e.setHodApproval(rs.getString(11));
				e.setCoordinatorApproval(rs.getString(12));
				
				Employee em = empDao.selectById(rs.getInt(13));
				e.setEmp(em);
				
				Grade g = gradeDao.selectGradeById(rs.getInt(14));
				e.setGrade(g);
				
				allEvents.add(e);
			}
			
		}catch(SQLException s) {
			s.printStackTrace();
		}
		return allEvents;
	}

	@Override
	public List<Event> selectEventByEmployee(int empId) {
		List<Event> allEvents = new ArrayList<>();
		
		try(Connection con = conUtil.getConnection()){
			
			String sql = "select * from emp_event"
					+ " where emp_id = ?"; 
			
			ps =con.prepareStatement(sql);
			
			ps.setInt(1, empId);
				
			ResultSet rs = ps.executeQuery();	
			
			while(rs.next()) {
				Event e = new Event();
				
				e.setId(rs.getInt(1));
				e.setCost(rs.getDouble(2));
				e.setType(rs.getString(3));
				e.setRequestDate((rs.getDate(4)).toLocalDate());
				e.setStartDate((rs.getDate(5)).toLocalDate());
				e.setEndDate((rs.getDate(6)).toLocalDate());
				e.setDescription(rs.getString(7));
				e.setReason(rs.getString(8));
				e.setEligibleAmount(rs.getDouble(9));
				e.setDsApproval(rs.getString(10));
				e.setHodApproval(rs.getString(11));
				e.setCoordinatorApproval(rs.getString(12));
				
				Employee em = empDao.selectById(rs.getInt(13));
				e.setEmp(em);
				
				Grade g = gradeDao.selectGradeById(rs.getInt(14));
				e.setGrade(g);
				
				allEvents.add(e);
			}
			
		}catch(SQLException s) {
			s.printStackTrace();
		}
		return allEvents;
	}

	@Override
	public boolean updateEventFromEmployee(Event e) {
		
		try(Connection con = conUtil.getConnection()){
			
			String sql = "update emp_event set event_cost = ?, description =?, eligible_amount = ?"
					+ " where event_id = ?;"; 
			
			ps =con.prepareStatement(sql);
			
			//need to figure out how to store/parse files
			ps.setDouble(1, e.getCost());
//			ps.setDate(2, Date.valueOf(e.getStartDate()));
//			ps.setDate(3, Date.valueOf(e.getEndDate()));
			ps.setString(2, e.getDescription());
			ps.setDouble(3, e.getEligibleAmount());
			ps.setInt(4, e.getId());
			
			ps.executeUpdate();	
			
			return true;
			
		}catch(SQLException s) {
			s.printStackTrace();
		}
		return false;
	}
	
	//to add grade to event
	public boolean addGradetoEvent(Event e) {
		
		try(Connection con = conUtil.getConnection()){
			
			String sql = "update emp_event set  grade_id = ?"
					+ " where event_id = ?;"; 
			
			ps =con.prepareStatement(sql);
			
			ps.setInt(1, e.getGrade().getGradeID());;
			ps.setInt(2, e.getId());
			
			ps.executeUpdate();	
			
			return true;
			
		}catch(SQLException s) {
			s.printStackTrace();
		}
		return false;
	}
	
	@Override
	public boolean deleteById(int id) {
		
		try(Connection con = conUtil.getConnection()){
			
			String sql = "delete from emp_event where event_id = ?;"; 
			
			ps =con.prepareStatement(sql);
	
			ps.executeUpdate();	
			
			return true;
			
		}catch(SQLException s) {
			
			s.printStackTrace();
			
		}
		return false;
	}

	@Override
	public boolean updateEventFromApprover(Event e) {
		
		try(Connection con = conUtil.getConnection()){
			
			String sql = "update emp_event set ds_approval = ?, hod_approval =?, coordinator_approval = ?, eligible_amount = ?, reason = ?"
					+ " where event_id = ?;"; 
			
			ps =con.prepareStatement(sql);
			
			System.out.println(e.getDsApproval());
			//need to figure out how to store/parse files
			ps.setString(1, e.getDsApproval());
			ps.setString(2, e.getHodApproval());
			ps.setString(3, e.getCoordinatorApproval());
			ps.setDouble(4, e.getEligibleAmount());
			ps.setString(5, e.getReason());
			ps.setInt(6, e.getId());
				
			ps.executeUpdate();	
			
			return true;
			
		}catch(SQLException s) {
			s.printStackTrace();
		}
		return false;
	}



}
