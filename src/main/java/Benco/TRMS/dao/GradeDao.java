package Benco.TRMS.dao;

import Benco.TRMS.pojos.Grade;

public interface GradeDao {
	
	public Grade insertGrade(Grade g);
	
	public Grade updateGrade(Grade g);
	
	public Grade selectGradeById(int id);
	
	public Grade selectGradeByEventId(int eventId);
}
