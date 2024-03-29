package BencoTRMS;

import BencoTRMS.controller.AuthController;
import BencoTRMS.controller.EmployeeController;
import BencoTRMS.controller.EventController;
import BencoTRMS.controller.GradeController;
import io.javalin.Javalin;
import io.javalin.http.staticfiles.Location;

public class ServerDriver {
	
	private static final String EMP_URL = "/employee";
	private static final String EVENT_URL = "/event";
	private static final String LOGIN_URL = "/login";

	private static EmployeeController empCont = new EmployeeController();
	private static EventController eventCont = new EventController();
	private static AuthController authCont = new AuthController();
	private static GradeController gradeCont = new GradeController();
	
	public static void main(String[] args) {
		
		Javalin app = Javalin.create(config -> {
			config.addStaticFiles("/public");
		}).start("13.77.150.135", 9090);
		
		app.get("/hello", ctx -> ctx.html("Hello project 1 so so exciting!"));
		app.post(EMP_URL,  ctx -> empCont.createEmployee(ctx));
		app.get(EMP_URL,  ctx -> empCont.getEmployeeById(ctx));

		
		//handling all event request
		app.post(EVENT_URL, ctx -> eventCont.createEvent(ctx));
		app.get(EVENT_URL +"/dashboard", ctx -> eventCont.getEventByEmpDepartment(ctx));
		app.get(EVENT_URL +"/:eventId",  ctx -> { if(authCont.checkUser(ctx)) { eventCont.getEventByEventId(ctx); } 
        								else ctx.redirect("employee-login.html");});
		
		app.get(EVENT_URL, ctx ->  eventCont.getAllEventsByEmployeeId(ctx));
		
		app.delete(EVENT_URL +"/:eventId", ctx -> eventCont.deleteEvent(ctx));
		app.post(EVENT_URL +"/:eventId", ctx -> eventCont.updateEventFromEmployee(ctx));
		app.post(EVENT_URL +"/dashboard/:eventId", ctx -> eventCont.updateEventFromApprover(ctx));
		app.post(EVENT_URL +"/addMoney/:eventId", ctx -> eventCont.addMoneytoReimbursement(ctx));
		
		//handling all login and logout requests
		app.post(LOGIN_URL, ctx -> authCont.login(ctx));
		app.get(LOGIN_URL, ctx -> authCont.checkUser(ctx));
		app.post("/logout", ctx -> {authCont.logout(ctx);});
		
		//handling all grade requests
		app.post("/grade", ctx -> gradeCont.createGrade(ctx));
		app.post("/grade/:gradeId" , ctx -> gradeCont.updateGrade(ctx));
	}

	
}
