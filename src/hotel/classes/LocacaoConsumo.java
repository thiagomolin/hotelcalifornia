package hotel.classes;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class LocacaoConsumo {

	private long id;
	private long fk_locacao;
	private long fk_produto;
	private long fk_usuario;
	private int nr_quantidade;
	private LocalDate dt_atual;

	public LocacaoConsumo(long fk_locacao, long fk_produto, long fk_usuario, int nr_quantidade, LocalDate dt_atual) {
		super();
		this.fk_locacao = fk_locacao;
		this.fk_produto = fk_produto;
		this.fk_usuario = fk_usuario;
		this.nr_quantidade = nr_quantidade;
		this.dt_atual = dt_atual;
	}

	public LocacaoConsumo(long id, long fk_locacao, long fk_produto, long fk_usuario, int nr_quantidade,
			LocalDate dt_atual) {
		super();
		this.id = id;
		this.fk_locacao = fk_locacao;
		this.fk_produto = fk_produto;
		this.fk_usuario = fk_usuario;
		this.nr_quantidade = nr_quantidade;
		this.dt_atual = dt_atual;
	}

	public long getId() {
		return id;
	}
	
	public java.sql.Date getDt_AtualSQL() {
		return java.sql.Date.valueOf(dt_atual);
	}
	
	public long getFk_locacao() {
		return fk_locacao;
	}

	public void setFk_locacao(long fk_locacao) {
		this.fk_locacao = fk_locacao;
	}

	public long getFk_produto() {
		return fk_produto;
	}

	public void setFk_produto(long fk_produto) {
		this.fk_produto = fk_produto;
	}

	public long getFk_usuario() {
		return fk_usuario;
	}

	public void setFk_usuario(long fk_usuario) {
		this.fk_usuario = fk_usuario;
	}

	public int getNr_quantidade() {
		return nr_quantidade;
	}

	public void setNr_quantidade(int nr_quantidade) {
		this.nr_quantidade = nr_quantidade;
	}

	public LocalDate getDt_atual() {
		return dt_atual;
	}

	public void setDt_atual(LocalDate dt_atual) {
		this.dt_atual = dt_atual;
	}

	public void setId(long id) {
		this.id = id;
	}

	public static List<String> getCampos() {
		List<String> lista = new ArrayList<String>();
		lista.add("Nome do Produto");
		lista.add("Nome do Fornecedor");

		return lista;
	}

	public String toString() {
		return String.valueOf(id);
	}

}
