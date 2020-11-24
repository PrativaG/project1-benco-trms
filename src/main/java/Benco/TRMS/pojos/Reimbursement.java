package Benco.TRMS.pojos;

public class Reimbursement {
	
	private double amount;
		
	private String reason;//for rejection and added amount
		
	private Event event;
	
	private String dsApproval;
	
	private String coordinatorApproval;
	
	private String hodApproval;

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public String getDsAPproval() {
		return dsApproval;
	}

	public void setDsAPproval(String dsAPproval) {
		this.dsApproval = dsAPproval;
	}

	public String getCoordinatorApproval() {
		return coordinatorApproval;
	}

	public void setCoordinatorApproval(String coordinatorApproval) {
		this.coordinatorApproval = coordinatorApproval;
	}

	public String getHodApproval() {
		return hodApproval;
	}

	public void setHodApproval(String hodApproval) {
		this.hodApproval = hodApproval;
	}
	
	
}
