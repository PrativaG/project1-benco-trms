package Benco.TRMS.service;

import java.util.List;

import Benco.TRMS.dao.EventDaoImpl;
import Benco.TRMS.pojos.Event;

public class EventServiceImpl implements EventService {
	
	private EventDaoImpl eventDao = new EventDaoImpl();
	
	@Override
	public Event createEvent(Event ev) {
		
		return eventDao.insertEvent(ev);
	}

	@Override
	public Event updateEvent(Event ev) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Event> getAllEventByEmployeeId(int empId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteEventById(int eventId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Event> getAllEvents() {
		
		return eventDao.selectAllEvent();
	}

}
