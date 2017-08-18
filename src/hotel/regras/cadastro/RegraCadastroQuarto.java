package hotel.regras.cadastro;

import java.sql.SQLException;

import javax.swing.JOptionPane;

import hotel.classes.Quarto;
import hotel.classes.DAO.QuartoDAO;
import hotel.telas.cadastro.TelaCadastroQuarto;

public class RegraCadastroQuarto {

	TelaCadastroQuarto tela;

	public RegraCadastroQuarto(TelaCadastroQuarto tela) {
		this.tela = tela;
	}

	public void incluirQuarto() {
		String nrQuarto = tela.getNumeroQuarto();
		boolean stQuarto = tela.isDisponivel();

		Quarto quarto = new Quarto(nrQuarto, stQuarto);
		try {
			QuartoDAO quartoDao = new QuartoDAO();
			quartoDao.inserir(quarto);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

	}

	public void alterarQuarto() {
		long idQuarto = tela.getQuartoSelecionado().getId();
		String nrQuarto = tela.getNumeroQuarto();
		boolean stQuarto = tela.isDisponivel();
		Quarto quarto = new Quarto(idQuarto, nrQuarto, stQuarto);
		try {
			QuartoDAO quartoDao = new QuartoDAO();
			quartoDao.alterar(quarto);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

	}

	public void excluirQuarto() {

		int result = JOptionPane.showConfirmDialog(null, "Confirmar a exclusão?", "Confirmar",
				JOptionPane.OK_CANCEL_OPTION);
		if (JOptionPane.OK_OPTION == result) {
			Quarto quarto = tela.getQuartoSelecionado();
			try {
				QuartoDAO quartoDao = new QuartoDAO();
				quartoDao.excluir(quarto);
				JOptionPane.showMessageDialog(tela, "Excluido com sucesso!", "Sucesso",
						JOptionPane.INFORMATION_MESSAGE);
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		}

	}

	public void mostrarQuarto() {
		Quarto quarto = tela.getQuartoSelecionado();
		tela.setNumeroQuarto(quarto.getNrQuarto());
		tela.setDisponivel(quarto.isDisponivel());

	}

	public void selecionarPorId(Long id) {
		QuartoDAO dao;
		try {
			dao = new QuartoDAO();
			tela.setSelectedComboBoxCodigo(dao.selecionar(id));
			mostrarQuarto();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

	}

}
