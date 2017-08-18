package hotel.classes;

public class Estado {

	long id;
	long fkPais;
	String dsEstado;
	
	public Estado(long id, long fkPais, String dsEstado) {
		this.id = id;
		this.dsEstado = dsEstado;
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
	 * @return the fkPais
	 */
	public long getFkPais() {
		return fkPais;
	}

	/**
	 * @param fkPais the fkPais to set
	 */
	public void setFkPais(long fkPais) {
		this.fkPais = fkPais;
	}

	/**
	 * @return the dsEstado
	 */
	public String getDsEstado() {
		return dsEstado;
	}

	/**
	 * @param dsEstado the dsEstado to set
	 */
	public void setDsEstado(String dsEstado) {
		this.dsEstado = dsEstado;
	}
	
	public String toString() {
		return dsEstado;		
	}
	
}
