package hotel.telas.cadastro;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Iterator;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

import com.toedter.calendar.JDateChooser;

import hotel.classes.Cliente;
import hotel.classes.Locacao;
import hotel.classes.Reserva;
import hotel.classes.TipoDeQuarto;
import hotel.classes.DAO.ClienteDAO;
import hotel.classes.DAO.LocacaoDAO;
import hotel.classes.DAO.ReservaDAO;
import hotel.enums.ETipo;
import hotel.regras.cadastro.RegraCadastroLocacao;
import hotel.telas.consulta.TelaConsultaGeral;
import hotel.telas.main.TelaPrincipal;
import hotel.util.UtilCombobox;
import hotel.util.UtilDatas;

public class TelaCadastroLocacao extends Tela {
	private static final long serialVersionUID = 1L;

	private JComboBox<Object> comboBoxCodigo;
	private RegraCadastroLocacao regraLocacao;

	private JButton buttonIncluir;
	private JButton buttonExcluir;
	private JButton buttonAlterar;
	private JButton buttonCancelar;
	private JButton buttonNovo;
	private JButton buttonConsulta;
	private JButton buttonMostrar;
	private JButton buttonConsultaCliente;

	private TelaPrincipal telaPrincipal;

	private JComboBox<Object> comboBoxCliente;
	private JComboBox<Object> comboBoxReserva;
	private JComboBox<Object> comboBoxTipoDeQuarto;

	private JDateChooser dateSaida;
	private JDateChooser dateEntrada;
	private JLabel lblReserva;
	private JLabel lblTipoDeQuarto;
	private JLabel lblNrQuarto;
	private JButton buttonConsultaReserva;

	public TelaCadastroLocacao(TelaPrincipal telaPrincipal) {
		super();
		setTitle("Cadastro Locacao");
		regraLocacao = new RegraCadastroLocacao(this);
		inicializarLayoutEEventos();
		inicializarComboBoxCodigo();
		inicializarComboBoxCliente();
		UtilCombobox.inicializarComboBoxTipoQuarto(comboBoxTipoDeQuarto);

		lblNrQuarto = new JLabel("");
		lblNrQuarto.setBounds(287, 194, 79, 14);
		getContentPane().add(lblNrQuarto);
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

		JButton buttonSair = new JButton("Sair");
		buttonSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				sair();
			}
		});
		buttonSair.setBounds(29, 365, 89, 23);
		panel.add(buttonSair);

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
				consultar(ETipo.Locacao);
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

		JLabel lblCodLocacao = new JLabel("Cod. Locacao");
		lblCodLocacao.setBounds(10, 62, 99, 14);
		panel_1.add(lblCodLocacao);

		JLabel lblCliente = new JLabel("Cliente");
		lblCliente.setBounds(53, 152, 46, 14);
		getContentPane().add(lblCliente);

		comboBoxCliente = new JComboBox<Object>();
		comboBoxCliente.setBounds(138, 149, 129, 20);
		getContentPane().add(comboBoxCliente);

		JLabel lblDataEntrada = new JLabel("Data Entrada");
		lblDataEntrada.setBounds(53, 231, 70, 14);
		getContentPane().add(lblDataEntrada);

		dateEntrada = new JDateChooser();
		dateEntrada.setBounds(138, 231, 129, 20);
		getContentPane().add(dateEntrada);

		dateSaida = new JDateChooser();
		dateSaida.setBounds(138, 272, 129, 20);
		getContentPane().add(dateSaida);

		JLabel lblDataSaida = new JLabel("Data Saida");
		lblDataSaida.setBounds(53, 272, 70, 14);
		getContentPane().add(lblDataSaida);

		buttonConsultaCliente = new JButton("...");
		buttonConsultaCliente.setEnabled(false);
		buttonConsultaCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				consultar(ETipo.Cliente);
			}
		});
		buttonConsultaCliente.setBounds(278, 148, 18, 23);
		getContentPane().add(buttonConsultaCliente);

		comboBoxReserva = new JComboBox<Object>();
		comboBoxReserva.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setLocacaoPorReserva();
			}
		});
		comboBoxReserva.setEnabled(false);
		comboBoxReserva.setBounds(138, 104, 129, 20);
		getContentPane().add(comboBoxReserva);

		lblReserva = new JLabel("Reserva (se existir)");
		lblReserva.setBounds(10, 107, 113, 14);
		getContentPane().add(lblReserva);

		buttonConsultaReserva = new JButton("...");
		buttonConsultaReserva.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				consultar(ETipo.Reserva);
			}
		});
		buttonConsultaReserva.setEnabled(false);
		buttonConsultaReserva.setBounds(278, 103, 18, 23);
		getContentPane().add(buttonConsultaReserva);

		comboBoxTipoDeQuarto = new JComboBox<Object>();
		comboBoxTipoDeQuarto.setEnabled(false);
		comboBoxTipoDeQuarto.setBounds(138, 188, 129, 20);
		getContentPane().add(comboBoxTipoDeQuarto);

		lblTipoDeQuarto = new JLabel("Tipo de Quarto");
		lblTipoDeQuarto.setBounds(46, 191, 77, 14);
		getContentPane().add(lblTipoDeQuarto);

	}
	// LAYOUT

	// Eventos de botões
	protected void incluir() {
		if (isFormularioValido()) {
			int result = JOptionPane.showConfirmDialog(null, "Confirmar a inclusão?", "Confirmar",
					JOptionPane.OK_CANCEL_OPTION);
			if (JOptionPane.OK_OPTION == result) {
				regraLocacao.incluirLocacao(getReservaSelecionado());
				limpaCampos();
				inicializarComboBoxCodigo();
			}
		} else {
			JOptionPane.showMessageDialog(null, "Foram detectados campos com informações inválidas!",
					"Erro, verifique o cadastro", JOptionPane.ERROR_MESSAGE);
		}
	}

	protected void excluir() {
		regraLocacao.excluirLocacao();
		limpaCampos();
		inicializarComboBoxCodigo();
	}

	protected void cancelar() {
		limpaCampos();
	}

	protected void mostrar() {
		if (comboBoxCodigo.getSelectedItem() == null) {
			JOptionPane.showMessageDialog(null, "Selecione uma Locação ou pressione \"Novo\"");
		} else {			
			regraLocacao.mostrarLocacao();
			habilitaCamposEditar();
		}
	}

	protected void novo() {
		comboBoxTipoDeQuarto.setEnabled(true);
		comboBoxReserva.setEnabled(true);
		comboBoxCliente.setEnabled(true);
		comboBoxCodigo.setEnabled(false);
		dateEntrada.setEnabled(true);
		dateSaida.setEnabled(true);
		buttonConsultaCliente.setEnabled(true);
		buttonConsultaReserva.setEnabled(true);
		buttonConsulta.setEnabled(false);
		buttonAlterar.setEnabled(false);
		buttonExcluir.setEnabled(false);
		buttonIncluir.setEnabled(true);
		buttonCancelar.setEnabled(true);
		buttonNovo.setEnabled(false);
		buttonMostrar.setEnabled(false);
		comboBoxCodigo.getModel().setSelectedItem(null);
		comboBoxTipoDeQuarto.getModel().setSelectedItem(null);
		comboBoxCliente.getModel().setSelectedItem(null);
		inicializarComboBoxReserva();
		comboBoxReserva.getModel().setSelectedItem(null);
	}

	protected void alterar() {
		if (isFormularioValido()) {
			int result = JOptionPane.showConfirmDialog(null, "Confirmar a alteração?", "Confirmar",
					JOptionPane.OK_CANCEL_OPTION);
			if (JOptionPane.OK_OPTION == result) {
				regraLocacao.alterarLocacao();
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

	// Combo Box
	// Eventos de botões

	// Eventos de ComboBox
	public void inicializarComboBoxReserva() {
		ReservaDAO rsv;
		try {
			rsv = new ReservaDAO();
			List<Reserva> reservas = rsv.getLista();
			for (Iterator<Reserva> iterator = reservas.iterator(); iterator.hasNext();) {
				Reserva value = iterator.next();
				if (value.getFkStatus() != 1) {
					iterator.remove();
				}
			}
			comboBoxReserva.setModel(new DefaultComboBoxModel<Object>(reservas.toArray()));
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void inicializarComboBoxCodigo() {
		try {
			LocacaoDAO cl = new LocacaoDAO();
			List<Locacao> locacaos = cl.getLista();
			comboBoxCodigo.setModel(new DefaultComboBoxModel<Object>(locacaos.toArray()));
		} catch (ClassNotFoundException | SQLException ex) {
			ex.printStackTrace();
			ex.printStackTrace();
		}
	}

	private void inicializarComboBoxCliente() {
		try {
			ClienteDAO cl = new ClienteDAO();
			List<Cliente> clientes = cl.getLista();
			comboBoxCliente.setModel(new DefaultComboBoxModel<Object>(clientes.toArray()));
		} catch (ClassNotFoundException | SQLException ex) {
			ex.printStackTrace();
			ex.printStackTrace();
		}
	}
	// Eventos de ComboBox

	// Métodos de manipulação de componentes

	// Combo Box

	// Campos
	private void inicializarComponentes() {
		buttonAlterar.setEnabled(false);
		comboBoxCliente.setEnabled(false);
		comboBoxTipoDeQuarto.setEnabled(false);
		comboBoxReserva.setEditable(false);
		dateEntrada.setEnabled(false);
		dateSaida.setEnabled(false);
		buttonExcluir.setEnabled(false);
		buttonIncluir.setEnabled(false);
		buttonCancelar.setEnabled(false);
		comboBoxCodigo.getModel().setSelectedItem(null);
		comboBoxTipoDeQuarto.getModel().setSelectedItem(null);
		comboBoxReserva.getModel().setSelectedItem(null);
		comboBoxCliente.getModel().setSelectedItem(null);
		buttonMostrar.setEnabled(true);
	}

	protected void habilitaCamposEditar() {
		comboBoxCliente.setEnabled(false);
		comboBoxTipoDeQuarto.setEnabled(true);
		comboBoxReserva.setEditable(true);
		dateEntrada.setEnabled(false);
		dateSaida.setEnabled(true);
		buttonConsultaCliente.setEnabled(false);
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

		comboBoxCliente.setEnabled(false);
		comboBoxCodigo.setEnabled(true);
		comboBoxTipoDeQuarto.setEnabled(false);
		comboBoxReserva.setEnabled(false);
		dateEntrada.setEnabled(false);
		dateSaida.setEnabled(false);
		buttonAlterar.setEnabled(false);
		buttonExcluir.setEnabled(false);
		buttonIncluir.setEnabled(false);
		buttonCancelar.setEnabled(false);
		buttonConsultaReserva.setEnabled(false);
		buttonConsultaCliente.setEnabled(false);
		buttonNovo.setEnabled(true);
		buttonConsulta.setEnabled(true);
		buttonMostrar.setEnabled(true);
	}

	public void limpaCampos() {
		desabilitaCampos();
		comboBoxCodigo.getModel().setSelectedItem(null);
		comboBoxTipoDeQuarto.getModel().setSelectedItem(null);
		comboBoxReserva.getModel().setSelectedItem(null);
		comboBoxCliente.getModel().setSelectedItem(null);
		setDataEntrada(null);
		setDataSaida(null);
		lblNrQuarto.setText("");

	}
	// Campos

	// Getter and Setter
	private void setLocacaoPorReserva() {
		Reserva reserva = getReservaSelecionado();
		if (reserva != null) {
			setDataSaida(reserva.getDtSaidaSQL());
			setDataEntrada(reserva.getDtEntradaSQL());
			setSelectedComboBoxTipoDeQuarto(regraLocacao.selecionarTipoQuartoPorReserva(reserva));
			lblNrQuarto.setText("Qrt: " + regraLocacao.selecionarQuartoPorReserva(reserva).toString());
			comboBoxCliente.setEnabled(true);
			comboBoxReserva.setEnabled(false);
			buttonConsultaReserva.setEnabled(false);
			comboBoxTipoDeQuarto.setEnabled(false);
			dateEntrada.setEnabled(false);
			dateSaida.setEnabled(false);

		}
	}

	public void setConsulta(Long id, ETipo tipo) {
		if (tipo == ETipo.Locacao) {
			regraLocacao.selecionarPorId(id);
			habilitaCamposEditar();
		} else if (tipo == ETipo.Cliente) {
			try {
				ClienteDAO c = new ClienteDAO();
				setClienteSelecionado(c.selecionar(id));
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		} else if (tipo == ETipo.Reserva) {
			try {
				ReservaDAO r = new ReservaDAO();
				if (r.selecionar(id).getFkStatus() == 1) {
					setReservaSelecionado(r.selecionar(id));
				} else {
					JOptionPane.showMessageDialog(null, "RESERVA NÃO ATIVA");
				}

			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		}
	}
	// Métodos de manipulação de componentes

	// Validação de formulário
	protected boolean isFormularioValido() {
		LocalDate entrada = UtilDatas.dateToLocalDate(dateEntrada.getDate());
		LocalDate saida = UtilDatas.dateToLocalDate(dateSaida.getDate());
		boolean valido = true;
		valido = (dateEntrada.getDate() == null) ? false : valido;
		valido = (dateSaida.getDate() == null) ? false : valido;
		valido = (entrada.equals(saida)) ? false : valido;
		valido = (entrada.isAfter(saida)) ? false : valido;
		valido = (entrada.isBefore(LocalDate.now())) ? false : valido;
		return valido;
	}
	// Validação de formulário

	// GETTERS AND SETTERS

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

	// GETTER SETTER ITEM COMBOBOX
	public Locacao getLocacaoSelecionado() {
		return ((Locacao) comboBoxCodigo.getSelectedItem());
	}

	public Cliente getClienteSelecionado() {
		return (Cliente) comboBoxCliente.getModel().getSelectedItem();
	}

	public void setClienteSelecionado(Cliente cliente) {
		comboBoxCliente.getModel().setSelectedItem(cliente);
	}

	public void setReservaSelecionado(Reserva reserva) {
		comboBoxReserva.getModel().setSelectedItem(reserva);
	}

	public Reserva getReservaSelecionado() {
		return (Reserva) comboBoxReserva.getModel().getSelectedItem();
	}

	public TipoDeQuarto getTipoDeQuartoSelecionado() {
		return ((TipoDeQuarto) comboBoxTipoDeQuarto.getSelectedItem());
	}

	public void setSelectedComboBoxTipoDeQuarto(TipoDeQuarto tipoDeQuarto) {
		this.comboBoxTipoDeQuarto.getModel().setSelectedItem(tipoDeQuarto);
	}

	public void setSelectedComboBoxCodigo(Locacao locacao) {
		this.comboBoxCodigo.getModel().setSelectedItem(locacao);
	}

	public JLabel getLblNrQuarto() {
		return lblNrQuarto;
	}

	public void setLblNrQuarto(String string) {
		this.lblNrQuarto.setText(string);
	}
}
// Getter and Setter