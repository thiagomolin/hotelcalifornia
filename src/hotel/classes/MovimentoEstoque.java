package hotel.classes;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MovimentoEstoque {
	
	private long id;
	private long fkProduto;
	private long fkUsuario;
	private long fkTipoMovimento;
	private int nrQuantidade;
	private LocalDate dtAtual;
	
	public MovimentoEstoque(long id, long fkProduto, long fkUsuario, long fkTipoMovimento, int nrQuantidade,
			LocalDate dtAtual) {
		super();
		this.id = id;
		this.fkProduto = fkProduto;
		this.fkUsuario = fkUsuario;
		this.fkTipoMovimento = fkTipoMovimento;
		this.nrQuantidade = nrQuantidade;
		this.dtAtual = dtAtual;
	}
	
	public MovimentoEstoque(long fkProduto, long fkUsuario, long fkTipoMovimento, int nrQuantidade,
			LocalDate dtAtual) {
		this.fkProduto = fkProduto;
		this.fkUsuario = fkUsuario;
		this.fkTipoMovimento = fkTipoMovimento;
		this.nrQuantidade = nrQuantidade;
		this.dtAtual = dtAtual;
	}
	
	public static List<String> getCampos() {
		List<String> lista = new ArrayList<String>();
		lista.add("Produto");
		lista.add("Fornecedor");
		lista.add("Fornecedor");		
		return lista;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getFkProduto() {
		return fkProduto;
	}

	public void setFkProduto(long fkProduto) {
		this.fkProduto = fkProduto;
	}

	public long getFkUsuario() {
		return fkUsuario;
	}

	public void setFkUsuario(long fkUsuario) {
		this.fkUsuario = fkUsuario;
	}

	public long getFkTipoMovimento() {
		return fkTipoMovimento;
	}

	public void setFkTipoMovimento(long fkTipoMovimento) {
		this.fkTipoMovimento = fkTipoMovimento;
	}

	public int getNrQuantidade() {
		return nrQuantidade;
	}

	public void setNrQuantidade(int nrQuantidade) {
		this.nrQuantidade = nrQuantidade;
	}

	public LocalDate getDtAtual() {
		return dtAtual;
	}
	
	public java.sql.Date getDtAtualSQL() {
		return java.sql.Date.valueOf(dtAtual);
	}
	
	public void setDtAtual(LocalDate dtAtual) {
		this.dtAtual = dtAtual;
	}
}
