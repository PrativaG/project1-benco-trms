package BencoTRMS.dao;

import java.util.List;

import BencoTRMS.pojos.Department;
import BencoTRMS.pojos.Event;

public interface EventDao {
	
	public Event insertEvent(Event e);
	
	public Event selectById(int id);
	
	public List<Event> selectAllEvent();
	
	public List<Event> selectEventByDept(Department dep);
	
	public List<Event> selectEventByEmployee(int empId);
	
	public List<Event> selectEventByTtitle(String title);
	
	public boolean updateEventFromEmployee(Event e);
	
	public boolean updateEventFromApprover(Event e);
	
	public boolean deleteById(int id);

}
