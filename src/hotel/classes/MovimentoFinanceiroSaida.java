package hotel.classes;

import java.time.LocalDate;

public class MovimentoFinanceiroSaida {

	private long id;
	private long fkProduto;
	private long fkUsuario;
	private float nrValor;
	private LocalDate dtAtual;

	public MovimentoFinanceiroSaida(long id, long fkProduto, long fkUsuario, float nrValor, LocalDate dtAtual) {
		super();
		this.id = id;
		this.fkProduto = fkProduto;
		this.fkUsuario = fkUsuario;
		this.nrValor = nrValor;
		this.dtAtual = dtAtual;
	}

	public MovimentoFinanceiroSaida(long fkProduto, long fkUsuario, float nrValor, LocalDate dtAtual) {
		this.fkProduto = fkProduto;
		this.fkUsuario = fkUsuario;
		this.nrValor = nrValor;
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

	public float getNrValor() {
		return nrValor;
	}

	public void setNrValor(float nrValor) {
		this.nrValor = nrValor;
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
