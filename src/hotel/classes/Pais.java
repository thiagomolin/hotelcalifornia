package hotel.classes;

public class Pais {
	
	private long id;
	private String dsPais;
	
	
	public Pais(long id, String dsPais) {
		
		this.id = id;
		this.dsPais = dsPais;
	}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getDsPais() {
		return dsPais;
	}


	public void setDsPais(String dsPais) {
		this.dsPais = dsPais;
	}
	
	public String toString() {
		return dsPais;		
	}
	

}
