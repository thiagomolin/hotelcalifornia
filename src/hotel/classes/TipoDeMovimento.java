package hotel.classes;

public class TipoDeMovimento {
	
	String tipo;
	long id;
	String acumulador;
	
	public TipoDeMovimento(long id) {
		this.id = id;
		definirTipo();
	}
	
	
	private void definirTipo() {
		if(id == 1) {
			tipo = "Saldo inicial de estoque";
			acumulador = "E";
		}else if(id == 2) {
			tipo = "Venda";
			acumulador = "S";
		}else if(id == 3) {
			tipo = "Entrada por transferência";
			acumulador = "E";
		}else if(id == 4) {
			tipo = "Saída por transferência";
			acumulador = "S";
		}else if(id == 5) {
			tipo = "Entrada por nota fiscal";
			acumulador = "E";
		}else if(id == 6) {
			tipo = "Ajuste de saída - Inventário";
			acumulador = "S";
		}else if(id == 7) {
			tipo = "Ajuste de entrada - Inventário";
			acumulador = "E";
		}
	}


	public long getId() {
		return id;
	}


	public String getAcumulador() {
		return acumulador;
	}


	@Override
	public String toString() {
		return tipo;
	}
}
