package hotel.telas.financeiro;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;

import hotel.classes.Locacao;
import hotel.classes.LocacaoConsumo;
import hotel.classes.Produto;
import hotel.classes.DAO.LocacaoConsumoDAO;
import hotel.classes.DAO.LocacaoDAO;
import hotel.classes.DAO.ProdutoDAO;
import hotel.telas.cadastro.Tela;
import hotel.telas.consulta.ETipos;
import hotel.telas.consulta.TelaConsultaGeral;
import hotel.telas.main.TelaPrincipal;

public class TelaFinanceiroLancamento extends Tela {
	private static final long serialVersionUID = 1L;

	private JTextField textFieldQuantidade;

	private JComboBox<Object> comboBoxCodigo;
	private JComboBox<Object> comboBoxProduto;

	private TelaPrincipal telaPrincipal;

	public TelaFinanceiroLancamento(TelaPrincipal telaPrincipal) {
		this.telaPrincipal = telaPrincipal;
		inicializarLayoutEEventos();
		inicializarComboBoxCodigo();
		inicializarComboBoxProduto();
		limpaCampos();
	}

	private void inicializarLayoutEEventos() {
		setClosable(true);
		setTitle("Novo Lançamento");
		setBounds(100, 100, 540, 440);
		getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		panel.setBounds(376, 0, 148, 410);
		getContentPane().add(panel);
		panel.setLayout(null);

		JButton btnProcessar = new JButton("Processar");
		btnProcessar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				processar();
			}
		});
		btnProcessar.setBounds(29, 41, 89, 23);
		panel.add(btnProcessar);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				limpaCampos();
			}
		});
		btnCancelar.setBounds(29, 99, 89, 23);
		panel.add(btnCancelar);

		JButton btnSair = new JButton("Sair");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sair();
			}
		});
		btnSair.setBounds(29, 365, 89, 23);
		panel.add(btnSair);

		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		panel_1.setBounds(0, 0, 380, 92);
		getContentPane().add(panel_1);

		JLabel lblCodigo = new JLabel("Código da Locação");
		lblCodigo.setBounds(49, 62, 99, 14);
		panel_1.add(lblCodigo);

		comboBoxCodigo = new JComboBox<Object>();
		comboBoxCodigo.setBounds(158, 59, 92, 20);
		panel_1.add(comboBoxCodigo);

		JButton buttonConsultaLocacao = new JButton("...");
		buttonConsultaLocacao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				consultar(ETipos.Locacao);
			}
		});
		buttonConsultaLocacao.setBounds(254, 58, 18, 23);
		panel_1.add(buttonConsultaLocacao);

		JLabel lblProduto = new JLabel("Produto");
		lblProduto.setBounds(42, 114, 46, 14);
		getContentPane().add(lblProduto);

		comboBoxProduto = new JComboBox<Object>();
		comboBoxProduto.setBounds(127, 111, 181, 20);
		getContentPane().add(comboBoxProduto);

		JButton buttonConsultaProduto = new JButton("...");
		buttonConsultaProduto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				consultar(ETipos.Produto);
			}
		});
		buttonConsultaProduto.setBounds(311, 110, 18, 23);
		getContentPane().add(buttonConsultaProduto);

		textFieldQuantidade = new JTextField();
		textFieldQuantidade.setBounds(127, 153, 181, 20);
		getContentPane().add(textFieldQuantidade);
		textFieldQuantidade.setColumns(10);

		JLabel lblQuantidade = new JLabel("Quantidade");
		lblQuantidade.setBounds(42, 156, 75, 14);
		getContentPane().add(lblQuantidade);

	}

	protected void processar() {
		if (!textFieldQuantidade.getText().isEmpty()) {
			try {
				LocacaoConsumoDAO lcdao = new LocacaoConsumoDAO();

				long fk_locacao = getLocacaoSelecionado().getId();
				long fk_produto = getProdutoSelecionado().getId();
				long fk_usuario = 1;
				int nr_quantidade = Integer.parseInt(textFieldQuantidade.getText());
				LocalDate dt_atual = LocalDate.now();

				LocacaoConsumo lc = new LocacaoConsumo(fk_locacao, fk_produto, fk_usuario, nr_quantidade, dt_atual);

				lcdao.inserir(lc);

				JOptionPane.showMessageDialog(null, "Consumo inserido com sucesso");
				limpaCampos();

			} catch (ClassNotFoundException | SQLException ex) {
				ex.printStackTrace();
			}
		}else {
			JOptionPane.showMessageDialog(null, "Insira a quantidade");			
		}
	}

	private void limpaCampos() {
		comboBoxCodigo.getModel().setSelectedItem(null);
		comboBoxProduto.getModel().setSelectedItem(null);
		textFieldQuantidade.setText("");
	}

	private void inicializarComboBoxProduto() {
		try {
			ProdutoDAO cl = new ProdutoDAO();
			List<Produto> produtos = cl.getLista();
			comboBoxProduto.setModel(new DefaultComboBoxModel<Object>(produtos.toArray()));
		} catch (ClassNotFoundException | SQLException ex) {
			ex.printStackTrace();
		}
	}

	protected void sair() {
		telaPrincipal.fecharTela(this);
	}

	protected void consultar(ETipos tipo) {
		TelaConsultaGeral telaConsulta = new TelaConsultaGeral(tipo, this);
		telaConsulta.setVisible(true);
	}

	public Locacao getLocacaoSelecionado() {
		return ((Locacao) comboBoxCodigo.getSelectedItem());
	}

	public Produto getProdutoSelecionado() {
		return ((Produto) comboBoxProduto.getSelectedItem());
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

	@Override
	public void setConsulta(Long id, ETipos tipo) {
		// TODO Auto-generated method stub

	}
}
