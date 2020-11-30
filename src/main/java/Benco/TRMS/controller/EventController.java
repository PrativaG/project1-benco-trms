package Benco.TRMS.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import Benco.TRMS.pojos.Department;
import Benco.TRMS.pojos.Employee;
import Benco.TRMS.pojos.Event;
import Benco.TRMS.service.EmployeeServiceImpl;
import Benco.TRMS.service.EventServiceImpl;
import io.javalin.http.Context;

public class EventController {
	
	private EmployeeServiceImpl empServ = new EmployeeServiceImpl();
	private EventServiceImpl eventServ = new EventServiceImpl();
	
	public void createEvent(Context ctx) {
		
		String eventType = ctx.formParam("eventType");
		
		LocalDate startDate =LocalDate.parse( ctx.formParam( "startDate"));
		
		LocalDate endDate =LocalDate.parse( ctx.formParam( "endDate"));
		
		double cost = Double.parseDouble(ctx.formParam("cost"));
		
		String desc = ctx.formParam("description");
		
		LocalDate requestDate = LocalDate.now();
		
		int id = ctx.sessionAttribute("empId");
		Employee e = empServ.displayEmployeeById(id);
		
		Event ev = new Event(eventType, startDate, endDate, requestDate, cost, desc, e);
		eventServ.createEvent(ev);
		
		ctx.status(201);
		ctx.redirect("dashboard.html");
	}
	
	public void getAllEvents(Context ctx) {
		
		List<Event> eventList = eventServ.getAllEvents();
		ctx.json(eventList);
		
	}
	
	public void getAllEventsByEmployeeId(Context ctx) {
		
		List<Event> eventList = new ArrayList<>();
		
		if(ctx.sessionAttribute("empId") != null ) {
			
			int empId = ctx.sessionAttribute("empId");
			
			eventList = eventServ.getAllEventByEmployeeId(empId);
			
			}
		
		
		if(Objects.nonNull(eventList)) ctx.json(eventList);
		
		else ctx.status(400);
		
	}
	
	public void getEventByEventId(Context ctx) {
		
		int id = Integer.valueOf(ctx.pathParam("eventId"));
		
		Event ev = eventServ.getEventById(id);
		ctx.json(ev);
	}
	
	public void getEventByEmpDepartment(Context ctx) {
		
		List<Event> eventList = new ArrayList<>();
		
		if(ctx.sessionAttribute("empDept") != null ) {
			
			Department empDept = ctx.sessionAttribute("empDept");
			
			eventList = eventServ.getAllEventByEmployeeDept(empDept);
			
			System.out.println(empDept);
						
			}
		System.out.println(eventList.size());
		ctx.json(eventList);
	}
	
	public void deleteEvent(Context ctx) {
		
		int id = Integer.valueOf(ctx.pathParam("eventId"));
		
		eventServ.deleteEventById(id);
		
		ctx.redirect("dashboard.html");
	}
	
	public void updateEventFromEmployee(Context ctx) {
		
		int id = Integer.valueOf(ctx.pathParam("eventId"));
		double cost = Double.valueOf(ctx.formParam("cost"));
		String description = ctx.formParam("description");
		
		Event ev = new Event(id, cost, description);
		
		eventServ.updateEvent(ev);
		
		ctx.redirect("http://localhost:9090/dashboard.html");
	}
	
	public void updateEventFromApprover(Context ctx) {
		
		//fetching employee so that we can know title and approval status
		Employee e = empServ.displayEmployeeById(ctx.sessionAttribute("empId"));
		String empTitle = e.getTitle();
		
		int id = Integer.valueOf(ctx.pathParam("eventId"));
		
		//fetching event so that we do not loose prev values if we create new one
		Event ev = eventServ.getEventById(id);
		
		String approval = ctx.formParam("approval");
		
		if(approval.equals("Denied")) {
			String reason = ctx.formParam("reason");
			ev.setReason(reason);
		}
		
		if(empTitle.equals("Direct Supervisor")) {
			ev.setDsApproval(approval);
		}
		
		if(empTitle.equals("Department Head")) {
			ev.setHodApproval(approval);
		}
		
		if(empTitle.equals("Benefit Coordinator")) {
			ev.setCoordinatorApproval(approval);
		}
		
		eventServ.updateEventFromApprover(ev);
		
		ctx.redirect("http://localhost:9090/approverDashboard.html");
		
	}
	
	public void storeEventIdAndDisplay(Context ctx) {
		
		int id = Integer.valueOf(ctx.pathParam("eventId"));
		
		ctx.sessionAttribute("eventId", id);
		
		ctx.redirect("detailsOfEvent.html");
		
	}
	
}
