package BencoTRMS.service;

import BencoTRMS.dao.EventDaoImpl;
import BencoTRMS.dao.GradeDaoImpl;
import BencoTRMS.pojos.Event;
import BencoTRMS.pojos.Grade;

public class GradeServiceImpl implements GradeService {
	
	private GradeDaoImpl gradeDao = new GradeDaoImpl();
	
	@Override
	public Grade createGrade(Grade g) {
		
		return gradeDao.insertGrade(g);
	}

	@Override
	public Grade updateGradeById(Grade g) {
		
		return gradeDao.updateGrade(g);
	}

	@Override
	public Grade getGradeById(int gradeId) {
		
		return gradeDao.selectGradeById(gradeId);
	}

}
