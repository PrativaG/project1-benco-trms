package Benco.TRMS.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import Benco.TRMS.exception.TitleNotAvailableException;
import Benco.TRMS.pojos.Department;
import Benco.TRMS.pojos.Employee;
import Benco.TRMS.pojos.Event;
import Benco.TRMS.service.AuthServiceImpl;
import Benco.TRMS.service.EmployeeService;
import Benco.TRMS.service.EmployeeServiceImpl;
import io.javalin.http.Context;

public class EmployeeController {
	
	private EmployeeServiceImpl empServ= new EmployeeServiceImpl(); 
	
	private AuthServiceImpl auth = new AuthServiceImpl();

	
	public void createEmployee(Context ctx){
		String fname = ctx.formParam("firstName");
		String lname = ctx.formParam("lastName");
		String email = ctx.formParam("email");
		String password = ctx.formParam("password");
		String confirmPw = ctx.formParam("confirm-password");
		String contact = ctx.formParam("contact");
		Department dept = Department.valueOf(ctx.formParam("department").toUpperCase());
		String empPost = ctx.formParam("title");
		
		//checking for password match
		
		
		System.out.println(contact.length());
		
		Employee e = new Employee(fname, lname, dept, contact,  email, password, empPost);
		
//		if(!e.getPassword().equals(confirmPw)) {
//			
//			ctx.redirect("login.html", 422);
//			
//		}
//		if(e.getContact().length() < 10 || e.getContact().length() > 10) {
//			
//			System.out.println("checking phone length");
//			ctx.redirect("login.html");
//			
//		}
		
		Employee createdEmployee;
		try {
			
			createdEmployee = empServ.createEmployee(e);
			
		} catch (TitleNotAvailableException e1) {
			
			System.out.println("Title not available error");
			
			ctx.redirect("registration.html");
			return;
		}
		
		ctx.status(201);
		ctx.sessionAttribute("empId", createdEmployee.getEmployeeId());
		ctx.cookieStore("security", auth.createToken(email));
		
		if(e.getTitle().equals("General Employee")) {
			ctx.redirect("dashboard.html");
		}
		else {
			ctx.sessionAttribute("empDept", createdEmployee.getDepartment());
			ctx.redirect("approverDashboard.html");
		}
		
	}	
	
	public void getEmployeeById(Context ctx) {
		
		Employee e  = new Employee();
		
		if(ctx.sessionAttribute("empId") != null ) {
			
			int empId = ctx.sessionAttribute("empId");
			
			e = empServ.displayEmployeeById(empId);
			
			String d = String.valueOf(e.getDepartment());
			
			e.setDept(d);
			
			}
		
		
		if(Objects.nonNull(e)) ctx.json(e);
		
		else ctx.status(400);	}
	
}
