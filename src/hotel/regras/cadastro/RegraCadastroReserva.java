package hotel.regras.cadastro;

import java.sql.Date;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import hotel.classes.Quarto;
import hotel.classes.Locacao;
import hotel.classes.DAO.ClienteDAO;
import hotel.classes.DAO.QuartoDAO;
import hotel.classes.DAO.LocacaoDAO;
import hotel.telas.cadastro.TelaCadastroLocacao;

public class RegraCadastroReserva {

	TelaCadastroLocacao tela;

	public RegraCadastroReserva(TelaCadastroLocacao tela) {
		this.tela = tela;
	}

	public void incluirLocacao() {
		long fkCliente = tela.getClienteSelecionado().getId();
		Date entrada = tela.getDataEntrada();
		Date saida = tela.getDataSaida();
		Quarto quarto;
		try {
			QuartoDAO qdao = new QuartoDAO();
			quarto = qdao.selecionarQuartoDisponivel(entrada, saida);
			Locacao reserva = new Locacao(fkCliente, quarto.getId(), entrada.toLocalDate(), saida.toLocalDate(), (long) 1);

			LocacaoDAO reservaDao = new LocacaoDAO();
			reservaDao.inserir(reserva);
			
		} catch (ClassNotFoundException | SQLException e1) {
			JOptionPane.showMessageDialog(null, "Erro no banco de dados, verifique a conexão e a senha, e tente novamente");
		}

	}

	public void alterarLocacao() {
		long id = tela.getLocacaoSelecionado().getId();
		long fkCliente = tela.getClienteSelecionado().getId();
		Date entrada = tela.getDataEntrada();
		Date saida = tela.getDataSaida();
		Quarto quarto;
		try {
			QuartoDAO qdao = new QuartoDAO();
			quarto = qdao.selecionarQuartoDisponivel(entrada, saida);
			Locacao reserva = new Locacao(id, fkCliente, quarto.getId(), entrada.toLocalDate(), saida.toLocalDate(), (long) 1);

			LocacaoDAO reservaDao = new LocacaoDAO();
			reservaDao.alterar(reserva);
			
		} catch (ClassNotFoundException | SQLException e1) {
			JOptionPane.showMessageDialog(null, "Erro no banco de dados, verifique a conexão e a senha, e tente novamente");
		}
	}

	public void excluirLocacao() {

		int result = JOptionPane.showConfirmDialog(null, "Confirmar a exclusão?", "Confirmar",
				JOptionPane.OK_CANCEL_OPTION);
		if (JOptionPane.OK_OPTION == result) {
			Locacao reserva = tela.getLocacaoSelecionado();
			try {
				LocacaoDAO reservaDao = new LocacaoDAO();
				reservaDao.excluir(reserva);
				JOptionPane.showMessageDialog(tela, "Excluido com sucesso!", "Sucesso",
						JOptionPane.INFORMATION_MESSAGE);
			} catch (ClassNotFoundException | SQLException e) {
				JOptionPane.showMessageDialog(null,
						"Erro no banco de dados, verifique a conexão e a senha, e tente novamente");
			}
		}

	}

	public void mostrarLocacao() {//
		Locacao reserva = tela.getLocacaoSelecionado();
		try {
			ClienteDAO cdao = new ClienteDAO();
			tela.setClienteSelecionado(cdao.selecionar(reserva.getFkCliente()));
		} catch (ClassNotFoundException | SQLException e) {
		}
		tela.setDataEntrada(reserva.getDtEntradaSQL());
		tela.setDataSaida(reserva.getDtSaidaSQL());
		
	}

	public void selecionarPorId(Long id) {
		LocacaoDAO dao;
		try {
			dao = new LocacaoDAO();
			tela.setSelectedComboBoxCodigo(dao.selecionar(id));
			mostrarLocacao();
		} catch (ClassNotFoundException | SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro no banco de dados, verifique a conexão e a senha, e tente novamente");
		}

	}

}
