package hotel.regras.cadastro;

import java.sql.Date;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import hotel.classes.Quarto;
import hotel.classes.Reserva;
import hotel.classes.DAO.ClienteDAO;
import hotel.classes.DAO.QuartoDAO;
import hotel.classes.DAO.ReservaDAO;
import hotel.telas.cadastro.TelaCadastroReserva;

public class RegraCadastroReserva {

	TelaCadastroReserva tela;

	public RegraCadastroReserva(TelaCadastroReserva tela) {
		this.tela = tela;
	}

	public void incluirReserva() {
		long fkCliente = tela.getClienteSelecionado().getId();
		Date entrada = tela.getDataEntrada();
		Date saida = tela.getDataSaida();
		Quarto quarto;
		try {
			QuartoDAO qdao = new QuartoDAO();
			quarto = qdao.selecionarQuartoDisponivel(entrada, saida);
			Reserva reserva = new Reserva(fkCliente, quarto.getId(), entrada.toLocalDate(), saida.toLocalDate(), (long) 1);

			ReservaDAO reservaDao = new ReservaDAO();
			reservaDao.inserir(reserva);
			
		} catch (ClassNotFoundException | SQLException e1) {
			JOptionPane.showMessageDialog(null, "Erro no banco de dados, verifique a conexão e a senha, e tente novamente");
		}

	}

	public void alterarReserva() {
		long id = tela.getReservaSelecionado().getId();
		long fkCliente = tela.getClienteSelecionado().getId();
		Date entrada = tela.getDataEntrada();
		Date saida = tela.getDataSaida();
		Quarto quarto;
		try {
			QuartoDAO qdao = new QuartoDAO();
			quarto = qdao.selecionarQuartoDisponivel(entrada, saida);
			Reserva reserva = new Reserva(id, fkCliente, quarto.getId(), entrada.toLocalDate(), saida.toLocalDate(), (long) 1);

			ReservaDAO reservaDao = new ReservaDAO();
			reservaDao.alterar(reserva);
			
		} catch (ClassNotFoundException | SQLException e1) {
			JOptionPane.showMessageDialog(null, "Erro no banco de dados, verifique a conexão e a senha, e tente novamente");
		}
	}

	public void excluirReserva() {

		int result = JOptionPane.showConfirmDialog(null, "Confirmar a exclusão?", "Confirmar",
				JOptionPane.OK_CANCEL_OPTION);
		if (JOptionPane.OK_OPTION == result) {
			Reserva reserva = tela.getReservaSelecionado();
			try {
				ReservaDAO reservaDao = new ReservaDAO();
				reservaDao.excluir(reserva);
				JOptionPane.showMessageDialog(tela, "Excluido com sucesso!", "Sucesso",
						JOptionPane.INFORMATION_MESSAGE);
			} catch (ClassNotFoundException | SQLException e) {
				JOptionPane.showMessageDialog(null,
						"Erro no banco de dados, verifique a conexão e a senha, e tente novamente");
			}
		}

	}

	public void mostrarReserva() {//
		Reserva reserva = tela.getReservaSelecionado();
		try {
			ClienteDAO cdao = new ClienteDAO();
			tela.setClienteSelecionado(cdao.selecionar(reserva.getFkCliente()));
		} catch (ClassNotFoundException | SQLException e) {
		}
		tela.setDataEntrada(reserva.getDtEntradaSQL());
		tela.setDataSaida(reserva.getDtSaidaSQL());
		
	}

	public void selecionarPorId(Long id) {
		ReservaDAO dao;
		try {
			dao = new ReservaDAO();
			tela.setSelectedComboBoxCodigo(dao.selecionar(id));
			mostrarReserva();
		} catch (ClassNotFoundException | SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro no banco de dados, verifique a conexão e a senha, e tente novamente");
		}

	}

}
