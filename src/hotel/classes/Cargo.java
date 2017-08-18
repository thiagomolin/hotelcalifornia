package hotel.classes;

public class Cargo {
	
	long id;
	String dsCargo;
	
	
	public Cargo(long id, String dsCargo) {
		
		this.id = id;
		this.dsCargo = dsCargo;
	}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getDsCargo() {
		return dsCargo;
	}


	public void setDsCargo(String dsCargo) {
		this.dsCargo = dsCargo;
	}
	
	public String toString() {
		return dsCargo;		
	}
	

}
