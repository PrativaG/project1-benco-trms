package Benco.TRMS.driver;

import Benco.TRMS.controller.AuthController;
import Benco.TRMS.controller.EmployeeController;
import Benco.TRMS.controller.EventController;
import io.javalin.Javalin;

public class ServerDriver {
	
	private static final String EMP_URL = "/employee";
	private static final String EVENT_URL = "/event";
	private static final String LOGIN_URL = "/login";


	private static EmployeeController empCont = new EmployeeController();
	private static EventController eventCont = new EventController();
	private static AuthController authCont = new AuthController();

	
	public static void main(String[] args) {
		
		Javalin app = Javalin.create(config -> {
			config.addStaticFiles("/public");
		}).start(9090);
		
		app.get("/hello", ctx -> ctx.html("Hello project 1 so so exciting!"));
		app.post(EMP_URL,  ctx -> empCont.createEmployee(ctx));
		
		//handling all event request
		app.post(EVENT_URL, ctx -> eventCont.createEvent(ctx));
//		app.get(EVENT_URL +"/:eventId", ctx -> eventCont.storeEventIdAndDisplay(ctx));
		app.get(EVENT_URL +"/:eventId", ctx -> eventCont.getEventByEventId(ctx));
		app.get(EVENT_URL, ctx -> eventCont.getAllEventsByEmployeeId(ctx));
		app.delete(EVENT_URL +"/:eventId", ctx -> eventCont.deleteEvent(ctx));
		app.post(EVENT_URL +"/:eventId", ctx -> eventCont.updateEventFromEmployee(ctx));
		
		//handling all login 
		app.post(LOGIN_URL, ctx -> authCont.login(ctx));
		app.get(LOGIN_URL, ctx -> authCont.checkUser(ctx));
	}

}