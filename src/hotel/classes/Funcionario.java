package hotel.classes;

import java.util.ArrayList;
import java.util.List;

public class Funcionario {

	private long id;
	private String nmFuncionario;
	private String nrCpf;
	private String nrTelefone;
	private String dsEmail;
	private String dsEndereco;
	private long fkCidade;
	private long fkEstado;
	private long fkPais;
	private long fkCargo;

	public Funcionario(String nmFuncionario, long fkCargo, String nrCpf, String nrTelefone, String dsEmail, String dsEndereco,
			long fkCidade, long fkEstado, long fkPais) {
		this.nmFuncionario = nmFuncionario;
		this.nrCpf = nrCpf;
		this.nrTelefone = nrTelefone;
		this.dsEmail = dsEmail;
		this.dsEndereco = dsEndereco;
		this.fkCidade = fkCidade;
		this.fkEstado = fkEstado;
		this.fkPais = fkPais;
		this.fkCargo = fkCargo;
	}

	public Funcionario(long id, String nmFuncionario, long fkCargo, String nrCpf, String nrTelefone, String dsEmail,
			String dsEndereco, long fkCidade, long fkEstado, long fkPais) {
		this.id = id;
		this.nmFuncionario = nmFuncionario;
		this.nrCpf = nrCpf;
		this.nrTelefone = nrTelefone;
		this.dsEmail = dsEmail;
		this.dsEndereco = dsEndereco;
		this.fkCidade = fkCidade;
		this.fkEstado = fkEstado;
		this.fkPais = fkPais;
		this.fkCargo = fkCargo;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return the nmFuncionario
	 */
	public String getNmFuncionario() {
		return nmFuncionario;
	}

	/**
	 * @param nmFuncionario
	 *            the nmFuncionario to set
	 */
	public void setNmFuncionario(String nmFuncionario) {
		this.nmFuncionario = nmFuncionario;
	}

	/**
	 * @return the nrCpf
	 */
	public String getNrCpf() {
		return nrCpf;
	}

	/**
	 * @param nrCpf
	 *            the nrCpf to set
	 */
	public void setNrCpf(String nrCpf) {
		this.nrCpf = nrCpf;
	}

	/**
	 * @return the nrTelefone
	 */
	public String getNrTelefone() {
		return nrTelefone;
	}

	/**
	 * @param nrTelefone
	 *            the nrTelefone to set
	 */
	public void setNrTelefone(String nrTelefone) {
		this.nrTelefone = nrTelefone;
	}

	/**
	 * @return the dsEmail
	 */
	public String getDsEmail() {
		return dsEmail;
	}

	/**
	 * @param dsEmail
	 *            the dsEmail to set
	 */
	public void setDsEmail(String dsEmail) {
		this.dsEmail = dsEmail;
	}

	/**
	 * @return the dsEndereco
	 */
	public String getDsEndereco() {
		return dsEndereco;
	}

	/**
	 * @param dsEndereco
	 *            the dsEndereco to set
	 */
	public void setDsEndereco(String dsEndereco) {
		this.dsEndereco = dsEndereco;
	}

	/**
	 * @return the fkCidade
	 */
	public long getFkCidade() {
		return fkCidade;
	}

	/**
	 * @param fkCidade
	 *            the fkCidade to set
	 */
	public void setFkCidade(long fkCidade) {
		this.fkCidade = fkCidade;
	}

	/**
	 * @return the fkEstado
	 */
	public long getFkEstado() {
		return fkEstado;
	}

	/**
	 * @param fkEstado
	 *            the fkEstado to set
	 */
	public void setFkEstado(long fkEstado) {
		this.fkEstado = fkEstado;
	}

	/**
	 * @return the fkPais
	 */
	public long getFkPais() {
		return fkPais;
	}

	/**
	 * @param fkPais
	 *            the fkPais to set
	 */
	public void setFkPais(long fkPais) {
		this.fkPais = fkPais;
	}

	/**
	 * @return the fkCargo
	 */
	public long getFkCargo() {
		return fkCargo;
	}

	/**
	 * @param fkCargo
	 *            the fkCargo to set
	 */
	public void setFkCargo(long fkCargo) {
		this.fkCargo = fkCargo;
	}

	public static List<String> getCampos() {
		List<String> lista = new ArrayList<String>();
		lista.add("Nome Do Funcionário");
		lista.add("CPF");

		return lista;
	}

	public String toString() {

		return id + " - " + nmFuncionario;
	}

}
