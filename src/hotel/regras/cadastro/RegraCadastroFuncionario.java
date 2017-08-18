package hotel.regras.cadastro;

import java.sql.SQLException;

import javax.swing.JOptionPane;

import hotel.classes.Funcionario;
import hotel.classes.DAO.CargoDAO;
import hotel.classes.DAO.CidadeDAO;
import hotel.classes.DAO.FuncionarioDAO;
import hotel.classes.DAO.EstadoDAO;
import hotel.classes.DAO.PaisDAO;
import hotel.telas.cadastro.TelaCadastroFuncionario;
import hotel.util.UtilCombobox;

public class RegraCadastroFuncionario {

	TelaCadastroFuncionario tela;

	public RegraCadastroFuncionario(TelaCadastroFuncionario tela) {
		this.tela = tela;
	}

	public void incluirFuncionario() {
		String nmFuncionario = tela.getNome();
		String nrCpf = tela.getCpf();
		String nrTelefone = tela.getTelefone();
		String dsEmail = tela.getEmail();
		String dsEndereco = tela.getEndereco();
		long fkCidade = tela.getCidadeSelecionado().getId();
		long fkEstado = tela.getEstadoSelecionado().getId();
		long fkPais = tela.getPaisSelecionado().getId();
		long fkCargo = tela.getCargoSelecionado().getId();
		Funcionario funcionario = new Funcionario(nmFuncionario, fkCargo, nrCpf, nrTelefone, dsEmail, dsEndereco, fkCidade, fkEstado, fkPais);
		try {
			FuncionarioDAO funcionarioDao = new FuncionarioDAO();
			funcionarioDao.inserir(funcionario);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

	}
	
	public void alterarFuncionario() {
		long idFuncionario = tela.getFuncionarioSelecionado().getId();
		String nmFuncionario = tela.getNome();
		String nrCpf = tela.getCpf();
		String nrTelefone = tela.getTelefone();
		String dsEmail = tela.getEmail();
		String dsEndereco = tela.getEndereco();
		long fkCidade = tela.getCidadeSelecionado().getId();
		long fkEstado = tela.getEstadoSelecionado().getId();
		long fkPais = tela.getPaisSelecionado().getId();
		long fkCargo = tela.getCargoSelecionado().getId();
		Funcionario funcionario = new Funcionario(idFuncionario, nmFuncionario, fkCargo, nrCpf, nrTelefone, dsEmail, dsEndereco, fkCidade, fkEstado, fkPais);
		try {
			FuncionarioDAO funcionarioDao = new FuncionarioDAO();
			funcionarioDao.alterar(funcionario);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

	}

	public void excluirFuncionario() {
	
		int result = JOptionPane.showConfirmDialog(null, "Confirmar a exclusão?", "Confirmar",
				JOptionPane.OK_CANCEL_OPTION);
		if (JOptionPane.OK_OPTION == result) {
			Funcionario funcionario = tela.getFuncionarioSelecionado();
			try {
				FuncionarioDAO funcionarioDao = new FuncionarioDAO();
				funcionarioDao.excluir(funcionario);
				JOptionPane.showMessageDialog(tela, "Excluido com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		}


	}

	public void mostrarFuncionario() {
		try {
			Funcionario funcionario = tela.getFuncionarioSelecionado();
			tela.setTextFieldNome(funcionario.getNmFuncionario());
			tela.setTextFieldCpf(funcionario.getNrCpf());
			tela.setTextFieldEmail(funcionario.getDsEmail());
			tela.setTextFieldTelefone(funcionario.getNrTelefone());
			tela.setTextFieldEndereco(funcionario.getDsEndereco());
			try {
				PaisDAO p = new PaisDAO();
				EstadoDAO e = new EstadoDAO();
				CidadeDAO c = new CidadeDAO();
				tela.setSelectedComboBoxPais(p.listarPorId(funcionario.getFkPais()));
				UtilCombobox.inicializarComboBoxEstado(tela.getComboBoxEstado(), funcionario.getFkPais());
				tela.setSelectedComboBoxEstado(e.listarPorId(funcionario.getFkEstado()));
				UtilCombobox.inicializarComboBoxCidade(tela.getComboBoxCidade(), funcionario.getFkEstado());
				tela.setSelectedComboBoxCidade(c.listarPorId(funcionario.getFkCidade()));
				
				CargoDAO ca = new CargoDAO();
				tela.setSelectedComboBoxCargo(ca.listarPorId(funcionario.getFkCargo()));
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
		}

	}
	
	public void selecionarPorId(Long id) {
		FuncionarioDAO dao;
		try {
			dao = new FuncionarioDAO();
			tela.setSelectedComboBoxCodigo(dao.selecionar(id));
			mostrarFuncionario();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		
	}
	

}
