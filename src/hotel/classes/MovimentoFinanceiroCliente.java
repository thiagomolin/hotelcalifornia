package hotel.classes;

import java.time.LocalDate;

public class MovimentoFinanceiroCliente {
	long id;
	long fkReserva;
	long fkProduto;
	long fkUsuario;
	int nrQuantidade;
	LocalDate dtAtual;
		
	public MovimentoFinanceiroCliente(long id, long fkReserva, long fkProduto, long fkUsuario, int nrQuantidade,
			LocalDate dtAtual) {
		super();
		this.id = id;
		this.fkReserva = fkReserva;
		this.fkProduto = fkProduto;
		this.fkUsuario = fkUsuario;
		this.nrQuantidade = nrQuantidade;
		this.dtAtual = dtAtual;
	}
	
	public MovimentoFinanceiroCliente(long fkReserva, long fkProduto, long fkUsuario, int nrQuantidade,
			LocalDate dtAtual) {
		this.fkReserva = fkReserva;
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
	public long getFkReserva() {
		return fkReserva;
	}
	public void getFkReserva(long fkReserva) {
		this.fkReserva = fkReserva;
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
