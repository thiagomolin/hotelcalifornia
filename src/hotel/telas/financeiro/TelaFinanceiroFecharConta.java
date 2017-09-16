package hotel.telas.financeiro;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Locale;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;

import hotel.classes.Locacao;
import hotel.classes.MovimentoEstoque;
import hotel.classes.MovimentoFinanceiroEntrada;
import hotel.classes.DAO.LocacaoConsumoDAO;
import hotel.classes.DAO.LocacaoDAO;
import hotel.classes.DAO.MovimentoEstoqueDAO;
import hotel.classes.DAO.MovimentoFinanceiroEntradaDAO;
import hotel.classes.DAO.ProdutoDAO;
import hotel.telas.cadastro.Tela;
import hotel.telas.consulta.ETipos;
import hotel.telas.consulta.TelaConsultaGeral;
import hotel.telas.main.TelaPrincipal;
import hotel.util.UtilVector;

public class TelaFinanceiroFecharConta extends Tela {
	private static final long serialVersionUID = 1L;

	private JComboBox<Object> comboBoxCodigo;

	private TelaPrincipal telaPrincipal;

	private JLabel lblDtInicial;
	private JLabel lblDtFinal;
	private JLabel lblTotal;

	private Vector<Vector<Object>> listaDados;
	private Vector<String> listaColunas;
	private JTable table;

	public TelaFinanceiroFecharConta(TelaPrincipal telaPrincipal) {
		this.telaPrincipal = telaPrincipal;
		inicializarLayoutEEventos();
		inicializarComboBoxCodigo();
	}

	private void inicializarComboBoxCodigo() {
		try {
			LocacaoDAO cl = new LocacaoDAO();
			List<Locacao> locacoes = cl.getListaAtiva();
			comboBoxCodigo.setModel(new DefaultComboBoxModel<Object>(locacoes.toArray()));
		} catch (ClassNotFoundException | SQLException ex) {
			ex.printStackTrace();
			ex.printStackTrace();
		}
	}

	private void inicializarLayoutEEventos() {
		setClosable(true);
		setTitle("Fechar Conta");
		setBounds(100, 100, 540, 440);
		getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(376, 0, 148, 410);
		panel.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		getContentPane().add(panel);
		panel.setLayout(null);

		JButton btnProcessar = new JButton("Processar");
		btnProcessar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				processar();
			}
		});
		btnProcessar.setBounds(29, 155, 89, 23);
		panel.add(btnProcessar);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(29, 217, 89, 23);
		panel.add(btnCancelar);

		JButton btnSair = new JButton("Sair");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sair();
			}
		});
		btnSair.setBounds(29, 365, 89, 23);
		panel.add(btnSair);

		JButton buttonMostrar = new JButton("Mostrar");
		buttonMostrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mostrar();
			}
		});
		buttonMostrar.setBounds(29, 35, 89, 23);
		panel.add(buttonMostrar);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 0, 380, 92);
		panel_1.setLayout(null);
		panel_1.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		getContentPane().add(panel_1);

		JLabel lblCodigo = new JLabel("Código da Locação");
		lblCodigo.setBounds(51, 30, 99, 14);
		panel_1.add(lblCodigo);

		comboBoxCodigo = new JComboBox<Object>();
		comboBoxCodigo.setBounds(160, 27, 92, 20);
		panel_1.add(comboBoxCodigo);

		JButton buttonConsulta = new JButton("...");
		buttonConsulta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				consultar(ETipos.Locacao);
			}
		});
		buttonConsulta.setBounds(256, 26, 18, 23);
		panel_1.add(buttonConsulta);

		lblDtInicial = new JLabel("");
		lblDtInicial.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDtInicial.setBounds(107, 64, 73, 17);
		panel_1.add(lblDtInicial);

		lblDtFinal = new JLabel("");
		lblDtFinal.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDtFinal.setBounds(297, 64, 73, 17);
		panel_1.add(lblDtFinal);

		JLabel lblInicial = new JLabel("Data Inicial:");
		lblInicial.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblInicial.setBounds(10, 64, 87, 17);
		panel_1.add(lblInicial);

		JLabel lblFinal = new JLabel("Data Final:");
		lblFinal.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblFinal.setBounds(206, 64, 87, 17);
		panel_1.add(lblFinal);

		lblTotal = new JLabel("");
		lblTotal.setBounds(215, 303, 146, 17);
		lblTotal.setFont(new Font("Tahoma", Font.BOLD, 14));
		getContentPane().add(lblTotal);

		JLabel label = new JLabel("TOTAL:");
		label.setBounds(46, 303, 146, 17);
		label.setFont(new Font("Tahoma", Font.BOLD, 14));
		getContentPane().add(label);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 103, 356, 183);
		getContentPane().add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);

	}

	protected void consultar(ETipos tipo) {
		TelaConsultaGeral telaConsulta = new TelaConsultaGeral(tipo, this);
		telaConsulta.setVisible(true);
	}

	protected void mostrar() {
		try {
			LocacaoConsumoDAO lcdao = new LocacaoConsumoDAO();

			long fk_locacao = getLocacaoSelecionado().getId();

			listaDados = UtilVector.rsParaVector(lcdao.listar(fk_locacao));
			adicionarTotalPernoites(fk_locacao);
			somarValorTotal();

			listaColunas = lcdao.getCamposBD();
			table.setModel(construirTableModel());

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

	}

	private void somarValorTotal() {
		double acumulador = 0;

		for (Vector<Object> lista : listaDados) {
			acumulador += (double) lista.get(5);
		}

		lblTotal.setText(String.format(Locale.ROOT, "%.2f", acumulador));
	}

	private void adicionarTotalPernoites(long fk_locacao) {
		try {
			LocacaoDAO locadao = new LocacaoDAO();
			Locacao loca = locadao.selecionar(fk_locacao);
			ProdutoDAO proddao = new ProdutoDAO();

			float valorPernoite = proddao.selecionar(4).getNrValorVenda(); // seleciona o produto com id 4, ou seja, o
																			// produto "locação"

			LocalDate dt_entrada = loca.getDtEntrada();
			LocalDate hoje = LocalDate.now();
			long totalPernoites = java.time.temporal.ChronoUnit.DAYS.between(dt_entrada, hoje);
			totalPernoites = (totalPernoites == 0) ? 1 : totalPernoites;

			// Seta labels indicando as datas
			lblDtInicial.setText(dt_entrada.toString());
			lblDtFinal.setText(hoje.toString());

			Vector<Object> pernoites = new Vector<Object>();
			pernoites.add("");
			pernoites.add("Pernoites");
			pernoites.add("");
			pernoites.add(String.valueOf(totalPernoites));
			pernoites.add(String.valueOf(valorPernoite));
			pernoites.add((double) totalPernoites * valorPernoite);

			listaDados.add(pernoites);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

	}

	private DefaultTableModel construirTableModel() {
		DefaultTableModel tableModel = new DefaultTableModel(listaDados, listaColunas) {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		return tableModel;
	}

	protected void processar() {
		int result = JOptionPane.showConfirmDialog(null, "Confirmar o pagamento e finalizar locação?", "Confirmar",
				JOptionPane.OK_CANCEL_OPTION);
		if (JOptionPane.OK_OPTION == result) {

			long fk_locacao = getLocacaoSelecionado().getId();

			gerarFinanceiroEntrada(fk_locacao);
			finalizarLocacao(fk_locacao);
			gerarMovimentoEstoque();

			JOptionPane.showMessageDialog(null, "Sucesso! Reserva finalizada. Lançamentos de estoque gerados");

			limparCampos();

		}
	}

	private void finalizarLocacao(long fk_locacao) {

		try {

			LocacaoDAO locadao = new LocacaoDAO();

			locadao.alterarStatusLocacao(fk_locacao, 2);

		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // status 2, finalizada

	}

	private void limparCampos() {
		inicializarComboBoxCodigo();
		comboBoxCodigo.getModel().setSelectedItem(null);
		listaDados = new Vector<Vector<Object>>();
		table.setModel(construirTableModel());
		lblDtFinal.setText("");
		lblDtInicial.setText("");
		lblTotal.setText("");
	}

	private void gerarMovimentoEstoque() {
		for(Vector<Object> row : listaDados) {
			try {
				long fkProduto = (long) row.get(9);
				if(fkProduto == 4) {
					continue;
				}
				long fkUsuario = 1; // alterar para pegar o usuario logado
				long tipo_mov = 2; //saida por venda
				int quantidade = (int) row.get(3);
				LocalDate dtAtual = LocalDate.now();
				
				MovimentoEstoqueDAO movest = new MovimentoEstoqueDAO();
				MovimentoEstoque mov = new MovimentoEstoque(fkProduto, fkUsuario, tipo_mov, quantidade, dtAtual);
				
				movest.inserir(mov);
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
			
		}
	}

	private void gerarFinanceiroEntrada(long fk_locacao) {
		try {
			long fkUsuario = 1; // alterar para pegar o usuario logado
			float nrValor = Float.valueOf(lblTotal.getText());
			LocalDate dtAtual = LocalDate.now();

			MovimentoFinanceiroEntrada mov = new MovimentoFinanceiroEntrada(fk_locacao, fkUsuario, nrValor, dtAtual);

			MovimentoFinanceiroEntradaDAO movdao = new MovimentoFinanceiroEntradaDAO();

			movdao.inserir(mov);

		} catch (NumberFormatException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	public Locacao getLocacaoSelecionado() {
		return ((Locacao) comboBoxCodigo.getSelectedItem());
	}

	protected void sair() {
		telaPrincipal.fecharTela(this);
	}

	@Override
	public void setConsulta(Long id, ETipos tipo) {
		// TODO Auto-generated method stub

	}
}
