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
		String contact = ctx.formParam("contact");
		Department dept = Department.valueOf(ctx.formParam("department").toUpperCase());
		String empPost = ctx.formParam("title");
		
		System.out.println(dept);
		System.out.println(empPost);
		
		Employee e = new Employee(fname, lname, dept, contact,  email, password, empPost);
		
		
		try {
			
			empServ.createEmployee(e);
			
		} catch (TitleNotAvailableException e1) {
			
			System.out.println("not available, from error");
			ctx.status(400);
			ctx.redirect("registration.html");
			return;
		}
		
		ctx.status(201);
		ctx.redirect("application.html");
	}
	
	
		
	
}
