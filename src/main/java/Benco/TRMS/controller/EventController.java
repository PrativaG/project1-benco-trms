package Benco.TRMS.controller;

import java.time.LocalDate;
import java.util.List;

import Benco.TRMS.pojos.Event;
import Benco.TRMS.service.EventServiceImpl;
import io.javalin.http.Context;

public class EventController {
	
	private EventServiceImpl eventServ = new EventServiceImpl();
	
	public void createEvent(Context ctx) {
		String eventType = ctx.formParam("eventType");
		LocalDate startDate =LocalDate.parse( ctx.formParam( "startDate"));
		LocalDate endDate =LocalDate.parse( ctx.formParam( "endDate"));
		double cost = Double.parseDouble(ctx.formParam("cost"));
		LocalDate requestDate = LocalDate.now();
		
		Event ev = new Event(eventType, startDate, endDate, requestDate, cost );
		eventServ.createEvent(ev);
		
		ctx.status(201);
	}
	
	public void getAllEvents(Context ctx) {
		
		List<Event> eventList = eventServ.getAllEvents();
		ctx.json(eventList);
		
	}
	
	public void getAllEventsByEmployeeId(Context ctx) {
		
		int empId = Integer.parseInt(ctx.pathParam("empId"));
		
		List<Event> eventList = eventServ.getAllEventByEmployeeId(empId);
		
		ctx.json(eventList);
		
	}
	
	
}
