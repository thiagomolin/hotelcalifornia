package hotel.classes;

import java.util.ArrayList;
import java.util.List;

public class Produto {

	private long id;
	private String dsProduto;
	private float nrValorCompra;
	private float nrValorVenda;
	private boolean consumivel;
	private long fkFornecedor;

	public Produto(String dsProduto, long fkFornecedor, float nrValorCompra, float nrValorVenda, boolean consumivel) {
		this.dsProduto = dsProduto;
		this.nrValorCompra = nrValorCompra;
		this.nrValorVenda = nrValorVenda;
		this.consumivel = consumivel;
		this.fkFornecedor = fkFornecedor;
	}

	public Produto(long id, String dsProduto, long fkFornecedor, float nrValorCompra, float nrValorVenda, boolean consumivel) {
		this.id = id;
		this.dsProduto = dsProduto;
		this.nrValorCompra = nrValorCompra;
		this.nrValorVenda = nrValorVenda;
		this.consumivel = consumivel;
		this.fkFornecedor = fkFornecedor;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDsProduto() {
		return dsProduto;
	}

	public void setDsProduto(String dsProduto) {
		this.dsProduto = dsProduto;
	}

	public float getNrValorCompra() {
		return nrValorCompra;
	}

	public void setNrValorCompra(Integer nrValorCompra) {
		this.nrValorCompra = nrValorCompra;
	}

	public float getNrValorVenda() {
		return nrValorVenda;
	}

	public void setNrValorVenda(Integer nrValorVenda) {
		this.nrValorVenda = nrValorVenda;
	}

	public boolean isConsumivel() {
		return consumivel;
	}

	public void setConsumivel(boolean consumivel) {
		this.consumivel = consumivel;
	}

	public long getFkFornecedor() {
		return fkFornecedor;
	}

	public void setFkFornecedor(long fkFornecedor) {
		this.fkFornecedor = fkFornecedor;
	}

	public static List<String> getCampos() {
		List<String> lista = new ArrayList<String>();
		lista.add("Nome do Produto");
		lista.add("Nome do Fornecedor");

		return lista;
	}

	public String toString() {

		return id + " - " + dsProduto;
	}

}
