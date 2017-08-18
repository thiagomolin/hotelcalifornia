package hotel.regras.cadastro;

import java.sql.SQLException;

import javax.swing.JOptionPane;

import hotel.classes.Produto;
import hotel.classes.DAO.FornecedorDAO;
import hotel.classes.DAO.ProdutoDAO;
import hotel.telas.cadastro.TelaCadastroProduto;

public class RegraCadastroProduto {

	TelaCadastroProduto tela;

	public RegraCadastroProduto(TelaCadastroProduto tela) {
		this.tela = tela;
	}

	public void incluirProduto() {

		String dsProduto = tela.getNome();
		float nrValorCompra = tela.getVlrCompra();
		float nrValorVenda = tela.getVlrVenda();
		long fkFornecedor = tela.getFornecedorSelecionado().getId();
		boolean consumivel = tela.isConsumivel();
		
		Produto produto = new Produto(dsProduto, fkFornecedor, nrValorCompra, nrValorVenda, consumivel);
		try {
			ProdutoDAO produtoDao = new ProdutoDAO();
			produtoDao.inserir(produto);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

	}

	public void alterarProduto() {
		long idProduto = tela.getProdutoSelecionado().getId();
		String dsProduto = tela.getNome();
		float nrValorCompra = tela.getVlrCompra();
		float nrValorVenda = tela.getVlrVenda();
		long fkFornecedor = tela.getFornecedorSelecionado().getId();
		boolean consumivel = tela.isConsumivel();
		Produto produto = new Produto(idProduto, dsProduto, fkFornecedor, nrValorCompra, nrValorVenda, consumivel);
		try {
			ProdutoDAO produtoDao = new ProdutoDAO();
			produtoDao.alterar(produto);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

	}

	public void excluirProduto() {

		int result = JOptionPane.showConfirmDialog(null, "Confirmar a exclusão?", "Confirmar",
				JOptionPane.OK_CANCEL_OPTION);
		if (JOptionPane.OK_OPTION == result) {
			Produto produto = tela.getProdutoSelecionado();
			try {
				ProdutoDAO produtoDao = new ProdutoDAO();
				produtoDao.excluir(produto);
				JOptionPane.showMessageDialog(tela, "Excluido com sucesso!", "Sucesso",
						JOptionPane.INFORMATION_MESSAGE);
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		}

	}

	public void mostrarProduto() {
		Produto produto = tela.getProdutoSelecionado();
		tela.setTextFieldNome(produto.getDsProduto());
		tela.setTextFieldVlrCompra(produto.getNrValorCompra());
		tela.setTextFieldVlrVenda(produto.getNrValorVenda());
		FornecedorDAO dao;
		try {
			dao = new FornecedorDAO();
			tela.setSelectedComboBoxFornecedor(dao.selecionar(produto.getFkFornecedor()));
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		tela.setCheckBoxConsumivel(produto.isConsumivel());
	}

	public void selecionarPorId(Long id) {
		ProdutoDAO dao;
		try {
			dao = new ProdutoDAO();
			tela.setSelectedComboBoxCodigo(dao.selecionar(id));
			mostrarProduto();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

	}

}
