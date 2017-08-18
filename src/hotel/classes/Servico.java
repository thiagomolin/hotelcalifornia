package hotel.classes;

public class Servico {
	
	long id;
	String dsServico;
	
	
	
	public Servico(long id, String dsServico) {
		this.id = id;
		this.dsServico = dsServico;
	}


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDsServico() {
		return dsServico;
	}

	public void setDsServico(String dsServico) {
		this.dsServico = dsServico;
	}
	
	public String toString() {
		return dsServico;
	}

}
