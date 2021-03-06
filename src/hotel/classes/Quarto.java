package hotel.classes;

import java.util.ArrayList;
import java.util.List;

public class Quarto {

	long id;
	String nrDoQuarto;
	long fkTipoQuarto;
	boolean disponivel;

	public Quarto(long id, String nrDoQuarto, long fkTipoQuarto, boolean disponivel) {
		this.id = id;
		this.nrDoQuarto = nrDoQuarto;
		this.fkTipoQuarto = fkTipoQuarto;
		this.disponivel = disponivel;
	}

	public Quarto(String nrDoQuarto, long fkTipoQuarto, boolean disponivel) {
		this.nrDoQuarto = nrDoQuarto;
		this.fkTipoQuarto = fkTipoQuarto;
		this.disponivel = disponivel;
	}

	public String getNrQuarto() {
		return nrDoQuarto;
	}

	public void setNrDoQuarto(String nrDoQuarto) {
		this.nrDoQuarto = nrDoQuarto;
	}

	public boolean isDisponivel() {
		return disponivel;
	}

	public void setDisponivel(boolean disponivel) {
		this.disponivel = disponivel;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public static List<String> getCampos() {
		List<String> lista = new ArrayList<String>();
		lista.add("Nr. do Quarto");
		return lista;
	}

	public String toString() {
		return nrDoQuarto;
	}
	public long getFkTipoQuarto() {
		return fkTipoQuarto;
	}

	public void setFkTipoQuarto(long fkTipoQuarto) {
		this.fkTipoQuarto = fkTipoQuarto;
	}

	public String getNrDoQuarto() {
		return nrDoQuarto;
	}
}
