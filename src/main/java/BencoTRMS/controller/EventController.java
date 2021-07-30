package BencoTRMS.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import BencoTRMS.pojos.Department;
import BencoTRMS.pojos.Employee;
import BencoTRMS.pojos.Event;
import BencoTRMS.service.EmployeeServiceImpl;
import BencoTRMS.service.EventServiceImpl;
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
		
		if(e.getRemainingClaimAmt() == 0) {
			
			ctx.redirect("application.html");
		}
		
		Event ev = new Event(eventType, startDate, endDate, requestDate, cost, desc, e);
		eventServ.createEvent(ev);
		
		ctx.status(201);
		if(e.getTitle().equals("General Employee")) {
			ctx.redirect("dashboard.html");
		}else {
			ctx.redirect("approverDashboard.html");
		}
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
			
			//if coordinator then display event of coordinator of other department
			
			int id = ctx.sessionAttribute("empId");
			Employee e = empServ.displayEmployeeById(id);
			
			if(e.getTitle().equals("Benefit Coordinator")) {
				List<Event> eventOfAllBC = eventServ.getAllEventsByEmployeeTitle(e.getTitle());
				
				if(eventOfAllBC != null) {
					for(Event ev :eventOfAllBC) {
						eventList.add(ev);
					}
				}
			}
						
			}
		
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
		
		Event ev = eventServ.getEventById(id);
		ev.setCost(cost);
		ev.setDescription(description);
		
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
	
	public void addMoneytoReimbursement(Context ctx) {
		
		int id = Integer.valueOf(ctx.pathParam("eventId"));
		double addedAmount = Integer.valueOf(ctx.formParam("extraAmount"));
		
		Event e = eventServ.getEventById(id);
		e.setEligibleAmount(addedAmount + e.getEligibleAmount());
		
		eventServ.addMoney(e);
		
		ctx.redirect("http://localhost:9090/approverDashboard.html");

	}
	
	public void storeEventIdAndDisplay(Context ctx) {
		
		int id = Integer.valueOf(ctx.pathParam("eventId"));
		
		ctx.sessionAttribute("eventId", id);
		
		ctx.redirect("detailsOfEvent.html");
		
	}
	
}
