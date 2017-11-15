package hotel.classes;

import java.util.ArrayList;
import java.util.List;

public class Fornecedor {

	private long id;
	private String nmFornecedor;
	private String nrCnpj;
	private String nrTelefone;
	private String dsEmail;
	private String dsEndereco;
	private long fkCidade;
	private long fkEstado;
	private long fkPais;

	public Fornecedor(String nmFornecedor, String nrCnpj, String nrTelefone, String dsEmail, String dsEndereco,
			long fkCidade, long fkEstado, long fkPais) {
		this.nmFornecedor = nmFornecedor;
		this.nrCnpj = nrCnpj;
		this.nrTelefone = nrTelefone;
		this.dsEmail = dsEmail;
		this.dsEndereco = dsEndereco;
		this.fkCidade = fkCidade;
		this.fkEstado = fkEstado;
		this.fkPais = fkPais;

	}

	public Fornecedor(long id, String nmFornecedor, String nrCnpj, String nrTelefone, String dsEmail, String dsEndereco,
			long fkCidade, long fkEstado, long fkPais) {
		this.id = id;
		this.nmFornecedor = nmFornecedor;
		this.nrCnpj = nrCnpj;
		this.nrTelefone = nrTelefone;
		this.dsEmail = dsEmail;
		this.dsEndereco = dsEndereco;
		this.fkCidade = fkCidade;
		this.fkEstado = fkEstado;
		this.fkPais = fkPais;

	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNmFornecedor() {
		return nmFornecedor;
	}

	public void setNmFornecedor(String nmFornecedor) {
		this.nmFornecedor = nmFornecedor;
	}

	public String getNrCnpj() {
		return nrCnpj;
	}

	public void setNrCnpj(String nrCnpj) {
		this.nrCnpj = nrCnpj;
	}

	public String getNrTelefone() {
		return nrTelefone;
	}

	public void setNrTelefone(String nrTelefone) {
		this.nrTelefone = nrTelefone;
	}

	public String getDsEmail() {
		return dsEmail;
	}

	public void setDsEmail(String dsEmail) {
		this.dsEmail = dsEmail;
	}

	public String getDsEndereco() {
		return dsEndereco;
	}

	public void setDsEndereco(String dsEndereco) {
		this.dsEndereco = dsEndereco;
	}

	public long getFkCidade() {
		return fkCidade;
	}

	public void setFkCidade(long fkCidade) {
		this.fkCidade = fkCidade;
	}

	public long getFkEstado() {
		return fkEstado;
	}

	public void setFkEstado(long fkEstado) {
		this.fkEstado = fkEstado;
	}

	public long getFkPais() {
		return fkPais;
	}

	public void setFkPais(long fkPais) {
		this.fkPais = fkPais;
	}

	public static List<String> getCampos() {
		List<String> lista = new ArrayList<String>();
		lista.add("Nome Do Fornecedor");
		lista.add("CNPJ");

		return lista;
	}

	public String toString() {

		return id + " - " + nmFornecedor;
	}

}
