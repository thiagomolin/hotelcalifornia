package hotel.regras.cadastro;

import java.sql.Date;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import hotel.classes.Reserva;
import hotel.classes.DAO.ReservaDAO;
import hotel.classes.DAO.StatusDAO;
import hotel.telas.cadastro.TelaCadastroReserva;

public class RegraCadastroReserva {

	TelaCadastroReserva tela;

	public RegraCadastroReserva(TelaCadastroReserva tela) {
		this.tela = tela;
	}

	public void incluirReserva() {
		String nome = tela.getNome();
		String cpf = tela.getCpf();
		Date entrada = tela.getDataEntrada();
		Date saida = tela.getDataSaida();
		long fkStatus = tela.getSelectedComboBoxStatus().getId();
		try {
			Reserva reserva = new Reserva(nome, cpf, entrada.toLocalDate(), saida.toLocalDate(), fkStatus);

			ReservaDAO reservaDao = new ReservaDAO();
			reservaDao.inserir(reserva);
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

	}

	public void alterarReserva() {
		long id = tela.getReservaSelecionado().getId();
		String nome = tela.getNome();
		String cpf = tela.getCpf();
		Date entrada = tela.getDataEntrada();
		Date saida = tela.getDataSaida();
		long fkStatus = tela.getSelectedComboBoxStatus().getId();
		try {
			
			Reserva reserva = new Reserva(id, nome, cpf, entrada.toLocalDate(), saida.toLocalDate(), fkStatus);

			ReservaDAO reservaDao = new ReservaDAO();
			reservaDao.alterar(reserva);
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
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
			StatusDAO sdao = new StatusDAO();
			tela.setSelectedComboBoxStatus(sdao.selecionar(reserva.getFkStatus()));
			tela.setTextFieldCliente(reserva.getNomeCliente());
			tela.setTextFieldCpf(reserva.getNrCpf());
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
