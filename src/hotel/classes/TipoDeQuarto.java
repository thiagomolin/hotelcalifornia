package hotel.classes;

public class TipoDeQuarto {
	private long id;
	private String dsTipoQuarto;
	
	public TipoDeQuarto(long id, String dsTipoQuarto) {
		this.setId(id);
		this.setDsTipoQuarto(dsTipoQuarto);
	}

	
	
	public String getDsTipoQuarto() {
		return dsTipoQuarto;
	}

	public void setDsTipoQuarto(String dsTipoQuarto) {
		this.dsTipoQuarto = dsTipoQuarto;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String toString() {
		return dsTipoQuarto;
	}
	
	
}
