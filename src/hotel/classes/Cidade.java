package hotel.classes;

public class Cidade {

	long id;
	String dsCidade;

	public Cidade(long id, String dsCidade) {
		this.id = id;
		this.dsCidade = dsCidade;
	}

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return the dsCidade
	 */
	public String getDsCidade() {
		return dsCidade;
	}

	/**
	 * @param dsCidade the dsCidade to set
	 */
	public void setDsCidade(String dsCidade) {
		this.dsCidade = dsCidade;
	}
	public String toString() {
		return dsCidade;		
	}
	

}
