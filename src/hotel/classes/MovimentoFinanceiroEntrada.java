package hotel.classes;

import java.time.LocalDate;

public class MovimentoFinanceiroEntrada {

	private long id;
	private long fk_locacao;
	private long fkUsuario;
	private float nrValor;
	private LocalDate dt_lancamento;

	public MovimentoFinanceiroEntrada(long id, long fkLocacao, long fkUsuario, float nrValor, LocalDate dtAtual) {
		super();
		this.id = id;
		this.fk_locacao = fkLocacao;
		this.fkUsuario = fkUsuario;
		this.nrValor = nrValor;
		this.dt_lancamento = dtAtual;
	}

	public MovimentoFinanceiroEntrada(long fkLocacao, long fkUsuario, float nrValor, LocalDate dtAtual) {
		this.fk_locacao = fkLocacao;
		this.fkUsuario = fkUsuario;
		this.nrValor = nrValor;
		this.dt_lancamento = dtAtual;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getFkLocacao() {
		return fk_locacao;
	}

	public void setFkLocacao(long fkLocacao) {
		this.fk_locacao = fkLocacao;
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

	public LocalDate getDtLancamento() {
		return dt_lancamento;
	}

	public java.sql.Date getDtLancamentoSQL() {
		return java.sql.Date.valueOf(dt_lancamento);
	}

	public void setDtAtual(LocalDate dtAtual) {
		this.dt_lancamento = dtAtual;
	}
}
