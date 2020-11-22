package Benco.TRMS.dao;

import java.util.List;

import Benco.TRMS.pojos.Event;

public interface EventDao {
	
	public Event insertEvent(Event e);
	
	public Event selectById(int id);
	
	public List<Event> selectAllEvent();
	
	public List<Event> selectEventByDept(int deptId);
	
	public List<Event> selectEventByEmployee(int empId);
	
	public boolean updateEvent(Event e);
	
	public boolean deleteById(int id);

}
