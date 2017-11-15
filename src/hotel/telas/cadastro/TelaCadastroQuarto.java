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

import hotel.classes.Quarto;
import hotel.classes.TipoDeQuarto;
import hotel.classes.DAO.QuartoDAO;
import hotel.classes.DAO.TipoDeQuartoDAO;
import hotel.enums.ETipo;
import hotel.regras.cadastro.RegraCadastroQuarto;
import hotel.telas.consulta.TelaConsultaGeral;
import hotel.telas.main.TelaPrincipal;

public class TelaCadastroQuarto extends Tela {
	private static final long serialVersionUID = 1L;
	private JTextField txtNumeroQuarto;

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
		panel.setBounds(375, 0, 150, 410);
		getContentPane().add(panel);

		buttonExcluir = new JButton("Excluir");
		buttonExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				excluir();
			}
		});
		buttonExcluir.setBounds(30, 200, 90, 20);
		panel.add(buttonExcluir);

		buttonAlterar = new JButton("Alterar");
		buttonAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				alterar();
			}
		});
		buttonAlterar.setBounds(30, 165, 90, 20);
		panel.add(buttonAlterar);

		buttonIncluir = new JButton("Incluir");

		buttonIncluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				incluir();
			}
		});
		buttonIncluir.setBounds(30, 115, 90, 20);
		panel.add(buttonIncluir);

		buttonCancelar = new JButton("Cancelar");
		buttonCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cancelar();
			}
		});
		buttonCancelar.setBounds(30, 305, 90, 20);
		panel.add(buttonCancelar);

		JButton buttonSair = new JButton("Sair");
		buttonSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				sair();
			}
		});
		buttonSair.setBounds(30, 365, 90, 20);
		panel.add(buttonSair);

		buttonMostrar = new JButton("Mostrar");
		buttonMostrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mostrar();
			}
		});
		buttonMostrar.setEnabled(false);
		buttonMostrar.setBounds(30, 35, 90, 20);
		panel.add(buttonMostrar);

		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		panel_1.setBounds(0, 0, 380, 90);
		getContentPane().add(panel_1);

		JLabel lblCodigo = new JLabel("Código");
		lblCodigo.setBounds(10, 60, 45, 15);
		panel_1.add(lblCodigo);

		comboBoxCodigo = new JComboBox<Object>();
		comboBoxCodigo.setBounds(55, 60, 130, 20);
		panel_1.add(comboBoxCodigo);

		buttonConsulta = new JButton("...");
		buttonConsulta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				consultar();
			}
		});
		buttonConsulta.setBounds(195, 60, 20, 20);
		panel_1.add(buttonConsulta);

		buttonNovo = new JButton("Novo");
		buttonNovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				novo();
			}
		});
		buttonNovo.setBounds(260, 60, 90, 20);
		panel_1.add(buttonNovo);

		JLabel lblNumero = new JLabel("Num do Quarto");
		lblNumero.setBounds(10, 120, 95, 15);
		getContentPane().add(lblNumero);

		txtNumeroQuarto = new JTextField();
		txtNumeroQuarto.setBounds(120, 120, 180, 20);
		getContentPane().add(txtNumeroQuarto);
		txtNumeroQuarto.setColumns(10);

		JLabel lblDisponivel = new JLabel("Disponível ");
		lblDisponivel.setBounds(10, 220, 95, 15);
		getContentPane().add(lblDisponivel);

		checkBoxDisponivel = new JCheckBox("");
		checkBoxDisponivel.setBounds(120, 220, 95, 20);
		getContentPane().add(checkBoxDisponivel);

		comboBoxTipoQuarto = new JComboBox<Object>();
		comboBoxTipoQuarto.setBounds(120, 165, 180, 20);
		getContentPane().add(comboBoxTipoQuarto);

		JLabel lblTipoDeQuarto = new JLabel("Tipo de Quarto");
		lblTipoDeQuarto.setBounds(10, 170, 95, 15);
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
		txtNumeroQuarto.setEnabled(true);
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
		TelaConsultaGeral telaConsulta = new TelaConsultaGeral(ETipo.Quarto, this);
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
		txtNumeroQuarto.setEnabled(false);
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
		txtNumeroQuarto.setEnabled(true);
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
		txtNumeroQuarto.setText("");
		txtNumeroQuarto.setEnabled(false);
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
		valido = (txtNumeroQuarto.getText().isEmpty()) ? false : valido;
		valido = (comboBoxTipoQuarto.getModel().getSelectedItem() == null) ? false : valido;
		QuartoDAO cl;
		try {
			cl = new QuartoDAO();
			List<Quarto> quartos = cl.getLista();
			for (int i = 0; i < quartos.size(); i++) {
				if ((quartos.get(i).getNrQuarto().equals(txtNumeroQuarto.getText()))) {
					try {
						if (getQuartoSelecionado().getId() == quartos.get(i).getId()) {
							continue;
						}
					} catch (Exception e) {
					}
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
		this.txtNumeroQuarto.setText(text);
	}

	public void setSelectedComboBoxCodigo(Quarto quarto) {
		this.comboBoxCodigo.getModel().setSelectedItem(quarto);
	}

	public String getNumeroQuarto() {
		return txtNumeroQuarto.getText();
	}

	public void setDisponivel(boolean st) {
		checkBoxDisponivel.setSelected(st);
	}

	public boolean isDisponivel() {
		return checkBoxDisponivel.isSelected();
	}

	@Override
	public void setConsulta(Long id, ETipo tipo) {
		regraQuarto.selecionarPorId(id);
		habilitaCamposEditar();
	}

	public void setTipoQuarto(TipoDeQuarto tipoDeQuarto) {
		this.comboBoxTipoQuarto.getModel().setSelectedItem(tipoDeQuarto);
	}

	public TipoDeQuarto getTipoQuartoSelecionado() {
		return ((TipoDeQuarto) comboBoxTipoQuarto.getSelectedItem());
	}
}
