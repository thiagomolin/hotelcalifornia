package hotel.telas.cadastro;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;

import hotel.classes.Cidade;
import hotel.classes.Cliente;
import hotel.classes.Estado;
import hotel.classes.Pais;
import hotel.classes.DAO.ClienteDAO;
import hotel.regras.cadastro.RegraCadastroCliente;
import hotel.telas.consulta.ETipos;
import hotel.telas.consulta.TelaConsultaGeral;
import hotel.telas.main.TelaPrincipal;
import hotel.util.UtilCombobox;
import hotel.util.UtilCpf;

public class TelaCadastroCliente extends Tela {
	private static final long serialVersionUID = 1L;
	private JTextField textFieldNome;
	private JTextField textFieldCpf;
	private JTextField textFieldTelefone;
	private JTextField textFieldEndereco;
	private JTextField textFieldEmail;

	private JComboBox<Object> comboBoxCodigo;
	private JComboBox<Object> comboBoxPais;
	private JComboBox<Object> comboBoxEstado;

	private JComboBox<Object> comboBoxCidade;
	private RegraCadastroCliente regraCliente;

	private JButton buttonIncluir;
	private JButton buttonExcluir;
	private JButton buttonAlterar;
	private JButton buttonCancelar;
	private JButton buttonNovo;
	private JButton buttonConsulta;
	private JButton buttonMostrar;
	
	private TelaPrincipal telaPrincipal;
	
	public TelaCadastroCliente(TelaPrincipal telaPrincipal) {
		super();
		setTitle("Cadastro Cliente");
		regraCliente = new RegraCadastroCliente(this);
		inicializarLayoutEEventos();
		UtilCombobox.inicializarComboBoxPais(comboBoxPais);
		inicializarComboBoxCodigo();
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
				consultar();
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

		JLabel lblNome = new JLabel("Nome");
		lblNome.setBounds(59, 123, 46, 14);
		getContentPane().add(lblNome);

		textFieldNome = new JTextField();
		textFieldNome.setBounds(120, 122, 181, 20);
		getContentPane().add(textFieldNome);
		textFieldNome.setColumns(10);

		JLabel lblCpf = new JLabel("CPF");
		lblCpf.setBounds(59, 151, 46, 14);
		getContentPane().add(lblCpf);

		textFieldCpf = new JTextField();
		textFieldCpf.setBounds(120, 148, 181, 20);
		getContentPane().add(textFieldCpf);
		textFieldCpf.setColumns(10);

		JLabel lblTelefone = new JLabel("Telefone");
		lblTelefone.setBounds(59, 181, 46, 14);
		getContentPane().add(lblTelefone);

		textFieldTelefone = new JTextField();
		textFieldTelefone.setBounds(120, 178, 181, 20);
		getContentPane().add(textFieldTelefone);
		textFieldTelefone.setColumns(10);

		JLabel lblEndereo = new JLabel("Endereço");
		lblEndereo.setBounds(59, 354, 46, 14);
		getContentPane().add(lblEndereo);

		textFieldEndereco = new JTextField();
		textFieldEndereco.setBounds(120, 351, 181, 20);
		getContentPane().add(textFieldEndereco);
		textFieldEndereco.setColumns(10);

		JLabel lblEmail = new JLabel("E-mail");
		lblEmail.setBounds(59, 209, 46, 14);
		getContentPane().add(lblEmail);

		textFieldEmail = new JTextField();
		textFieldEmail.setBounds(120, 206, 181, 20);
		getContentPane().add(textFieldEmail);
		textFieldEmail.setColumns(10);

		JLabel lblNewLabel = new JLabel("País");
		lblNewLabel.setBounds(59, 246, 46, 14);
		getContentPane().add(lblNewLabel);

		comboBoxPais = new JComboBox<Object>();
		comboBoxPais.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				inicializarComboBoxEstado();
			}
		});
		comboBoxPais.setBounds(120, 243, 181, 20);
		getContentPane().add(comboBoxPais);

		JLabel lblEstafo = new JLabel("Estado");
		lblEstafo.setBounds(59, 283, 46, 14);
		getContentPane().add(lblEstafo);

		comboBoxEstado = new JComboBox<Object>();
		comboBoxEstado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				inicializarComboBoxCidade();
			}
		});
		comboBoxEstado.setBounds(120, 280, 181, 20);
		getContentPane().add(comboBoxEstado);

		JLabel lblCidade = new JLabel("Cidade");
		lblCidade.setBounds(59, 322, 46, 14);
		getContentPane().add(lblCidade);

		comboBoxCidade = new JComboBox<Object>();
		comboBoxCidade.setBounds(120, 319, 181, 20);
		getContentPane().add(comboBoxCidade);

	}
	// LAYOUT

	// Eventos de botões
	protected void incluir() {
		if (isFormularioValido()) {
			int result = JOptionPane.showConfirmDialog(null, "Confirmar a inclusão?", "Confirmar",
					JOptionPane.OK_CANCEL_OPTION);
			if (JOptionPane.OK_OPTION == result) {
				regraCliente.incluirCliente();
				limpaCampos();
				inicializarComboBoxCodigo();
			}
		} else {
			JOptionPane.showMessageDialog(null, "Foram detectados campos com informações inválidas!",
					"Erro, verifique o cadastro", JOptionPane.ERROR_MESSAGE);
		}
	}

	protected void excluir() {
		regraCliente.excluirCliente();
		limpaCampos();
		inicializarComboBoxCodigo();
	}
	
	protected void cancelar() {
		limpaCampos();
	}
	
	protected void mostrar() {
		regraCliente.mostrarCliente();
		habilitaCamposEditar();		
	}
	
	protected void novo() {
		textFieldNome.setEnabled(true);
		textFieldCpf.setEnabled(true);
		textFieldTelefone.setEnabled(true);
		textFieldEndereco.setEnabled(true);
		textFieldEmail.setEnabled(true);
		comboBoxCidade.setEnabled(true);
		comboBoxEstado.setEnabled(true);
		comboBoxPais.setEnabled(true);
		buttonAlterar.setEnabled(false);
		buttonExcluir.setEnabled(false);
		buttonIncluir.setEnabled(true);
		buttonCancelar.setEnabled(true);
		comboBoxCodigo.setEnabled(false);
		buttonNovo.setEnabled(false);
		buttonConsulta.setEnabled(false);
		buttonMostrar.setEnabled(false);
	}

	protected void alterar() {
		if (isFormularioValido()) {
			int result = JOptionPane.showConfirmDialog(null, "Confirmar a alteração?", "Confirmar",
					JOptionPane.OK_CANCEL_OPTION);
			if (JOptionPane.OK_OPTION == result) {
				regraCliente.alterarCliente();
				limpaCampos();
				inicializarComboBoxCodigo();
				comboBoxCodigo.getModel().setSelectedItem(null);
			}
		} else {
			JOptionPane.showMessageDialog(null, "Foram detectados campos com informações inválidas!",
					"Erro, verifique o cadastro", JOptionPane.ERROR_MESSAGE);
		}
	}

	protected void consultar() {
		TelaConsultaGeral telaConsulta = new TelaConsultaGeral(ETipos.Cliente, this);
		telaConsulta.setVisible(true);	
	}

	protected void sair() {
		telaPrincipal.fecharTela(this);
	}
	// Eventos de botões
	
	
	// Eventos de ComboBox
	public void inicializarComboBoxCodigo() {
		try {
			ClienteDAO cl = new ClienteDAO();
			List<Cliente> clientes = cl.getLista();
			comboBoxCodigo.setModel(new DefaultComboBoxModel<Object>(clientes.toArray()));
		} catch (ClassNotFoundException | SQLException ex) {
			ex.printStackTrace();
			ex.printStackTrace();
		}
	}

	protected void inicializarComboBoxEstado() {
		UtilCombobox.inicializarComboBoxEstado(comboBoxEstado, getPaisSelecionado().getId());
		comboBoxCidade.removeAllItems();
	}
	
	protected void inicializarComboBoxCidade() {
		UtilCombobox.inicializarComboBoxCidade(comboBoxCidade, getEstadoSelecionado().getId());
	}
	// Eventos de ComboBox
	
	
	
	//Métodos de manipulação de componentes 
	private void inicializarComponentes() {
		textFieldNome.setEnabled(false);
		textFieldCpf.setEnabled(false);
		textFieldTelefone.setEnabled(false);
		textFieldEndereco.setEnabled(false);
		textFieldEmail.setEnabled(false);
		comboBoxCidade.setEnabled(false);
		comboBoxEstado.setEnabled(false);
		comboBoxPais.setEnabled(false);
		buttonAlterar.setEnabled(false);
		buttonExcluir.setEnabled(false);
		buttonIncluir.setEnabled(false);
		buttonCancelar.setEnabled(false);
		comboBoxCodigo.getModel().setSelectedItem(null);
		buttonMostrar.setEnabled(true);
	}

	protected void habilitaCamposEditar() {
		textFieldNome.setEnabled(true);
		textFieldCpf.setEnabled(true);
		textFieldTelefone.setEnabled(true);
		textFieldEndereco.setEnabled(true);
		textFieldEmail.setEnabled(true);
		comboBoxCidade.setEnabled(true);
		comboBoxEstado.setEnabled(true);
		comboBoxPais.setEnabled(true);
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
		textFieldNome.setEnabled(false);
		textFieldCpf.setEnabled(false);
		textFieldTelefone.setEnabled(false);
		textFieldEndereco.setEnabled(false);
		textFieldEmail.setEnabled(false);
		comboBoxCidade.setEnabled(false);
		comboBoxEstado.setEnabled(false);
		comboBoxPais.setEnabled(false);
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
		this.setTextFieldEmail("");
		this.setTextFieldCpf("");
		this.setTextFieldTelefone("");
		this.setTextFieldEndereco("");
		comboBoxCodigo.setSelectedItem(null);
		comboBoxCidade.removeAllItems();
		comboBoxEstado.removeActionListener(comboBoxEstado.getActionListeners()[0]);
		comboBoxEstado.removeAllItems();
		comboBoxEstado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UtilCombobox.inicializarComboBoxCidade(comboBoxCidade, getEstadoSelecionado().getId());
			}
		});
	}

	//Métodos de manipulação de componentes 
	
	
	
	//Validação de formulário
	protected boolean isFormularioValido() {
		textFieldCpf.setText(textFieldCpf.getText().replaceAll("[^0-9]+", ""));
		boolean valido = true;
		valido = (textFieldNome.getText().isEmpty()) ? false : valido;
		valido = (textFieldCpf.getText().isEmpty() || !UtilCpf.isCpfValido(textFieldCpf.getText())) ? false : valido;
		valido = (textFieldEmail.getText().isEmpty()) ? false : valido;
		valido = (textFieldEndereco.getText().isEmpty()) ? false : valido;
		valido = (textFieldTelefone.getText().isEmpty()) ? false : valido;
		valido = (comboBoxCidade.getModel().getSelectedItem() == null) ? false : valido;
		valido = (comboBoxEstado.getModel().getSelectedItem() == null) ? false : valido;
		valido = (comboBoxPais.getModel().getSelectedItem() == null) ? false : valido;

		return valido;
	}
	//Validação de formulário
	
	
	// GETTERS AND SETTERS
	public String getNome() {
		return textFieldNome.getText();
	}

	public String getCpf() {
		return textFieldCpf.getText();
	}

	public String getTelefone() {
		return textFieldTelefone.getText().replaceAll("[^0-9]+", "");
	}

	public String getEndereco() {
		return textFieldEndereco.getText();
	}

	public String getEmail() {
		return textFieldEmail.getText();
	}

	public Estado getEstadoSelecionado() {
		return ((Estado) comboBoxEstado.getSelectedItem());
	}

	public Pais getPaisSelecionado() {
		return ((Pais) comboBoxPais.getSelectedItem());
	}

	public Cidade getCidadeSelecionado() {
		return ((Cidade) comboBoxCidade.getSelectedItem());
	}

	public Cliente getClienteSelecionado() {
		return ((Cliente) comboBoxCodigo.getSelectedItem());
	}

	public void setTextFieldNome(String text) {
		this.textFieldNome.setText(text);
	}

	public void setTextFieldCpf(String text) {
		this.textFieldCpf.setText(text);
	}

	public void setTextFieldTelefone(String text) {
		this.textFieldTelefone.setText(text);
	}

	public void setTextFieldEndereco(String text) {
		this.textFieldEndereco.setText(text);
	}

	public void setTextFieldEmail(String text) {
		this.textFieldEmail.setText(text);
	}

	public void setSelectedComboBoxPais(Pais pais) {
		this.comboBoxPais.getModel().setSelectedItem(pais);
	}

	public void setSelectedComboBoxEstado(Estado estado) {
		this.comboBoxEstado.getModel().setSelectedItem(estado);
	}

	public void setSelectedComboBoxCidade(Cidade cidade) {
		this.comboBoxCidade.getModel().setSelectedItem(cidade);
	}
	
	public void setSelectedComboBoxCodigo(Cliente cliente) {
		this.comboBoxCodigo.getModel().setSelectedItem(cliente);
	}

	public JComboBox<Object> getComboBoxEstado() {
		return comboBoxEstado;
	}

	public JComboBox<Object> getComboBoxCidade() {
		return comboBoxCidade;
	}

	public JComboBox<Object> getComboBoxPais() {
		return comboBoxPais;
	}
	// GETTERS AND SETTERS

	@Override
	public void setConsulta(Long id, ETipos tipo) {
		regraCliente.selecionarPorId(id);
		habilitaCamposEditar();
	}

}

