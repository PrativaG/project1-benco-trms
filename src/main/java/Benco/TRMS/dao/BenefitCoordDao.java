package Benco.TRMS.dao;

import Benco.TRMS.pojos.Coordinator;

public interface BenefitCoordDao {
	public Coordinator insertCoordinator(Coordinator c);
	
	public Coordinator selectById(int id);
	
	public boolean updateCoordinator(Coordinator c);
	
	public boolean deleteById(int id);
}
