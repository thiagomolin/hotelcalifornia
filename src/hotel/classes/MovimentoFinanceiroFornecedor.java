package hotel.classes;

import java.time.LocalDate;

public class MovimentoFinanceiroFornecedor {
	long id;
	long fkFornecedor;
	long fkProduto;
	long fkUsuario;
	int nrQuantidade;
	LocalDate dtAtual;
		
	public MovimentoFinanceiroFornecedor(long id, long fkFornecedor, long fkProduto, long fkUsuario, int nrQuantidade,
			LocalDate dtAtual) {
		super();
		this.id = id;
		this.fkFornecedor = fkFornecedor;
		this.fkProduto = fkProduto;
		this.fkUsuario = fkUsuario;
		this.nrQuantidade = nrQuantidade;
		this.dtAtual = dtAtual;
	}
	
	public MovimentoFinanceiroFornecedor(long fkFornecedor, long fkProduto, long fkUsuario, int nrQuantidade,
			LocalDate dtAtual) {
		this.fkFornecedor = fkFornecedor;
		this.fkProduto = fkProduto;
		this.fkUsuario = fkUsuario;
		this.nrQuantidade = nrQuantidade;
		this.dtAtual = dtAtual;
	}
		
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getFkFornecedor() {
		return fkFornecedor;
	}
	public void setFkFornecedor(long fkFornecedor) {
		this.fkFornecedor = fkFornecedor;
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
