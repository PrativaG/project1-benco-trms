package BencoTRMS.service;

import java.util.List;

import BencoTRMS.dao.EventDaoImpl;
import BencoTRMS.pojos.Department;
import BencoTRMS.pojos.Employee;
import BencoTRMS.pojos.Event;

public class EventServiceImpl implements EventService {
	
	private EventDaoImpl eventDao = new EventDaoImpl();
	
	private EmployeeServiceImpl empServ = new EmployeeServiceImpl();
	
	@Override
	public Event createEvent(Event ev) {
		
		double eligibleAmt = calculateEligibleAmount(ev);
		
		if(ev.getEmp().getRemainingClaimAmt() < eligibleAmt) {
			eligibleAmt = eligibleAmt - ev.getEmp().getRemainingClaimAmt();
		}
		
		ev.setEligibleAmount(eligibleAmt);
		
		//updating employee available claim amount due to this event request
		Employee updateClaimAmtEmployee = ev.getEmp();
		double afterRequest = updateClaimAmtEmployee.getRemainingClaimAmt() - eligibleAmt;
	
		updateClaimAmtEmployee.setRemainingClaimAmt( afterRequest );
		empServ.updateEmployee(ev.getEmp());
		
		return eventDao.insertEvent(ev);
	}
	
	//from applier side
	@Override
	public Event updateEvent(Event ev) {
		
		double eligibleAmt = calculateEligibleAmount(ev);
		
		if(ev.getEmp().getRemainingClaimAmt() < eligibleAmt) {
			eligibleAmt = eligibleAmt - ev.getEmp().getRemainingClaimAmt();
		}
		
		ev.setEligibleAmount(eligibleAmt);
		
		//updating employee available claim amount due to this event request
		Employee updateClaimAmtEmployee = ev.getEmp();
		double afterRequest = updateClaimAmtEmployee.getRemainingClaimAmt() - eligibleAmt;
	
		updateClaimAmtEmployee.setRemainingClaimAmt( afterRequest );
		empServ.updateEmployee(ev.getEmp());
		
		
		if(eventDao.updateEventFromEmployee(ev)) return ev;
		
		return null;
	}
	
	@Override
	public Event updateEventFromApprover(Event ev) {
		
		// adding the eligible amount of the reimbursement to employee total claim if the request is denied
			if(ev.getCoordinatorApproval().equals("Denied")
				|| ev.getDsApproval().equals("Denied")
				|| ev.getHodApproval().equals("Denied")) {
							
				Employee updateClaimAmtEmployee = ev.getEmp();
				
				double afterRequest = updateClaimAmtEmployee.getRemainingClaimAmt() + ev.getEligibleAmount();

				updateClaimAmtEmployee.setRemainingClaimAmt( afterRequest );
				empServ.updateEmployee(updateClaimAmtEmployee);
			}
			
		Event notUpdatedEvent = eventDao.selectById(ev.getId());
		notUpdatedEvent.setCost(ev.getCost());
		
		double eligibleAmt = calculateEligibleAmount(notUpdatedEvent);
		
		ev.setEligibleAmount(eligibleAmt);
		
		if(eventDao.updateEventFromApprover(ev)) return ev;
		
		return null;
	}


	@Override
	public List<Event> getAllEventByEmployeeId(int empId) {
		
		return eventDao.selectEventByEmployee(empId);
	}

	@Override
	public boolean deleteEventById(int eventId) {
		
		if(eventDao.deleteById(eventId)) return true;
		return false;
	}

	@Override
	public List<Event> getAllEvents() {
		
		return eventDao.selectAllEvent();
	}

	@Override
	public Event getEventById(int eventId) {
		
		return eventDao.selectById(eventId);
	}
	
	public double calculateEligibleAmount(Event ev) {
		double coverageRate = 0.0 ;
		 
		switch(ev.getType()) {
			
			case "University":
				coverageRate = 0.80;
				break;
				
			case "Seminar":
				coverageRate = 0.60;
				break;
			
			case "Certification Preparation":
				coverageRate = 0.75;
				break;
				
			case "Certification":
				coverageRate = 1;
				break;
			
			case "Technical Training":
				coverageRate = 0.90;
				break;
				
			case "Others":
				coverageRate = 0.30;
				break;
			
		}
			
		//setting eligile amount on the basis of current available claim and coverage rate
		double eligibleAmt =  ev.getCost() * coverageRate;
		
		return eligibleAmt;
	}

	@Override
	public List<Event> getAllEventByEmployeeDept(Department dept) {
		
		return eventDao.selectEventByDept(dept);
	}

	@Override
	public List<Event> getAllEventsByEmployeeTitle(String title) {
		
		return eventDao.selectEventByTtitle(title);
	}
	
	public Event addMoney(Event e) {
		if(eventDao.updateEventFromEmployee(e)) return e;
		return null;
	}
	
}
