package hotel.classes;

import java.time.LocalDate;

public class MovimentoFinanceiroEntradasESaidas {
	private long id;
	private long fkUsuario;
	private float nrValor;
	private LocalDate dt_lancamento;
	
	
	public MovimentoFinanceiroEntradasESaidas(long id, long fkUsuario, float nrValor, LocalDate dt_lancamento){
		this.id = id;
		this.fkUsuario = fkUsuario;
		this.nrValor = nrValor;
		this.dt_lancamento = dt_lancamento;
	}
	
	public MovimentoFinanceiroEntradasESaidas(long fkUsuario, float nrValor, LocalDate dt_lancamento){
		this.fkUsuario = fkUsuario;
		this.nrValor = nrValor;
		this.dt_lancamento = dt_lancamento;
	}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
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


	public LocalDate getDt_lancamento() {
		return dt_lancamento;
	}


	public void setDt_lancamento(LocalDate dt_lancamento) {
		this.dt_lancamento = dt_lancamento;
	}
}
