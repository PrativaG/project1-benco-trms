package Benco.TRMS.service;

import java.util.List;

import Benco.TRMS.pojos.Event;

public interface EventService {
	
	public Event createEvent(Event ev);
	
	public Event getEventById(int eventId);
	
	public Event updateEvent(Event ev);
	
	public List<Event> getAllEvents();
	
	public List<Event> getAllEventByEmployeeId(int empId);
	
	public boolean deleteEventById(int eventId);
}
