package Benco.TRMS.pojos;

public class Reimbursement {
	
	private double amount;
	
	private String status; //Approved Rejected Pending
	
	private String reason;//for rejection and added amount
	
	private String additional_info_emp ;
	
	private String additional_info_hod ;
	
	private String additional_info_ds ;
	
	private Event event;

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}
	
	public String getAdditional_info_emp() {
		return additional_info_emp;
	}

	public void setAdditional_info_emp(String additional_info_emp) {
		this.additional_info_emp = additional_info_emp;
	}

	public String getAdditional_info_hod() {
		return additional_info_hod;
	}

	public void setAdditional_info_hod(String additional_info_hod) {
		this.additional_info_hod = additional_info_hod;
	}

	public String getAdditional_info_ds() {
		return additional_info_ds;
	}

	public void setAdditional_info_ds(String additional_info_ds) {
		this.additional_info_ds = additional_info_ds;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}
	
	
}
