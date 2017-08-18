package hotel.classes;

import java.util.ArrayList;
import java.util.List;

public class Cliente{

	long id;
	String nmCliente;
	String nrCpf;
	String nrTelefone;
	String dsEmail;
	String dsEndereco;
	long fkCidade;
	long fkEstado;
	long fkPais;

	public Cliente(long id, String nmCliente, String nrCpf, String nrTelefone, String dsEmail, String dsEndereco, long fkCidade, long fkEstado, long fkPais) {

		this.id = id;
		this.nmCliente = nmCliente;
		this.nrCpf = nrCpf;
		this.nrTelefone = nrTelefone;
		this.dsEmail = dsEmail;
		this.dsEndereco = dsEndereco;
		this.fkCidade = fkCidade;
		this.fkEstado = fkEstado;
		this.fkPais = fkPais;
	}
	public Cliente(String nmCliente, String nrCpf, String nrTelefone, String dsEmail, String dsEndereco, long fkCidade, long fkEstado, long fkPais) {
		this.nmCliente = nmCliente;
		this.nrCpf = nrCpf;
		this.nrTelefone = nrTelefone;
		this.dsEmail = dsEmail;
		this.dsEndereco = dsEndereco;
		this.fkCidade = fkCidade;
		this.fkEstado = fkEstado;
		this.fkPais = fkPais;
	}
	
	public static List<String> getCampos(){
		List<String> lista = new ArrayList<String>();
		lista.add("Nome Do Cliente");
		lista.add("CPF");
		
		return lista;
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
	 * @return the nmCliente
	 */
	public String getNmCliente() {
		return nmCliente;
	}

	/**
	 * @param nmCliente the nmCliente to set
	 */
	public void setNmCliente(String nmCliente) {
		this.nmCliente = nmCliente;
	}

	/**
	 * @return the nrCpf
	 */
	public String getNrCpf() {
		return nrCpf;
	}

	/**
	 * @param nrCpf the nrCpf to set
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
	 * @param nrTelefone the nrTelefone to set
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
	 * @param dsEmail the dsEmail to set
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
	 * @param dsEndereco the dsEndereco to set
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
	 * @param fkCidade the fkCidade to set
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
	 * @param fkEstado the fkEstado to set
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
	 * @param fkPais the fkPais to set
	 */
	public void setFkPais(long fkPais) {
		this.fkPais = fkPais;
	}
	
	public String toString() {
		
		return id +" - " + nmCliente;
	}
}
