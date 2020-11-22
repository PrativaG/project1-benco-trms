package Benco.TRMS.dao;

import java.sql.PreparedStatement;
import java.util.List;


import Benco.TRMS.pojos.Event;
import Benco.TRMS.util.ConnectionUtil;

public class EventDaoImpl implements EventDao{
	
	private ConnectionUtil conUtil = new ConnectionUtil();
	
	private PreparedStatement ps;
	
	public void setConnUtil(ConnectionUtil connUtil) {
		this.conUtil = connUtil;
	}
	
	@Override
	public Event insertEvent(Event e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Event selectById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Event> selectAllEvent() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Event> selectEventByDept(int deptId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Event> selectEventByEmployee(int empId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean updateEvent(Event e) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteById(int id) {
		// TODO Auto-generated method stub
		return false;
	}

}
