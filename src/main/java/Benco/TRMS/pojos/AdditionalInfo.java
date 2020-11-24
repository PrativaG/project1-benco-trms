package Benco.TRMS.pojos;

public class AdditionalInfo<T> {
	
	private int id;
	
	private T requestBy;
	
	private T requestTo;
	
	private String info;
	
	private Reimbursement rem;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public T getRequestBy() {
		return requestBy;
	}

	public void setRequestBy(T requestBy) {
		this.requestBy = requestBy;
	}

	public T getRequestTo() {
		return requestTo;
	}

	public void setRequestTo(T requestTo) {
		this.requestTo = requestTo;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public Reimbursement getRem() {
		return rem;
	}

	public void setRem(Reimbursement rem) {
		this.rem = rem;
	}
	

}
