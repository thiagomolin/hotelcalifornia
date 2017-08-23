package hotel.regras.cadastro;

import java.sql.Date;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import hotel.classes.Locacao;
import hotel.classes.Quarto;
import hotel.classes.DAO.ClienteDAO;
import hotel.classes.DAO.LocacaoDAO;
import hotel.classes.DAO.QuartoDAO;
import hotel.telas.cadastro.TelaCadastroLocacao;
import hotel.util.UtilDatas;

public class RegraCadastroLocacao {

	TelaCadastroLocacao tela;

	public RegraCadastroLocacao(TelaCadastroLocacao tela) {
		this.tela = tela;
	}

	public void incluirLocacao() {
		long fkCliente = tela.getClienteSelecionado().getId();
		Date entrada = tela.getDataEntrada();
		Date saida = tela.getDataSaida();

		try {
			QuartoDAO q = new QuartoDAO();

			Quarto quarto = q.selecionarQuartoDisponivel(UtilDatas.dateToSQLDate(entrada),
					UtilDatas.dateToSQLDate(saida), 1);
			
			long fkQuarto = quarto.getId();

			Locacao locacao = new Locacao(fkCliente, fkQuarto, entrada.toLocalDate(), saida.toLocalDate(), (long) 1);

			LocacaoDAO locacaoDao = new LocacaoDAO();
			locacaoDao.inserir(locacao);

		} catch (ClassNotFoundException | SQLException e1) {
			e1.printStackTrace();
		}

	}

	public void alterarLocacao() {
		long id = tela.getLocacaoSelecionado().getId();
		long fkCliente = tela.getClienteSelecionado().getId();
		Date entrada = tela.getDataEntrada();
		Date saida = tela.getDataSaida();
		try {
			Locacao locacao = new Locacao(id, fkCliente, entrada.toLocalDate(), saida.toLocalDate(), (long) 1);

			LocacaoDAO locacaoDao = new LocacaoDAO();
			locacaoDao.alterar(locacao);

		} catch (ClassNotFoundException | SQLException e1) {
			JOptionPane.showMessageDialog(null,
					"Erro no banco de dados, verifique a conexão e a senha, e tente novamente");
		}
	}

	public void excluirLocacao() {

		int result = JOptionPane.showConfirmDialog(null, "Confirmar a exclusão?", "Confirmar",
				JOptionPane.OK_CANCEL_OPTION);
		if (JOptionPane.OK_OPTION == result) {
			Locacao locacao = tela.getLocacaoSelecionado();
			try {
				LocacaoDAO locacaoDao = new LocacaoDAO();
				locacaoDao.excluir(locacao);
				JOptionPane.showMessageDialog(tela, "Excluido com sucesso!", "Sucesso",
						JOptionPane.INFORMATION_MESSAGE);
			} catch (ClassNotFoundException | SQLException e) {
				JOptionPane.showMessageDialog(null,
						"Erro no banco de dados, verifique a conexão e a senha, e tente novamente");
			}
		}

	}

	public void mostrarLocacao() {//
		Locacao locacao = tela.getLocacaoSelecionado();
		try {
			ClienteDAO cdao = new ClienteDAO();
			tela.setClienteSelecionado(cdao.selecionar(locacao.getFkCliente()));
		} catch (ClassNotFoundException | SQLException e) {
		}
		tela.setDataEntrada(locacao.getDtEntradaSQL());
		tela.setDataSaida(locacao.getDtSaidaSQL());

	}

	public void selecionarPorId(Long id) {
		LocacaoDAO dao;
		try {
			dao = new LocacaoDAO();
			tela.setSelectedComboBoxCodigo(dao.selecionar(id));
			mostrarLocacao();
		} catch (ClassNotFoundException | SQLException e) {
			JOptionPane.showMessageDialog(null,
					"Erro no banco de dados, verifique a conexão e a senha, e tente novamente");
		}

	}

}
