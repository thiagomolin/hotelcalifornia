package hotel.classes;

public class Status {
	
	private long id;
	private String dsStatus;
	
	
	public Status(long id, String dsStatus) {
		
		this.id = id;
		this.dsStatus = dsStatus;
	}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getDsStatus() {
		return dsStatus;
	}


	public void setDsStatus(String dsCargo) {
		this.dsStatus = dsCargo;
	}
	
	public String toString() {
		return dsStatus;		
	}
	

}
