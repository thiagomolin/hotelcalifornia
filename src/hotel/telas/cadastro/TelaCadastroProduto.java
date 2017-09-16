package hotel.telas.cadastro;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;

import hotel.classes.Fornecedor;
import hotel.classes.Produto;
import hotel.classes.DAO.FornecedorDAO;
import hotel.classes.DAO.ProdutoDAO;
import hotel.regras.cadastro.RegraCadastroProduto;
import hotel.telas.consulta.ETipo;
import hotel.telas.consulta.TelaConsultaGeral;
import hotel.telas.main.TelaPrincipal;

public class TelaCadastroProduto extends Tela {
	private static final long serialVersionUID = 1L;
	private JTextField textFieldProduto;

	private JComboBox<Object> comboBoxCodigo;
	private JComboBox<Object> comboBoxFornecedor;
	private RegraCadastroProduto regraProduto;

	private JButton buttonIncluir;
	private JButton buttonExcluir;
	private JButton buttonAlterar;
	private JButton buttonCancelar;
	private JButton buttonNovo;
	private JButton buttonConsulta;
	private JButton buttonConsultaFornecedor;
	private JButton buttonMostrar;

	private JCheckBox chckbxConsumivel;

	private TelaPrincipal telaPrincipal;
	private JTextField textFieldVlrCompra;
	private JTextField textFieldVlrVenda;
	private JLabel lblConsumvel;

	public TelaCadastroProduto(TelaPrincipal telaPrincipal) {
		super();
		setTitle("Cadastro Produto");
		regraProduto = new RegraCadastroProduto(this);
		inicializarLayoutEEventos();
		inicializarComboBoxCodigo();
		inicializarComboBoxFornecedor();
		inicializarComponentes();
		this.telaPrincipal = telaPrincipal;
	}

	// LAYOUT
	private void inicializarLayoutEEventos() {
		setBounds(100, 100, 540, 440);
		getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		panel.setBounds(376, 0, 148, 410);
		getContentPane().add(panel);

		buttonExcluir = new JButton("Excluir");
		buttonExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				excluir();
			}
		});
		buttonExcluir.setBounds(29, 198, 89, 23);
		panel.add(buttonExcluir);

		buttonAlterar = new JButton("Alterar");
		buttonAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				alterar();
			}
		});
		buttonAlterar.setBounds(29, 164, 89, 23);
		panel.add(buttonAlterar);

		buttonIncluir = new JButton("Incluir");

		buttonIncluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				incluir();
			}
		});
		buttonIncluir.setBounds(29, 114, 89, 23);
		panel.add(buttonIncluir);

		buttonCancelar = new JButton("Cancelar");
		buttonCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cancelar();
			}
		});
		buttonCancelar.setBounds(29, 305, 89, 23);
		panel.add(buttonCancelar);

		JButton button_4 = new JButton("Sair");
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				sair();
			}
		});
		button_4.setBounds(29, 365, 89, 23);
		panel.add(button_4);

		buttonMostrar = new JButton("Mostrar");
		buttonMostrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mostrar();
			}
		});
		buttonMostrar.setEnabled(false);
		buttonMostrar.setBounds(29, 36, 89, 23);
		panel.add(buttonMostrar);

		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		panel_1.setBounds(0, 0, 380, 92);
		getContentPane().add(panel_1);

		JLabel lblCdigo = new JLabel("Código");
		lblCdigo.setBounds(10, 62, 46, 14);
		panel_1.add(lblCdigo);

		comboBoxCodigo = new JComboBox<Object>();
		comboBoxCodigo.setBounds(55, 59, 131, 20);
		panel_1.add(comboBoxCodigo);

		buttonConsulta = new JButton("...");
		buttonConsulta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				consultar(ETipo.Produto);
			}
		});
		buttonConsulta.setBounds(196, 58, 18, 23);
		panel_1.add(buttonConsulta);

		buttonNovo = new JButton("Novo");
		buttonNovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				novo();
			}
		});
		buttonNovo.setBounds(260, 58, 89, 23);
		panel_1.add(buttonNovo);

		JLabel lblNome = new JLabel("Produto");
		lblNome.setBounds(31, 123, 74, 14);
		getContentPane().add(lblNome);

		textFieldProduto = new JTextField();
		textFieldProduto.setBounds(120, 122, 181, 20);
		getContentPane().add(textFieldProduto);
		textFieldProduto.setColumns(10);

		JLabel lblVlrCompra = new JLabel("Vlr. Compra");
		lblVlrCompra.setBounds(31, 154, 74, 14);
		getContentPane().add(lblVlrCompra);

		textFieldVlrCompra = new JTextField();
		textFieldVlrCompra.setEnabled(false);
		textFieldVlrCompra.setColumns(10);
		textFieldVlrCompra.setBounds(120, 153, 181, 20);
		getContentPane().add(textFieldVlrCompra);

		JLabel lblVlrVenda = new JLabel("Vlr. Venda");
		lblVlrVenda.setBounds(31, 185, 74, 14);
		getContentPane().add(lblVlrVenda);

		textFieldVlrVenda = new JTextField();
		textFieldVlrVenda.setEnabled(false);
		textFieldVlrVenda.setColumns(10);
		textFieldVlrVenda.setBounds(120, 184, 181, 20);
		getContentPane().add(textFieldVlrVenda);

		lblConsumvel = new JLabel("Consumível");
		lblConsumvel.setBounds(31, 264, 74, 14);
		getContentPane().add(lblConsumvel);

		chckbxConsumivel = new JCheckBox("");
		chckbxConsumivel.setBounds(120, 260, 97, 23);
		getContentPane().add(chckbxConsumivel);

		comboBoxFornecedor = new JComboBox<Object>();
		comboBoxFornecedor.setBounds(120, 216, 181, 20);
		getContentPane().add(comboBoxFornecedor);

		JLabel lblFornecedor = new JLabel("Fornecedor");
		lblFornecedor.setBounds(31, 219, 74, 14);
		getContentPane().add(lblFornecedor);

		buttonConsultaFornecedor = new JButton("...");
		buttonConsultaFornecedor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				consultar(ETipo.Fornecedor);
			}
		});
		buttonConsultaFornecedor.setBounds(311, 215, 18, 23);
		getContentPane().add(buttonConsultaFornecedor);

	}
	// LAYOUT

	// Eventos de botões
	protected void incluir() {
		if (isFormularioValido()) {
			int result = JOptionPane.showConfirmDialog(null, "Confirmar a inclusão?", "Confirmar",
					JOptionPane.OK_CANCEL_OPTION);
			if (JOptionPane.OK_OPTION == result) {
				regraProduto.incluirProduto();
				limpaCampos();
				inicializarComboBoxCodigo();
			}
		} else {
			JOptionPane.showMessageDialog(null, "Foram detectados campos com informações inválidas!",
					"Erro, verifique o cadastro", JOptionPane.ERROR_MESSAGE);
		}
	}

	protected void excluir() {
		regraProduto.excluirProduto();
		limpaCampos();
		inicializarComboBoxCodigo();
	}

	protected void cancelar() {
		limpaCampos();
	}

	protected void mostrar() {
		regraProduto.mostrarProduto();
		habilitaCamposEditar();
	}

	protected void novo() {
		textFieldProduto.setEnabled(true);
		textFieldVlrCompra.setEnabled(true);
		chckbxConsumivel.setEnabled(true);
		textFieldVlrVenda.setEnabled(true);
		buttonConsultaFornecedor.setEnabled(true);
		buttonAlterar.setEnabled(false);
		buttonExcluir.setEnabled(false);
		buttonIncluir.setEnabled(true);
		buttonCancelar.setEnabled(true);
		comboBoxCodigo.setEnabled(false);
		comboBoxFornecedor.setEnabled(true);
		buttonNovo.setEnabled(false);
		buttonConsulta.setEnabled(false);
		buttonMostrar.setEnabled(false);
	}

	protected void alterar() {
		if (isFormularioValido()) {
			int result = JOptionPane.showConfirmDialog(null, "Confirmar a alteração?", "Confirmar",
					JOptionPane.OK_CANCEL_OPTION);
			if (JOptionPane.OK_OPTION == result) {
				regraProduto.alterarProduto();
				limpaCampos();
				inicializarComboBoxCodigo();
				comboBoxCodigo.getModel().setSelectedItem(null);
			}
		} else {
			JOptionPane.showMessageDialog(null, "Foram detectados campos com informações inválidas!",
					"Erro, verifique o cadastro", JOptionPane.ERROR_MESSAGE);
		}
	}

	protected void consultar(ETipo tipo) {
		TelaConsultaGeral telaConsulta = new TelaConsultaGeral(tipo, this);
		telaConsulta.setVisible(true);
	}

	protected void sair() {
		telaPrincipal.fecharTela(this);
	}
	// Eventos de botões

	// Eventos de ComboBox
	public void inicializarComboBoxCodigo() {
		try {
			ProdutoDAO cl = new ProdutoDAO();
			List<Produto> produtos = cl.getLista();
			comboBoxCodigo.setModel(new DefaultComboBoxModel<Object>(produtos.toArray()));
		} catch (ClassNotFoundException | SQLException ex) {
			ex.printStackTrace();
			ex.printStackTrace();
		}
	}

	public void inicializarComboBoxFornecedor() {
		try {
			FornecedorDAO f = new FornecedorDAO();
			List<Fornecedor> fornecedores = f.getLista();
			comboBoxFornecedor.setModel(new DefaultComboBoxModel<Object>(fornecedores.toArray()));
			comboBoxFornecedor.getModel().setSelectedItem(null);
		} catch (ClassNotFoundException | SQLException ex) {
			ex.printStackTrace();
			ex.printStackTrace();
		}
	}
	// Eventos de ComboBox

	// Métodos de manipulação de componentes
	private void inicializarComponentes() {
		textFieldProduto.setEnabled(false);
		chckbxConsumivel.setEnabled(false);
		buttonConsultaFornecedor.setEnabled(false);
		comboBoxFornecedor.setEnabled(false);
		buttonAlterar.setEnabled(false);
		buttonExcluir.setEnabled(false);
		buttonIncluir.setEnabled(false);
		buttonCancelar.setEnabled(false);
		comboBoxCodigo.getModel().setSelectedItem(null);
		buttonMostrar.setEnabled(true);
	}

	protected void habilitaCamposEditar() {
		textFieldProduto.setEnabled(true);
		textFieldVlrCompra.setEnabled(true);
		chckbxConsumivel.setEnabled(true);
		buttonConsultaFornecedor.setEnabled(true);
		comboBoxFornecedor.setEnabled(true);
		textFieldVlrVenda.setEnabled(true);
		buttonAlterar.setEnabled(true);
		buttonExcluir.setEnabled(true);
		buttonIncluir.setEnabled(false);
		buttonCancelar.setEnabled(true);
		comboBoxCodigo.setEnabled(false);
		buttonNovo.setEnabled(false);
		buttonConsulta.setEnabled(false);
		buttonMostrar.setEnabled(false);
	}

	protected void desabilitaCampos() {
		textFieldProduto.setEnabled(false);
		textFieldVlrCompra.setEnabled(false);
		chckbxConsumivel.setEnabled(false);
		buttonConsultaFornecedor.setEnabled(false);
		comboBoxFornecedor.setEnabled(false);
		textFieldVlrVenda.setEnabled(false);
		chckbxConsumivel.setEnabled(false);
		buttonAlterar.setEnabled(false);
		buttonExcluir.setEnabled(false);
		buttonIncluir.setEnabled(false);
		buttonCancelar.setEnabled(false);
		comboBoxCodigo.setEnabled(true);
		buttonNovo.setEnabled(true);
		buttonConsulta.setEnabled(true);
		buttonMostrar.setEnabled(true);
	}

	public void limpaCampos() {
		desabilitaCampos();

		this.setTextFieldNome("");
		this.setTextFieldVlrCompra(0);
		this.setTextFieldVlrVenda(0);
	}

	public void setConsulta(Long id, ETipo tipo) {
		if (tipo == ETipo.Produto) {
			regraProduto.selecionarPorId(id);
			habilitaCamposEditar();
		} else if (tipo == ETipo.Fornecedor) {
			try {
				FornecedorDAO f = new FornecedorDAO();
				comboBoxFornecedor.getModel().setSelectedItem(f.selecionar(id));
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	// Métodos de manipulação de componentes

	// Validação de formulário
	protected boolean isFormularioValido() {
		boolean valido = true;
		valido = (textFieldProduto.getText().isEmpty()) ? false : valido;
		valido = (textFieldVlrCompra.getText().isEmpty()) ? false : valido;
		valido = (textFieldVlrVenda.getText().isEmpty()) ? false : valido;

		return valido;
	}
	// Validação de formulário

	// GETTERS AND SETTERS
	public String getNome() {
		return textFieldProduto.getText();
	}

	public float getVlrCompra() {
		return Float.parseFloat(textFieldVlrCompra.getText());
	}

	public float getVlrVenda() {
		return Float.parseFloat(textFieldVlrVenda.getText());
	}

	public boolean isConsumivel() {
		return chckbxConsumivel.isSelected();
	}

	public Fornecedor getFornecedorSelecionado() {
		return ((Fornecedor) comboBoxFornecedor.getSelectedItem());
	}

	public Produto getProdutoSelecionado() {
		return ((Produto) comboBoxCodigo.getSelectedItem());
	}

	public void setTextFieldNome(String text) {
		this.textFieldProduto.setText(text);
	}

	public void setTextFieldVlrCompra(float vlr) {
		this.textFieldVlrCompra.setText(Float.toString(vlr));
	}

	public void setTextFieldVlrVenda(float vlr) {
		this.textFieldVlrVenda.setText(Float.toString(vlr));
	}

	public void setCheckBoxConsumivel(boolean bool) {
		chckbxConsumivel.setSelected(bool);
	}

	public void setSelectedComboBoxCodigo(Produto produto) {
		this.comboBoxCodigo.getModel().setSelectedItem(produto);
	}

	public void setSelectedComboBoxFornecedor(Fornecedor fornecedor) {
		this.comboBoxFornecedor.getModel().setSelectedItem(fornecedor);
	}

}
