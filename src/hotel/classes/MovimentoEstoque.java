package hotel.classes;

import java.time.LocalDate;

public class MovimentoEstoque {
	long id;
	long fkProduto;
	long fkUsuario;
	long fkTipoMovimento;
	int nrQuantidade;
	LocalDate dtAtual;
	
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
