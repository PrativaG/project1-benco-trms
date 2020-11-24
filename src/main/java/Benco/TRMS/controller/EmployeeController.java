package Benco.TRMS.controller;

import java.util.List;

import Benco.TRMS.pojos.Department;
import Benco.TRMS.pojos.Employee;
import Benco.TRMS.service.EmployeeService;
import Benco.TRMS.service.EmployeeServiceImpl;
import io.javalin.http.Context;

public class EmployeeController {
	
	private EmployeeServiceImpl empServ= new EmployeeServiceImpl(); 
	
	public void createEmployee(Context ctx) {
		String fname = ctx.formParam("first-name");
		String lname = ctx.formParam("last-name");
		String email = ctx.formParam("email");
		String password = ctx.formParam("password");
		String contact = ctx.formParam("contact");
		Department dept = Department.valueOf(ctx.formParam("department").toUpperCase());
		
		System.out.println(dept);
		
		Employee e = new Employee(fname, lname, dept, contact,  email, password);
				
		empServ.createEmployee(e);
		
		ctx.status(201);
	}
	
	
		
	
}
