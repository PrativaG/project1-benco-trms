package Benco.TRMS.dao;

import Benco.TRMS.pojos.HOD;

public interface HODDao {
	
	public HOD insertHod(HOD hod);
	
	public HOD selectById(int hodId);
	
	public boolean updateHod(HOD hod);
	
	public boolean deleteById(int hodId);
}
