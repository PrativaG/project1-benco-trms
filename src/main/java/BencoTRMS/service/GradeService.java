package BencoTRMS.service;

import BencoTRMS.pojos.Grade;

public interface GradeService {
	
	public Grade createGrade(Grade g);
	
	public Grade updateGradeById(Grade g);
	
	public Grade getGradeById(int gradeId);

}
