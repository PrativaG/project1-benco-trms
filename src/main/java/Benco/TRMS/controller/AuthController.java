package Benco.TRMS.controller;

import Benco.TRMS.pojos.Employee;
import Benco.TRMS.service.AuthServiceImpl;
import Benco.TRMS.service.EmployeeServiceImpl;
import io.javalin.http.Context;

public class AuthController {
	
	AuthServiceImpl auth = new AuthServiceImpl();
	
	EmployeeServiceImpl empServ = new EmployeeServiceImpl();
	
	public void login(Context ctx) {
		String email = ctx.formParam("email");
		String password = ctx.formParam("password");
		boolean authenticated = auth.authenticateUser(email, password);
		if (authenticated) {
			//ctx.status(200);
			ctx.cookieStore("security", auth.createToken(email));
			
			//checking if the employee is applier or approver
			Employee emp = empServ.displayEmployeeByEmail(email);
			
			if(emp.getTitle().equals("General Employee")) {
				
				ctx.redirect("listEvent.html");
				
			}
			
			else {
				
				ctx.redirect("approve.html");
				
			}
			
		} else {
			//ctx.status(401);
			ctx.redirect("login.html?error=failed-login");
		}
	}
	
	public void checkUser(Context ctx) {
		System.out.println("Get request for login just made");
		ctx.html(auth.validateToken(ctx.cookieStore("security")));
	}

}
