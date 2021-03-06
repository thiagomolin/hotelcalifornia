package hotel.classes;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Locacao {
	
	private long id;
	private long fkCliente;
	private long fkStatus;
	private long fkQuarto;
	private LocalDate dtSaida;
	private LocalDate dtEntrada;

	public Locacao(long id, long fkCliente, long fkQuarto, LocalDate dtEntrada, LocalDate dtSaida, long fkStatus) {
		this.id = id;
		this.fkCliente = fkCliente;
		this.setFkQuarto(fkQuarto);
		this.dtSaida = dtSaida;
		this.dtEntrada = dtEntrada;
		this.fkStatus = fkStatus;
	}

	public Locacao(long fkCliente, long fkQuarto, LocalDate dtEntrada, LocalDate dtSaida, long fkStatus) {
		this.fkCliente = fkCliente;
		this.setFkQuarto(fkQuarto);
		this.dtSaida = dtSaida;
		this.dtEntrada = dtEntrada;
		this.fkStatus = fkStatus;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getFkCliente() {
		return fkCliente;
	}

	public void setFkCliente(long fkCliente) {
		this.fkCliente = fkCliente;
	}

	public LocalDate getDtSaida() {
		return dtSaida;
	}

	public java.sql.Date getDtSaidaSQL() {
		return java.sql.Date.valueOf(dtSaida);
	}

	public void setDtSaida(LocalDate dtSaida) {
		this.dtSaida = dtSaida;
	}

	public LocalDate getDtEntrada() {
		return dtEntrada;
	}

	public java.sql.Date getDtEntradaSQL() {
		return java.sql.Date.valueOf(dtEntrada);
	}

	public void setDtEntrada(LocalDate dtEntrada) {
		this.dtEntrada = dtEntrada;
	}

	public long getFkQuarto() {
		return fkQuarto;
	}

	public void setFkQuarto(long fkQuarto) {
		this.fkQuarto = fkQuarto;
	}

	public long getFkStatus() {
		return fkStatus;
	}

	public void setAtiva(long ativa) {
		this.fkStatus = ativa;
	}

	public static List<String> getCampos() {
		List<String> lista = new ArrayList<String>();
		lista.add("Nome do Cliente");
		lista.add("Numero do Quarto");
		return lista;
	}

	public String toString() {
		return String.valueOf(id);
	}

}
