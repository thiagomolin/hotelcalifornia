package hotel.telas.estoque;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;

import hotel.classes.MovimentoEstoque;
import hotel.classes.Produto;
import hotel.classes.TipoDeMovimento;
import hotel.classes.DAO.MovimentoEstoqueDAO;
import hotel.classes.DAO.ProdutoDAO;
import hotel.telas.cadastro.Tela;
import hotel.telas.consulta.ETipos;
import hotel.telas.consulta.TelaConsultaGeral;
import hotel.telas.main.TelaPrincipal;

public class TelaAcertoEstoque extends Tela {
	private static final long serialVersionUID = 1L;

	private JTextField textFieldQuantidade;

	private JComboBox<Object> comboBoxProduto;
	private JComboBox<Object> comboBoxTipoDaMovimentacao;

	private TelaPrincipal telaPrincipal;

	public TelaAcertoEstoque(TelaPrincipal telaPrincipal) {
		super();
		this.telaPrincipal = telaPrincipal;
		inicializarLayoutEEventos();
		inicializarComboBoxProduto();
	}

	private void inicializarLayoutEEventos() {
		setClosable(true);
		setTitle("Acerto no estoque");
		setBounds(100, 100, 540, 440);
		getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		panel.setBounds(376, 0, 148, 410);
		getContentPane().add(panel);

		JButton btnProcessar = new JButton("Processar");
		btnProcessar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				processar();
			}
		});
		btnProcessar.setBounds(29, 41, 89, 23);
		panel.add(btnProcessar);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(29, 100, 89, 23);
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
		panel_1.setBounds(0, 0, 380, 65);
		getContentPane().add(panel_1);
		
		TipoDeMovimento t1 = new TipoDeMovimento(1);
		TipoDeMovimento t2 = new TipoDeMovimento(3);
		TipoDeMovimento t3 = new TipoDeMovimento(4);
		TipoDeMovimento t4 = new TipoDeMovimento(6);
		TipoDeMovimento t5 = new TipoDeMovimento(7);
		TipoDeMovimento[] tipos = {t1, t2, t3, t4, t5};

		comboBoxTipoDaMovimentacao = new JComboBox<Object>();
		comboBoxTipoDaMovimentacao.setBounds(123, 34, 200, 20);
		comboBoxTipoDaMovimentacao.setModel(new DefaultComboBoxModel<>(tipos));
		panel_1.add(comboBoxTipoDaMovimentacao);

		JLabel labelAcerto = new JLabel("Tipo do mov.");
		labelAcerto.setBounds(25, 37, 102, 14);
		panel_1.add(labelAcerto);

		JLabel lblNome = new JLabel("Produto");
		lblNome.setBounds(69, 87, 63, 14);
		getContentPane().add(lblNome);

		JLabel lblCpf = new JLabel("Quantidade");
		lblCpf.setBounds(69, 123, 63, 14);
		getContentPane().add(lblCpf);

		comboBoxProduto = new JComboBox<Object>();
		comboBoxProduto.setBounds(159, 88, 92, 20);
		getContentPane().add(comboBoxProduto);

		JButton buttonSelecionarProduto = new JButton("...");
		buttonSelecionarProduto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				consultar(ETipos.Produto);
			}
		});
		buttonSelecionarProduto.setBounds(255, 87, 18, 23);
		getContentPane().add(buttonSelecionarProduto);

		textFieldQuantidade = new JTextField();
		textFieldQuantidade.setBounds(159, 120, 114, 20);
		getContentPane().add(textFieldQuantidade);
		textFieldQuantidade.setColumns(10);

	}

	protected void processar() {
		if (formularioValido()) {
			gerarEstoque();
		}
	}

	private boolean formularioValido() {
		// TODO Auto-generated method stub
		return true;
	}

	private void gerarEstoque() {
		long fkProduto = getSelectedComboBoxProduto().getId();
		long fkUsuario = telaPrincipal.getUsuarioLogado();
		long fkTipoMovimento = getTipoDeMovimento().getId(); 
		int nrQuantidade = getQuantidade();
		LocalDate dtAtual = LocalDate.now();
		if((fkTipoMovimento == 2) || (fkTipoMovimento == 4) || (fkTipoMovimento == 6)) { //negativo em caso de saidas de estoque
			nrQuantidade *= -1;
		}
		
		MovimentoEstoque m = new MovimentoEstoque(fkProduto, fkUsuario, fkTipoMovimento, nrQuantidade, dtAtual);
		try {
			MovimentoEstoqueDAO mdao = new MovimentoEstoqueDAO();
			mdao.inserir(m);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void sair() {
		telaPrincipal.fecharTela(this);
	}

	public void inicializarComboBoxProduto() {
		try {
			ProdutoDAO cl = new ProdutoDAO();
			List<Produto> produtos = cl.getLista();
			comboBoxProduto.setModel(new DefaultComboBoxModel<Object>(produtos.toArray()));
		} catch (ClassNotFoundException | SQLException ex) {
			ex.printStackTrace();
			ex.printStackTrace();
		}
	}

	protected void consultar(ETipos tipo) {
		TelaConsultaGeral telaConsulta = new TelaConsultaGeral(tipo, this);
		telaConsulta.setVisible(true);
	}

	public int getQuantidade() {
		return Integer.parseInt(textFieldQuantidade.getText());
	}
	
	public TipoDeMovimento getTipoDeMovimento() {
		return (TipoDeMovimento) comboBoxTipoDaMovimentacao.getModel().getSelectedItem();
	}

	public Produto getSelectedComboBoxProduto() {
		return (Produto) comboBoxProduto.getModel().getSelectedItem();
	}

	@Override
	public void setConsulta(Long id, ETipos tipo) {
		try {
			ProdutoDAO p = new ProdutoDAO();
			comboBoxProduto.getModel().setSelectedItem(p.selecionar(id));
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

}
