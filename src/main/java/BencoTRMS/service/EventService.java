package BencoTRMS.service;

import java.util.List;

import BencoTRMS.pojos.Department;
import BencoTRMS.pojos.Event;

public interface EventService {
	
	public Event createEvent(Event ev);
	
	public Event getEventById(int eventId);
	
	public Event updateEvent(Event ev);
	
	public Event updateEventFromApprover(Event ev);
	
	public List<Event> getAllEvents();
	
	public List<Event> getAllEventByEmployeeId(int empId);
	
	public List<Event> getAllEventByEmployeeDept(Department dept);
	
	public List<Event> getAllEventsByEmployeeTitle(String title);
	
	public boolean deleteEventById(int eventId);
}
