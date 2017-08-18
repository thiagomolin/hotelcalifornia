package hotel.telas.cadastro;

import java.awt.Font;
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

import hotel.classes.Quarto;
import hotel.classes.TipoDeQuarto;
import hotel.classes.DAO.QuartoDAO;
import hotel.classes.DAO.TipoDeQuartoDAO;
import hotel.regras.cadastro.RegraCadastroQuarto;
import hotel.telas.consulta.ETipos;
import hotel.telas.consulta.TelaConsultaGeral;
import hotel.telas.main.TelaPrincipal;

public class TelaCadastroQuarto extends Tela {
	private static final long serialVersionUID = 1L;
	private JTextField textFieldNumeroQuarto;

	private JComboBox<Object> comboBoxCodigo;
	private JComboBox<Object> comboBoxTipoQuarto;
	private RegraCadastroQuarto regraQuarto;

	private JButton buttonIncluir;
	private JButton buttonExcluir;
	private JButton buttonAlterar;
	private JButton buttonCancelar;
	private JButton buttonNovo;
	private JButton buttonConsulta;
	private JButton buttonMostrar;
	private JCheckBox checkBoxDisponivel;

	private TelaPrincipal telaPrincipal;

	public TelaCadastroQuarto(TelaPrincipal telaPrincipal) {
		super();
		regraQuarto = new RegraCadastroQuarto(this);
		inicializarLayoutEEventos();
		inicializarComboBoxCodigo();
		inicializarComboBoxTipoQuarto();
		inicializarComponentes();
		this.telaPrincipal = telaPrincipal;
	}

	// LAYOUT
	private void inicializarLayoutEEventos() {
		setTitle("Cadastro Quarto");
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

		JLabel lblCadastroDeQuarto = new JLabel("Cadastro de Quarto");
		lblCadastroDeQuarto.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblCadastroDeQuarto.setBounds(10, 11, 146, 17);
		panel_1.add(lblCadastroDeQuarto);

		buttonNovo = new JButton("Novo");
		buttonNovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				novo();
			}
		});
		buttonNovo.setBounds(260, 58, 89, 23);
		panel_1.add(buttonNovo);

		JLabel lblNome = new JLabel("Numero do Quarto");
		lblNome.setBounds(10, 123, 95, 14);
		getContentPane().add(lblNome);

		textFieldNumeroQuarto = new JTextField();
		textFieldNumeroQuarto.setBounds(120, 122, 181, 20);
		getContentPane().add(textFieldNumeroQuarto);
		textFieldNumeroQuarto.setColumns(10);

		JLabel lblDisponvel = new JLabel("Disponível ");
		lblDisponvel.setBounds(10, 223, 95, 14);
		getContentPane().add(lblDisponvel);

		checkBoxDisponivel = new JCheckBox("");
		checkBoxDisponivel.setBounds(120, 219, 97, 23);
		getContentPane().add(checkBoxDisponivel);

		comboBoxTipoQuarto = new JComboBox<Object>();
		comboBoxTipoQuarto.setBounds(120, 165, 181, 20);
		getContentPane().add(comboBoxTipoQuarto);

		JLabel lblTipoDeQuarto = new JLabel("Tipo de Quarto");
		lblTipoDeQuarto.setBounds(10, 168, 95, 14);
		getContentPane().add(lblTipoDeQuarto);

	}
	// LAYOUT

	// Eventos de botões
	protected void incluir() {
		if (isFormularioValido()) {
			int result = JOptionPane.showConfirmDialog(null, "Confirmar a inclusão?", "Confirmar",
					JOptionPane.OK_CANCEL_OPTION);
			if (JOptionPane.OK_OPTION == result) {
				regraQuarto.incluirQuarto();
				limpaCampos();
				inicializarComboBoxCodigo();
			}
		} else {
			JOptionPane.showMessageDialog(null, "Foram detectados campos com informações inválidas!",
					"Erro, verifique o cadastro", JOptionPane.ERROR_MESSAGE);
		}
	}

	protected void excluir() {
		regraQuarto.excluirQuarto();
		limpaCampos();
		inicializarComboBoxCodigo();
	}

	protected void cancelar() {
		limpaCampos();
	}

	protected void mostrar() {
		
		try {
			regraQuarto.mostrarQuarto();
			habilitaCamposEditar();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Selecione um Quarto!");
		}
	}

	protected void novo() {
		textFieldNumeroQuarto.setEnabled(true);
		checkBoxDisponivel.setEnabled(true);
		buttonAlterar.setEnabled(false);
		buttonExcluir.setEnabled(false);
		buttonIncluir.setEnabled(true);
		buttonCancelar.setEnabled(true);
		comboBoxCodigo.setEnabled(false);
		comboBoxTipoQuarto.setEnabled(true);
		buttonNovo.setEnabled(false);
		buttonConsulta.setEnabled(false);
		buttonMostrar.setEnabled(false);
	}

	protected void alterar() {
		if (isFormularioValido()) {
			int result = JOptionPane.showConfirmDialog(null, "Confirmar a alteração?", "Confirmar",
					JOptionPane.OK_CANCEL_OPTION);
			if (JOptionPane.OK_OPTION == result) {
				regraQuarto.alterarQuarto();
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
		TelaConsultaGeral telaConsulta = new TelaConsultaGeral(ETipos.Quarto, this);
		telaConsulta.setVisible(true);
	}

	protected void sair() {
		telaPrincipal.fecharTela(this);
	}
	// Eventos de botões

	// Eventos de ComboBox
	public void inicializarComboBoxCodigo() {
		try {
			QuartoDAO cl = new QuartoDAO();
			List<Quarto> clientes = cl.getLista();	
			comboBoxCodigo.setModel(new DefaultComboBoxModel<Object>(clientes.toArray()));
		} catch (ClassNotFoundException | SQLException ex) {
			ex.printStackTrace();
			ex.printStackTrace();
		}
	}
	
	public void inicializarComboBoxTipoQuarto() {
		try {
			TipoDeQuartoDAO cl = new TipoDeQuartoDAO();
			List<TipoDeQuarto> tipoQuarto = cl.getLista();
			comboBoxTipoQuarto.setModel(new DefaultComboBoxModel<Object>(tipoQuarto.toArray()));
		} catch (ClassNotFoundException | SQLException ex) {
			ex.printStackTrace();
			ex.printStackTrace();
		}
	}
	// Eventos de ComboBox

	// Métodos de manipulação de componentes
	private void inicializarComponentes() {
		textFieldNumeroQuarto.setEnabled(false);
		buttonAlterar.setEnabled(false);
		buttonExcluir.setEnabled(false);
		buttonIncluir.setEnabled(false);
		buttonCancelar.setEnabled(false);
		comboBoxCodigo.getModel().setSelectedItem(null);
		comboBoxTipoQuarto.getModel().setSelectedItem(null);
		comboBoxTipoQuarto.setEnabled(false);
		buttonMostrar.setEnabled(true);
		checkBoxDisponivel.setEnabled(false);
	}
	
	protected void habilitaCamposEditar() {
		textFieldNumeroQuarto.setEnabled(true);
		checkBoxDisponivel.setEnabled(true);
		buttonAlterar.setEnabled(true);
		buttonExcluir.setEnabled(true);
		buttonIncluir.setEnabled(false);
		buttonCancelar.setEnabled(true);
		comboBoxCodigo.setEnabled(false);
		comboBoxTipoQuarto.setEnabled(true);
		buttonNovo.setEnabled(false);
		buttonConsulta.setEnabled(false);
		buttonMostrar.setEnabled(false);
		
	}

	protected void desabilitaCampos() {
		textFieldNumeroQuarto.setText("");
		textFieldNumeroQuarto.setEnabled(false);
		checkBoxDisponivel.setEnabled(false);
		buttonAlterar.setEnabled(false);
		buttonExcluir.setEnabled(false);
		buttonIncluir.setEnabled(false);
		buttonCancelar.setEnabled(false);
		comboBoxCodigo.setEnabled(true);
		comboBoxCodigo.getModel().setSelectedItem(null);
		comboBoxTipoQuarto.getModel().setSelectedItem(null);
		comboBoxTipoQuarto.setEnabled(false);
		buttonNovo.setEnabled(true);
		buttonConsulta.setEnabled(true);
		buttonMostrar.setEnabled(true);
	}

	public void limpaCampos() {
		desabilitaCampos();
	}

	// Métodos de manipulação de componentes

	// Validação de formulário
	protected boolean isFormularioValido() {
		boolean valido = true;
		valido = (textFieldNumeroQuarto.getText().isEmpty()) ? false : valido;
		valido = (comboBoxTipoQuarto.getModel().getSelectedItem() == null)? false: valido;
		QuartoDAO cl;
		try {
			cl = new QuartoDAO();
			List<Quarto> clientes = cl.getLista();	
			for(int i = 0; i < clientes.size(); i++ ) {
			 if((clientes.get(i).getNrQuarto().equals(textFieldNumeroQuarto.getText()))) {
				JOptionPane.showMessageDialog(null, "Este quarto já existe!");
				valido = false;
			 }
			}
		} catch (ClassNotFoundException | SQLException e) {
			
		}

		return valido;
	}
	// Validação de formulário

	// GETTERS AND SETTERS

	public Quarto getQuartoSelecionado() {
		return ((Quarto) comboBoxCodigo.getSelectedItem());
	}

	public void setNumeroQuarto(String text) {
		this.textFieldNumeroQuarto.setText(text);
	}

	public void setSelectedComboBoxCodigo(Quarto quarto) {
		this.comboBoxCodigo.getModel().setSelectedItem(quarto);
	}

	public String getNumeroQuarto() {
		return textFieldNumeroQuarto.getText();
	}

	public void setDisponivel(boolean st) {
		checkBoxDisponivel.setSelected(st);
	}

	public boolean isDisponivel() {
		return checkBoxDisponivel.isSelected();
	}

	@Override
	public void setConsulta(Long id, ETipos tipo) {
		regraQuarto.selecionarPorId(id);
		habilitaCamposEditar();
	}

	public void setTipoQuarto(TipoDeQuarto  tipoDeQuarto) {
		this.comboBoxTipoQuarto.getModel().setSelectedItem(tipoDeQuarto);
	}

	public TipoDeQuarto getTipoQuartoSelecionado() {
		return ((TipoDeQuarto) comboBoxTipoQuarto.getSelectedItem());
	}
}
