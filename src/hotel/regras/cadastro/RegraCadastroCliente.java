package hotel.regras.cadastro;

import java.sql.SQLException;

import javax.swing.JOptionPane;

import hotel.classes.Cliente;
import hotel.classes.DAO.CidadeDAO;
import hotel.classes.DAO.ClienteDAO;
import hotel.classes.DAO.EstadoDAO;
import hotel.classes.DAO.PaisDAO;
import hotel.telas.cadastro.TelaCadastroCliente;
import hotel.util.UtilCombobox;

public class RegraCadastroCliente {

	TelaCadastroCliente tela;

	public RegraCadastroCliente(TelaCadastroCliente tela) {
		this.tela = tela;
	}

	public void incluirCliente() {

		String nmCliente = tela.getNome();
		String nrCpf = tela.getCpf();
		String nrTelefone = tela.getTelefone();
		String dsEmail = tela.getEmail();
		String dsEndereco = tela.getEndereco();
		long fkCidade = tela.getCidadeSelecionado().getId();
		long fkEstado = tela.getEstadoSelecionado().getId();
		long fkPais = tela.getPaisSelecionado().getId();
		Cliente cliente = new Cliente(nmCliente, nrCpf, nrTelefone, dsEmail, dsEndereco, fkCidade, fkEstado, fkPais);
		try {
			ClienteDAO clienteDao = new ClienteDAO();
			clienteDao.inserir(cliente);
		} catch (ClassNotFoundException | SQLException e) {
			JOptionPane.showMessageDialog(null,
					"Erro no banco de dados, verifique a conexão e a senha, e tente novamente");
		}

	}

	public void alterarCliente() {
		long idCliente = tela.getClienteSelecionado().getId();
		String nmCliente = tela.getNome();
		String nrCpf = tela.getCpf();
		String nrTelefone = tela.getTelefone();
		String dsEmail = tela.getEmail();
		String dsEndereco = tela.getEndereco();
		long fkCidade = tela.getCidadeSelecionado().getId();
		long fkEstado = tela.getEstadoSelecionado().getId();
		long fkPais = tela.getPaisSelecionado().getId();
		Cliente cliente = new Cliente(idCliente, nmCliente, nrCpf, nrTelefone, dsEmail, dsEndereco, fkCidade, fkEstado,
				fkPais);
		try {
			ClienteDAO clienteDao = new ClienteDAO();
			clienteDao.alterar(cliente);
		} catch (ClassNotFoundException | SQLException e) {
			JOptionPane.showMessageDialog(null,
					"Erro no banco de dados, verifique a conexão e a senha, e tente novamente");
		}

	}

	public void excluirCliente() {

		int result = JOptionPane.showConfirmDialog(null, "Confirmar a exclusão?", "Confirmar",
				JOptionPane.OK_CANCEL_OPTION);
		if (JOptionPane.OK_OPTION == result) {
			Cliente cliente = tela.getClienteSelecionado();
			try {
				ClienteDAO clienteDao = new ClienteDAO();
				clienteDao.excluir(cliente);
				JOptionPane.showMessageDialog(tela, "Excluido com sucesso!", "Sucesso",
						JOptionPane.INFORMATION_MESSAGE);
			} catch (ClassNotFoundException | SQLException e) {
				JOptionPane.showMessageDialog(null,
						"Erro no banco de dados, verifique a conexão e a senha, e tente novamente");
			}
		}

	}

	public void mostrarCliente() {

		Cliente cliente = tela.getClienteSelecionado();
		tela.setTextFieldNome(cliente.getNmCliente());
		tela.setTextFieldCpf(cliente.getNrCpf());
		tela.setTextFieldEmail(cliente.getDsEmail());
		tela.setTextFieldTelefone(cliente.getNrTelefone());
		tela.setTextFieldEndereco(cliente.getDsEndereco());
		try {
			PaisDAO p = new PaisDAO();
			EstadoDAO e = new EstadoDAO();
			CidadeDAO c = new CidadeDAO();
			tela.setSelectedComboBoxPais(p.listarPorId(cliente.getFkPais()));
			UtilCombobox.inicializarComboBoxEstado(tela.getComboBoxEstado(), cliente.getFkPais());
			tela.setSelectedComboBoxEstado(e.listarPorId(cliente.getFkEstado()));
			UtilCombobox.inicializarComboBoxCidade(tela.getComboBoxCidade(), cliente.getFkEstado());
			tela.setSelectedComboBoxCidade(c.listarPorId(cliente.getFkCidade()));
		} catch (ClassNotFoundException | SQLException e) {
			JOptionPane.showMessageDialog(null,
					"Erro no banco de dados, verifique a conexão e a senha, e tente novamente");
		}

	}

	public void selecionarPorId(Long id) {
		ClienteDAO dao;
		try {
			dao = new ClienteDAO();
			tela.setSelectedComboBoxCodigo(dao.selecionar(id));
			mostrarCliente();
		} catch (ClassNotFoundException | SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro no banco de dados, verifique a conexão e a senha, e tente novamente");
		}

	}

}
