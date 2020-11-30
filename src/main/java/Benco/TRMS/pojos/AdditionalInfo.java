package Benco.TRMS.pojos;

public class AdditionalInfo {
	
	private int infoId;
	
	private int dsId;
	
	private int hodId;
	
	private int bcId;
	
	private String infoByEmp;
	
	private String infoByDS;
	
	private String infoByHod;
			
	private Event event ;

	public int getInfoId() {
		return infoId;
	}

	public void setInfoId(int infoId) {
		this.infoId = infoId;
	}

	public int getDsId() {
		return dsId;
	}

	public void setDsId(int dsId) {
		this.dsId = dsId;
	}

	public int getHodId() {
		return hodId;
	}

	public void setHodId(int hodId) {
		this.hodId = hodId;
	}

	public int getBcId() {
		return bcId;
	}

	public void setBcId(int bcId) {
		this.bcId = bcId;
	}

	public String getInfoByEmp() {
		return infoByEmp;
	}

	public void setInfoByEmp(String infoByEmp) {
		this.infoByEmp = infoByEmp;
	}

	public String getInfoByDS() {
		return infoByDS;
	}

	public void setInfoByDS(String infoByDS) {
		this.infoByDS = infoByDS;
	}

	public String getInfoByHod() {
		return infoByHod;
	}

	public void setInfoByHod(String infoByHod) {
		this.infoByHod = infoByHod;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

}
