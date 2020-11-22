package Benco.TRMS.dao;

import Benco.TRMS.pojos.DirectSupervisor;

public interface DirectSupervisorDao {
	
	public DirectSupervisor insertDirectSupervisor(DirectSupervisor ds);
	
	public DirectSupervisor selectById(int dsId);
	
	public boolean updateDirectSupervisor(DirectSupervisor ds);
	
	public boolean deleteById(int id);
}
