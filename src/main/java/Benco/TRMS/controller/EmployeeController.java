package Benco.TRMS.controller;

import java.util.List;

import Benco.TRMS.exception.TitleNotAvailableException;
import Benco.TRMS.pojos.Department;
import Benco.TRMS.pojos.Employee;
import Benco.TRMS.service.EmployeeService;
import Benco.TRMS.service.EmployeeServiceImpl;
import io.javalin.http.Context;

public class EmployeeController {
	
	private EmployeeServiceImpl empServ= new EmployeeServiceImpl(); 
	
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
		
		if(!e.getPassword().equals(confirmPw)) {
			
			ctx.redirect("login.html", 422);
			
		}
		if(e.getContact().length() < 10 || e.getContact().length() > 10) {
			
			System.out.println("checking phone length");
			ctx.redirect("login.html");
			
		}
		
		Employee createdEmployee;
		try {
			
			createdEmployee = empServ.createEmployee(e);
			
		} catch (TitleNotAvailableException e1) {
			
			System.out.println("Title not available error");
			
			ctx.redirect("registration.html", 400);
			return;
		}
		
		ctx.status(201);
		ctx.sessionAttribute("empId", createdEmployee.getEmployeeId());
		
		if(e.getTitle().equals("General Employee")) {
			ctx.redirect("dashboard.html");
		}
		else {
			ctx.redirect("approverDashboard.html");
		}
		
	}	
	
}
