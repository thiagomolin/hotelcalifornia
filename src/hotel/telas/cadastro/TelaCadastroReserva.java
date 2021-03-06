package hotel.telas.cadastro;

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

import com.toedter.calendar.JDateChooser;

import hotel.classes.Reserva;
import hotel.classes.Status;
import hotel.classes.TipoDeQuarto;
import hotel.classes.DAO.ReservaDAO;
import hotel.classes.DAO.StatusDAO;
import hotel.enums.ETipo;
import hotel.regras.cadastro.RegraCadastroReserva;
import hotel.telas.consulta.TelaConsultaGeral;
import hotel.telas.main.TelaPrincipal;
import hotel.util.UtilCombobox;
import hotel.util.UtilVerificadores;
import hotel.util.UtilDatas;

public class TelaCadastroReserva extends Tela {
	private static final long serialVersionUID = 1L;

	private JComboBox<Object> comboBoxCodigo;
	private JComboBox<Object> comboBoxStatus;
	private JComboBox<Object> comboBoxTipoQuarto;

	private RegraCadastroReserva regraReserva;

	private JButton buttonIncluir;
	private JButton buttonExcluir;
	private JButton buttonAlterar;
	private JButton buttonCancelar;
	private JButton buttonNovo;
	private JButton buttonConsulta;
	private JButton buttonMostrar;

	private TelaPrincipal telaPrincipal;

	private JDateChooser dateSaida;
	private JDateChooser dateEntrada;
	private JTextField textFieldCliente;
	private JTextField textFieldCpf;
	private JLabel lblTipoDeQuarto;

	public TelaCadastroReserva(TelaPrincipal telaPrincipal) {
		super();
		setTitle("Cadastro Reserva");
		regraReserva = new RegraCadastroReserva(this);
		inicializarLayoutEEventos();
		inicializarComboBoxCodigo();
		inicializarComboBoxStatus();
		UtilCombobox.inicializarComboBoxTipoQuarto(comboBoxTipoQuarto);
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

		comboBoxCodigo = new JComboBox<Object>();
		comboBoxCodigo.setBounds(111, 59, 131, 20);
		panel_1.add(comboBoxCodigo);

		buttonConsulta = new JButton("...");
		buttonConsulta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				consultar();
			}
		});
		buttonConsulta.setBounds(252, 58, 18, 23);
		panel_1.add(buttonConsulta);

		buttonNovo = new JButton("Novo");
		buttonNovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				novo();
			}
		});
		buttonNovo.setBounds(280, 58, 89, 23);
		panel_1.add(buttonNovo);

		JLabel lblCodReserva = new JLabel("Cod. Reserva");
		lblCodReserva.setBounds(10, 62, 99, 14);
		panel_1.add(lblCodReserva);

		JLabel labelCliente = new JLabel("Cliente");
		labelCliente.setBounds(53, 127, 46, 14);
		getContentPane().add(labelCliente);

		JLabel label_2 = new JLabel("Data Entrada");
		label_2.setBounds(53, 297, 70, 14);
		getContentPane().add(label_2);

		dateEntrada = new JDateChooser();
		dateEntrada.setBounds(138, 297, 129, 20);
		getContentPane().add(dateEntrada);

		dateSaida = new JDateChooser();
		dateSaida.setBounds(138, 338, 129, 20);
		getContentPane().add(dateSaida);

		JLabel label_3 = new JLabel("Data Saida");
		label_3.setBounds(53, 338, 70, 14);
		getContentPane().add(label_3);

		textFieldCliente = new JTextField();
		textFieldCliente.setBounds(138, 124, 129, 20);
		getContentPane().add(textFieldCliente);
		textFieldCliente.setColumns(10);

		textFieldCpf = new JTextField();
		textFieldCpf.setColumns(10);
		textFieldCpf.setBounds(138, 179, 129, 20);
		getContentPane().add(textFieldCpf);

		JLabel labelCpf = new JLabel("CPF");
		labelCpf.setBounds(53, 182, 46, 14);
		getContentPane().add(labelCpf);

		JLabel lblEou = new JLabel("Ou");
		lblEou.setBounds(53, 154, 46, 14);
		getContentPane().add(lblEou);

		comboBoxStatus = new JComboBox<Object>();
		comboBoxStatus.setBounds(138, 218, 129, 20);
		getContentPane().add(comboBoxStatus);

		comboBoxTipoQuarto = new JComboBox<Object>();
		comboBoxTipoQuarto.setEnabled(false);
		comboBoxTipoQuarto.setBounds(138, 260, 129, 20);
		getContentPane().add(comboBoxTipoQuarto);

		JLabel lblStatus = new JLabel("Status");
		lblStatus.setBounds(53, 221, 70, 14);
		getContentPane().add(lblStatus);

		lblTipoDeQuarto = new JLabel("Tipo Quarto");
		lblTipoDeQuarto.setBounds(53, 263, 70, 14);
		getContentPane().add(lblTipoDeQuarto);

	}
	// LAYOUT

	// Eventos de botões
	protected void incluir() {
		if (isFormularioValido()) {
			int result = JOptionPane.showConfirmDialog(null, "Confirmar a inclusão?", "Confirmar",
					JOptionPane.OK_CANCEL_OPTION);
			if (JOptionPane.OK_OPTION == result) {
				regraReserva.incluirReserva();
				limpaCampos();
				inicializarComboBoxCodigo();
			}
		} else {
			JOptionPane.showMessageDialog(null, "Foram detectados campos com informações inválidas!",
					"Erro, verifique o cadastro", JOptionPane.ERROR_MESSAGE);
		}
	}

	protected void excluir() {
		regraReserva.excluirReserva();
		limpaCampos();
		inicializarComboBoxCodigo();
	}

	protected void cancelar() {
		limpaCampos();
	}

	protected void mostrar() {
		try {
			regraReserva.mostrarReserva();
			habilitaCamposEditar();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Selecione uma Reserva!");
		}
	}

	protected void novo() {
		comboBoxCodigo.setEnabled(true);
		comboBoxTipoQuarto.setEnabled(true);
		dateEntrada.setEnabled(true);
		textFieldCliente.setEnabled(true);
		textFieldCpf.setEnabled(true);
		dateSaida.setEnabled(true);
		buttonAlterar.setEnabled(false);
		buttonExcluir.setEnabled(false);
		buttonIncluir.setEnabled(true);
		buttonCancelar.setEnabled(true);
		comboBoxStatus.setEnabled(true);
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
				regraReserva.alterarReserva();
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
		TelaConsultaGeral telaConsulta = new TelaConsultaGeral(ETipo.Reserva, this);
		telaConsulta.setVisible(true);
	}

	protected void sair() {
		telaPrincipal.fecharTela(this);
	}
	// Eventos de botões
	
	

	// Eventos de ComboBox
	private void inicializarComboBoxCodigo() {
		try {
			ReservaDAO cl = new ReservaDAO();
			List<Reserva> reservas = cl.getListaAtiva();
			comboBoxCodigo.setModel(new DefaultComboBoxModel<Object>(reservas.toArray()));
		} catch (ClassNotFoundException | SQLException ex) {
//
//
		}
	}
	// Eventos de ComboBox




	// Métodos de manipulação de componentes

	private void inicializarComboBoxStatus() {
		try {
			StatusDAO st = new StatusDAO();
			List<Status> status = st.getLista();
			comboBoxStatus.setModel(new DefaultComboBoxModel<Object>(status.toArray()));
		} catch (ClassNotFoundException | SQLException ex) {
//
//
		}
	}

	private void inicializarComponentes() {
		buttonAlterar.setEnabled(false);
		dateEntrada.setEnabled(false);
		textFieldCliente.setEnabled(false);
		textFieldCpf.setEnabled(false);
		dateSaida.setEnabled(false);
		buttonExcluir.setEnabled(false);
		buttonIncluir.setEnabled(false);
		buttonCancelar.setEnabled(false);
		comboBoxStatus.setEnabled(false);
		comboBoxStatus.setSelectedItem(null);
		comboBoxTipoQuarto.setEnabled(false);
		comboBoxTipoQuarto.setSelectedItem(null);
		comboBoxCodigo.getModel().setSelectedItem(null);
		buttonMostrar.setEnabled(true);
	}

	protected void habilitaCamposEditar() {
		textFieldCliente.setEnabled(true);
		textFieldCpf.setEnabled(true);
		dateEntrada.setEnabled(true);
		dateSaida.setEnabled(true);
		buttonAlterar.setEnabled(true);
		buttonExcluir.setEnabled(true);
		buttonIncluir.setEnabled(false);
		buttonCancelar.setEnabled(true);
		comboBoxStatus.setEnabled(true);
		comboBoxCodigo.setEnabled(false);
		comboBoxTipoQuarto.setEnabled(true);
		buttonNovo.setEnabled(false);
		buttonConsulta.setEnabled(false);
		buttonMostrar.setEnabled(false);
	}

	protected void desabilitaCampos() {
		buttonAlterar.setEnabled(false);
		textFieldCliente.setEnabled(false);
		textFieldCliente.setText("");
		textFieldCpf.setEnabled(false);
		textFieldCpf.setText("");
		dateEntrada.setEnabled(false);
		dateSaida.setEnabled(false);
		buttonAlterar.setEnabled(false);
		buttonExcluir.setEnabled(false);
		buttonIncluir.setEnabled(false);
		buttonCancelar.setEnabled(false);
		comboBoxCodigo.setEnabled(true);
		comboBoxCodigo.setSelectedItem(null);
		comboBoxStatus.setEnabled(false);
		comboBoxStatus.setSelectedItem(null);
		comboBoxTipoQuarto.setEnabled(false);
		comboBoxTipoQuarto.setSelectedItem(null);
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
		LocalDate entrada = UtilDatas.dateToLocalDate(dateEntrada.getDate());
		LocalDate saida = UtilDatas.dateToLocalDate(dateSaida.getDate());
		boolean valido = true;
		valido = (textFieldCliente.getText().isEmpty() && textFieldCpf.getText().isEmpty()) ? false : valido;
		valido = (!textFieldCpf.getText().isEmpty() && !UtilVerificadores.isCpfValido(textFieldCpf.getText())) ? false : valido;
		valido = (getSelectedComboBoxStatus() == null) ? false : valido;
		valido = (dateEntrada.getDate() == null) ? false : valido;
		valido = (dateSaida.getDate() == null) ? false : valido;
		valido = (entrada.equals(saida)) ? false : valido;
		valido = (entrada.isAfter(saida)) ? false : valido;
		valido = (entrada.isBefore(LocalDate.now())) ? false : valido;
		return valido;
	}
	// Validação de formulário
	
	

	// GETTERS AND SETTERS
	public void setTextFieldCliente(String cliente) {
		textFieldCliente.setText(cliente);
	}

	public void setTextFieldCpf(String cpf) {
		textFieldCpf.setText(cpf);
	}

	public java.sql.Date getDataEntrada() {
		return new java.sql.Date(dateEntrada.getDate().getTime());
	}

	public void setDataEntrada(java.util.Date data) {
		dateEntrada.setDate(data);
	}

	public void setDataSaida(java.util.Date data) {
		dateSaida.setDate(data);
	}

	public java.sql.Date getDataSaida() {
		return new java.sql.Date(dateSaida.getDate().getTime());
	}

	public String getCpf() {
		return textFieldCpf.getText();
	}

	public String getNome() {
		return textFieldCliente.getText();
	}

	public Reserva getReservaSelecionado() {
		return ((Reserva) comboBoxCodigo.getModel().getSelectedItem());
	}

	public Status getSelectedComboBoxStatus() {
		return (Status) comboBoxStatus.getModel().getSelectedItem();
	}

	public void setSelectedComboBoxCodigo(Reserva reserva) {
		this.comboBoxCodigo.getModel().setSelectedItem(reserva);
	}

	public void setSelectedComboBoxCodigo(TipoDeQuarto quarto) {
		this.comboBoxTipoQuarto.getModel().setSelectedItem(quarto);
	}

	public TipoDeQuarto getTipoDeQuartoSelecionado() {
		return ((TipoDeQuarto) comboBoxTipoQuarto.getModel().getSelectedItem());
	}

	public void setSelectedComboBoxStatus(Status status) {
		this.comboBoxStatus.getModel().setSelectedItem(status);
	}

	// GETTERS AND SETTERS

	public void setConsulta(Long id, ETipo tipo) {
		regraReserva.selecionarPorId(id);
		habilitaCamposEditar();
	}
}
