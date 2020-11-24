package Benco.TRMS.driver;

import Benco.TRMS.controller.EmployeeController;
import io.javalin.Javalin;

public class ServerDriver {
	
	private static final String EMP_URL = "/employee";
	private static final String EVENT_URL = "/event";

	private static EmployeeController empCont = new EmployeeController();
	
	public static void main(String[] args) {
		
		Javalin app = Javalin.create().start(9091);
		
		app.get("/hello", ctx -> ctx.html("Hello project 1 so so exciting!"));
		app.post(EMP_URL,  ctx -> empCont.createEmployee(ctx));
	}

}
