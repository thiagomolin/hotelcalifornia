package hotel.classes;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Reserva {
	private long id;
	private String nmCliente;
	private String nrCpf;
	private LocalDate dtSaida;
	private LocalDate dtEntrada;
	private long fkStatus;

	public Reserva(long id, String nmCliente, String nrCpf, LocalDate dtEntrada, LocalDate dtSaida, long fkStatus) {
		this.id = id;
		this.nmCliente = nmCliente;
		this.nrCpf = nrCpf;
		this.dtSaida = dtSaida;
		this.dtEntrada = dtEntrada;
		this.fkStatus = fkStatus;
	}

	public Reserva(String nmCliente, String nrCpf, LocalDate dtEntrada, LocalDate dtSaida, long fkStatus) {
		this.nmCliente = nmCliente;
		this.nrCpf = nrCpf;
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

	public String getNomeCliente() {
		return nmCliente;
	}

	public void setFkCliente(String fkCliente) {
		this.nmCliente = fkCliente;
	}

	public LocalDate getDtSaida() {
		return dtSaida;
	}

	public String getNrCpf() {
		return nrCpf;
	}

	public void setNrCpf(String nrCpf) {
		this.nrCpf = nrCpf;
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
		lista.add("CPF");
		return lista;
	}

	public String toString() {
		return String.valueOf(id) + " " + nmCliente;
	}

}
