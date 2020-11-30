package Benco.TRMS.dao;

import java.util.List;

import Benco.TRMS.pojos.Department;
import Benco.TRMS.pojos.Event;

public interface EventDao {
	
	public Event insertEvent(Event e);
	
	public Event selectById(int id);
	
	public List<Event> selectAllEvent();
	
	public List<Event> selectEventByDept(Department dep);
	
	public List<Event> selectEventByEmployee(int empId);
	
	public boolean updateEventFromEmployee(Event e);
	
	public boolean updateEventFromApprover(Event e);
	
	public boolean deleteById(int id);

}
