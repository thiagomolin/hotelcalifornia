package hotel.regras.cadastro;

import java.sql.SQLException;

import javax.swing.JOptionPane;

import hotel.classes.Fornecedor;
import hotel.classes.DAO.CidadeDAO;
import hotel.classes.DAO.FornecedorDAO;
import hotel.classes.DAO.EstadoDAO;
import hotel.classes.DAO.PaisDAO;
import hotel.telas.cadastro.TelaCadastroFornecedor;
import hotel.util.UtilCombobox;

public class RegraCadastroFornecedor{

	TelaCadastroFornecedor tela;

	public RegraCadastroFornecedor(TelaCadastroFornecedor tela) {
		this.tela = tela;
	}

	public void incluirFornecedor() {

		String nmFornecedor = tela.getNome();
		String nrCnpj = tela.getCnpj();
		String nrTelefone = tela.getTelefone();
		String dsEmail = tela.getEmail();
		String dsEndereco = tela.getEndereco();
		long fkCidade = tela.getCidadeSelecionado().getId();
		long fkEstado = tela.getEstadoSelecionado().getId();
		long fkPais = tela.getPaisSelecionado().getId();
		Fornecedor fornecedor = new Fornecedor(nmFornecedor, nrCnpj, nrTelefone, dsEmail, dsEndereco, fkCidade, fkEstado, fkPais);
		try {
			FornecedorDAO fornecedorDao = new FornecedorDAO();
			fornecedorDao.inserir(fornecedor);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

	}
	
	public void alterarFornecedor() {
		long idFornecedor = tela.getFornecedorSelecionado().getId();
		String nmFornecedor = tela.getNome();
		String nrCnpj = tela.getCnpj();
		String nrTelefone = tela.getTelefone();
		String dsEmail = tela.getEmail();
		String dsEndereco = tela.getEndereco();
		long fkCidade = tela.getCidadeSelecionado().getId();
		long fkEstado = tela.getEstadoSelecionado().getId();
		long fkPais = tela.getPaisSelecionado().getId();
		Fornecedor fornecedor = new Fornecedor(idFornecedor, nmFornecedor, nrCnpj, nrTelefone, dsEmail, dsEndereco, fkCidade, fkEstado, fkPais);
		try {
			FornecedorDAO fornecedorDao = new FornecedorDAO();
			fornecedorDao.alterar(fornecedor);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

	}

	public void excluirFornecedor() {
	
		int result = JOptionPane.showConfirmDialog(null, "Confirmar a exclusão?", "Confirmar",
				JOptionPane.OK_CANCEL_OPTION);
		if (JOptionPane.OK_OPTION == result) {
			Fornecedor fornecedor = tela.getFornecedorSelecionado();
			try {
				FornecedorDAO fornecedorDao = new FornecedorDAO();
				fornecedorDao.excluir(fornecedor);
				JOptionPane.showMessageDialog(tela, "Excluido com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		}


	}

	public void mostrarFornecedor() {
		try {
			Fornecedor fornecedor = tela.getFornecedorSelecionado();
			tela.setTextFieldNome(fornecedor.getNmFornecedor());
			tela.setTextFieldCnpj(fornecedor.getNrCnpj());
			tela.setTextFieldEmail(fornecedor.getDsEmail());
			tela.setTextFieldTelefone(fornecedor.getNrTelefone());
			tela.setTextFieldEndereco(fornecedor.getDsEndereco());
			try {
				PaisDAO p = new PaisDAO();
				EstadoDAO e = new EstadoDAO();
				CidadeDAO c = new CidadeDAO();
				tela.setSelectedComboBoxPais(p.listarPorId(fornecedor.getFkPais()));
				UtilCombobox.inicializarComboBoxEstado(tela.getComboBoxEstado(), fornecedor.getFkPais());
				tela.setSelectedComboBoxEstado(e.listarPorId(fornecedor.getFkEstado()));
				UtilCombobox.inicializarComboBoxCidade(tela.getComboBoxCidade(), fornecedor.getFkEstado());
				tela.setSelectedComboBoxCidade(c.listarPorId(fornecedor.getFkCidade()));
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
		}

	}
	
	public void selecionarPorId(Long id) {
		FornecedorDAO dao;
		try {
			dao = new FornecedorDAO();
			tela.setSelectedComboBoxCodigo(dao.selecionar(id));
			mostrarFornecedor();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}
	

}
