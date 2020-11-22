package Benco.TRMS.dao;

import java.util.List;

import Benco.TRMS.pojos.Reimbursement;

public interface ReimbursementDao {
	
	public Reimbursement insertReimbursement(Reimbursement r);
	
	public List<Reimbursement> selectByEmployee(int empId);
	
	public List<Reimbursement> selectByDept(int deptId);
	
	public List<Reimbursement> selectAll();
	
	public boolean updateReimbursement(Reimbursement reimbursement);
	
	public boolean deleteReimbursement(int remId);

}
