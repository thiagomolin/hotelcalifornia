package hotel.classes;

import java.time.LocalDate;

public class ConsumoDaLocacao {
	
	private long id;
	private long fkLocacao;
	private long fkProduto;
	private long fkUsuario;
	private int nrQuantidade;
	private LocalDate dtAtual;
		
	public ConsumoDaLocacao(long id, long fkLocacao, long fkProduto, long fkUsuario, int nrQuantidade,
			LocalDate dtAtual) {
		super();
		this.id = id;
		this.fkLocacao = fkLocacao;
		this.fkProduto = fkProduto;
		this.fkUsuario = fkUsuario;
		this.nrQuantidade = nrQuantidade;
		this.dtAtual = dtAtual;
	}
	
	public ConsumoDaLocacao(long fkLocacao, long fkProduto, long fkUsuario, int nrQuantidade,
			LocalDate dtAtual) {
		this.fkLocacao = fkLocacao;
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
		return fkLocacao;
	}
	public void getFkReserva(long fkLocacao) {
		this.fkLocacao = fkLocacao;
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
