package hotel.classes;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Reserva {
	long id;
	long fkCliente;
	long fkQuarto;
	long fkStatus;
	LocalDate dtSaida;
	LocalDate dtEntrada;

	public Reserva(long id, long fkCliente, long fkQuarto, LocalDate dtEntrada, LocalDate dtSaida, long fkStatus) {
		this.id = id;
		this.fkCliente = fkCliente;
		this.fkQuarto = fkQuarto;
		this.dtSaida = dtSaida;
		this.dtEntrada = dtEntrada;
		this.fkStatus = fkStatus;
	}

	public Reserva(long fkCliente, long fkQuarto, LocalDate dtEntrada, LocalDate dtSaida, long fkStatus) {
		this.fkCliente = fkCliente;
		this.fkQuarto = fkQuarto;
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

	public long getFkQuarto() {
		return fkQuarto;
	}

	public void setFkQuarto(long fkQuarto) {
		this.fkQuarto = fkQuarto;
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
