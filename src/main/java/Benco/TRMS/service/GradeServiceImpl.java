package Benco.TRMS.service;

import Benco.TRMS.dao.EventDaoImpl;
import Benco.TRMS.dao.GradeDaoImpl;
import Benco.TRMS.pojos.Event;
import Benco.TRMS.pojos.Grade;

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
