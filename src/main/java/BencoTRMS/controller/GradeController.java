package BencoTRMS.controller;

import java.awt.image.BufferedImage;
import org.apache.commons.io.IOUtils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import BencoTRMS.dao.EventDaoImpl;
import BencoTRMS.pojos.Event;
import BencoTRMS.pojos.Grade;
import BencoTRMS.service.EventServiceImpl;
import BencoTRMS.service.GradeServiceImpl;
import io.javalin.http.Context;

public class GradeController {
	
	private static GradeServiceImpl gradeServ = new GradeServiceImpl();
	private static EventServiceImpl eventServ = new EventServiceImpl();
	private static EventDaoImpl eventDao = new EventDaoImpl();
	
	public void createGrade(Context ctx) {
		
		String type = ctx.formParam("type");
		int eventId  = Integer.valueOf( ctx.formParam("event-id"));
		byte[] gradeFile = null;
		
		try {
			
			if(ctx.uploadedFile("grade_file") != null) {
				
				gradeFile = ctx.uploadedFile("grade_file").getContent().readAllBytes();
				System.out.println(gradeFile);
			}else {
				
				ctx.redirect("addGrade.html");
			}
					
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(gradeFile);
		
		Grade g = new Grade();
		g.setFile(gradeFile);
		g.setType(type);
		g.setStatus("Pending");
		
		Grade newGrade = gradeServ.createGrade(g);
		
		//updating event since grade is being added to the event
		Event e = eventServ.getEventById(eventId);
		e.setGrade(newGrade);
		eventDao.addGradetoEvent(e);
		
		ctx.redirect("dashboard.html");
	}
	
	public void updateGrade(Context ctx) {
		
		int gradeId = Integer.valueOf(ctx.pathParam("gradeId"));
		String status = ctx.formParam("approval");
		
		Grade g = gradeServ.getGradeById(gradeId);
		g.setStatus(status);
		
		gradeServ.updateGradeById(g);
		
		ctx.redirect("http://localhost:9090/approverDashboard.html");
	}
	
	
}
