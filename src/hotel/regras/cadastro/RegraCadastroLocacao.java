package hotel.regras.cadastro;

import java.sql.Date;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import hotel.classes.Locacao;
import hotel.classes.Quarto;
import hotel.classes.Reserva;
import hotel.classes.TipoDeQuarto;
import hotel.classes.DAO.ClienteDAO;
import hotel.classes.DAO.LocacaoDAO;
import hotel.classes.DAO.QuartoDAO;
import hotel.classes.DAO.TipoDeQuartoDAO;
import hotel.telas.cadastro.TelaCadastroLocacao;
import hotel.util.UtilDatas;

public class RegraCadastroLocacao {

	TelaCadastroLocacao tela;

	public RegraCadastroLocacao(TelaCadastroLocacao tela) {
		this.tela = tela;
	}

	public void incluirLocacao(Reserva reserva) {
		long fkCliente = tela.getClienteSelecionado().getId();
		long fkTipoQuarto = tela.getTipoDeQuartoSelecionado().getId();
		Date entrada = tela.getDataEntrada();
		Date saida = tela.getDataSaida();
		long fkStatus = 1;
		
		try {
			if (reserva == null) {
				QuartoDAO q = new QuartoDAO();

				Quarto quarto = q.selecionarQuartoDisponivel(UtilDatas.dateToSQLDate(entrada),
						UtilDatas.dateToSQLDate(saida), fkTipoQuarto);

				long fkQuarto = quarto.getId();

				Locacao locacao = new Locacao(fkCliente, fkQuarto, entrada.toLocalDate(), saida.toLocalDate(),
						fkStatus);

				LocacaoDAO locacaoDao = new LocacaoDAO();
				locacaoDao.inserir(locacao);
			} else {
				RegraCadastroReserva regraReserva = new RegraCadastroReserva();
				regraReserva.finalizarReserva(reserva);
				
				Locacao locacao = new Locacao(fkCliente, reserva.getFkQuarto(), entrada.toLocalDate(), saida.toLocalDate(),
						fkStatus);

				LocacaoDAO locacaoDao = new LocacaoDAO();
				locacaoDao.inserir(locacao);	
			}

		} catch (ClassNotFoundException | SQLException e1) {
			e1.printStackTrace();
		}

	}

	public void alterarLocacao() {
		long id = tela.getLocacaoSelecionado().getId();
		long fkCliente = tela.getClienteSelecionado().getId();
		Date entrada = tela.getDataEntrada();
		Date saida = tela.getDataSaida();
		long fkTipoDeQuarto = tela.getTipoDeQuartoSelecionado().getId();

		try {
			desativarLocacaoTemporariamente();
			QuartoDAO q = new QuartoDAO();
			long fkQuarto = q.selecionarQuartoDisponivel(entrada, saida, fkTipoDeQuarto).getId();
			Locacao locacao = new Locacao(id, fkCliente, fkQuarto, entrada.toLocalDate(), saida.toLocalDate(),
					(long) 1);

			LocacaoDAO locacaoDao = new LocacaoDAO();
			locacaoDao.alterar(locacao);

		} catch (ClassNotFoundException | SQLException e1) {
			JOptionPane.showMessageDialog(null,
					"Erro no banco de dados, verifique a conexão e a senha, e tente novamente");
		}
	}

	private void desativarLocacaoTemporariamente() {
		long fkStatus = 4;
		long id = tela.getLocacaoSelecionado().getId();

		try {
			LocacaoDAO reservaDao = new LocacaoDAO();
			reservaDao.alterarStatusLocacao(id, fkStatus);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
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
			TipoDeQuartoDAO tpqd = new TipoDeQuartoDAO();
			QuartoDAO qdao = new QuartoDAO();
			tela.setClienteSelecionado(cdao.selecionar(locacao.getFkCliente()));
			tela.setSelectedComboBoxTipoDeQuarto(tpqd.listarPorIDQuarto(locacao.getFkQuarto()));
			tela.setLblNrQuarto(qdao.selecionar(locacao.getFkQuarto()).getNrDoQuarto().toString());
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

	public TipoDeQuarto selecionarTipoQuartoPorReserva(Reserva reserva) {
		TipoDeQuarto t = null;
		try {
			QuartoDAO qDao = new QuartoDAO();
			TipoDeQuartoDAO tdq = new TipoDeQuartoDAO();
			Quarto q = qDao.selecionar(reserva.getFkQuarto());
			t = tdq.listarPorIDQuarto(q.getId());

		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return t;
	}
	
	public Quarto selecionarQuartoPorReserva(Reserva reserva) {
		Quarto q = null;
		try {
			QuartoDAO qDao = new QuartoDAO();
			q = qDao.selecionar(reserva.getFkQuarto());
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return q;
	}

}
