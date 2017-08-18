package hotel.classes;

public class Os {
	
	long id, fkFuncionario, fkQuarto, fkServico;
	String dsServico;
	
	
	public Os(long id, long fkFuncionario, long fkQuarto, long fkServico, String dsServico) {
		super();
		this.id = id;
		this.fkFuncionario = fkFuncionario;
		this.fkQuarto = fkQuarto;
		this.fkServico = fkServico;
		this.dsServico = dsServico;
	}
	public Os(long fkFuncionario, long fkQuarto, long fkServico, String dsServico) {
		this.fkFuncionario = fkFuncionario;
		this.fkQuarto = fkQuarto;
		this.fkServico = fkServico;
		this.dsServico = dsServico;
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
	 * @return the fkFuncionario
	 */
	public long getFkFuncionario() {
		return fkFuncionario;
	}
	/**
	 * @param fkFuncionario the fkFuncionario to set
	 */
	public void setFkFuncionario(long fkFuncionario) {
		this.fkFuncionario = fkFuncionario;
	}
	/**
	 * @return the fkQuarto
	 */
	public long getFkQuarto() {
		return fkQuarto;
	}
	/**
	 * @param fkQuarto the fkQuarto to set
	 */
	public void setFkQuarto(long fkQuarto) {
		this.fkQuarto = fkQuarto;
	}
	/**
	 * @return the fkServico
	 */
	public long getFkServico() {
		return fkServico;
	}
	/**
	 * @param fkServico the fkServico to set
	 */
	public void setFkServico(long fkServico) {
		this.fkServico = fkServico;
	}
	/**
	 * @return the dsServico
	 */
	public String getDsServico() {
		return dsServico;
	}
	/**
	 * @param dsServico the dsServico to set
	 */
	public void setDsServico(String dsServico) {
		this.dsServico = dsServico;
	}

}
