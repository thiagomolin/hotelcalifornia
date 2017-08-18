package hotel.classes;

public class Usuario {
	
	long id;
	String dsUsuario, dsSenha;
	boolean admin;
	
	
	public Usuario(long id, String dsUsuario, String dsSenha, boolean admin) {
		this.id = id;
		this.dsUsuario = dsUsuario;
		this.dsSenha = dsSenha;
		this.admin = admin;
	}
	public Usuario(String dsUsuario, String dsSenha, boolean admin) {
		this.dsUsuario = dsUsuario;
		this.dsSenha = dsSenha;
		this.admin = admin;
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
	 * @return the dsUsuario
	 */
	public String getDsUsuario() {
		return dsUsuario;
	}


	/**
	 * @param dsUsuario the dsUsuario to set
	 */
	public void setDsUsuario(String dsUsuario) {
		this.dsUsuario = dsUsuario;
	}


	/**
	 * @return the dsSenha
	 */
	public String getDsSenha() {
		return dsSenha;
	}


	/**
	 * @param dsSenha the dsSenha to set
	 */
	public void setDsSenha(String dsSenha) {
		this.dsSenha = dsSenha;
	}


	/**
	 * @return the admin
	 */
	public String isAdmin() {
		if(admin) {
			return "1";
		}else {
			return "0";
		}

	}


	/**
	 * @param admin the admin to set
	 */
	public void setAdmin(boolean admin) {
		this.admin = admin;
	}
	
	

}
