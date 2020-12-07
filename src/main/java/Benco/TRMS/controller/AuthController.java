package Benco.TRMS.controller;

import Benco.TRMS.pojos.Employee;
import Benco.TRMS.service.AuthServiceImpl;
import Benco.TRMS.service.EmployeeServiceImpl;
import io.javalin.http.Context;

public class AuthController {
	
	AuthServiceImpl auth = new AuthServiceImpl();
	
	EmployeeServiceImpl empServ = new EmployeeServiceImpl();
	
	public boolean logout(Context ctx) {
		
		try {
			ctx.clearCookieStore();
			 ctx.redirect("login.html");
			return true;
		}catch(Exception e) {
			
			e.printStackTrace();
			
		}
		return false;

	}
	
	public void login(Context ctx) {
		String email = ctx.formParam("email");
		String password = ctx.formParam("password");
		boolean authenticated = auth.authenticateUser(email, password);
		if (authenticated) {
			//ctx.status(200);
			ctx.cookieStore("security", auth.createToken(email));
			
			//checking if the employee is applier or approver
			Employee emp = empServ.displayEmployeeByEmail(email);
			
			ctx.sessionAttribute("empId", emp.getEmployeeId());
			
			if(emp.getTitle().equals("General Employee")) {		
				
				ctx.redirect("dashboard.html");
				
			}
			
			else {
				
				ctx.sessionAttribute("empDept", emp.getDepartment());
				ctx.redirect("approverDashboard.html");

//				if(emp.getTitle().equals("Direct Supervisor"))
//					ctx.redirect("dsDashboard.html");
//				
//				if(emp.getTitle().equals("Department Head"))
//					ctx.redirect("hodDashboard.html");
//				
//				if(emp.getTitle().equals("Benefit Coordinator"))
//					ctx.redirect("bcDashboard.html");
			}
			
		} else {
			//ctx.status(401);
			ctx.redirect("login.html?error=failed-login");
		}
	}
	
	public boolean checkUser(Context ctx) {
		
		boolean result = auth.validateToken(ctx.cookieStore("security"));
		return result;
		
	}
	
	

}
