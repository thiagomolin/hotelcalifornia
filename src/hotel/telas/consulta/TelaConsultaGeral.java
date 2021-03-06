package hotel.telas.consulta;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import hotel.classes.Cliente;
import hotel.classes.Fornecedor;
import hotel.classes.Locacao;
import hotel.classes.Produto;
import hotel.classes.Quarto;
import hotel.classes.DAO.ClienteDAO;
import hotel.classes.DAO.DAO;
import hotel.classes.DAO.FornecedorDAO;
import hotel.classes.DAO.LocacaoDAO;
import hotel.classes.DAO.ProdutoDAO;
import hotel.classes.DAO.QuartoDAO;
import hotel.classes.DAO.ReservaDAO;
import hotel.enums.ETipo;
import hotel.telas.cadastro.Tela;
import hotel.util.UtilDatas;
import hotel.util.UtilVector;

public class TelaConsultaGeral extends JFrame {
	private static final long serialVersionUID = 1L;

	private JTextField textFieldPalavraChave;

	private JComboBox<ETipo> comboBoxTipo;
	private JComboBox<Object> comboBoxCampo;

	private JDateChooser dataDe;
	private JDateChooser dataAte;

	private JButton btnSelecionar;
	private JButton btnCancelar;

	private DAO dao;
	private Tela telaCadastro;

	private Vector<Vector<Object>> listaDados;
	private Vector<String> listaColunas;

	private ETipo tipoSelecionado = null;
	private JTable table;


	public TelaConsultaGeral() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		inicializarLayoutEEventos();
		comboBoxTipo.getModel().setSelectedItem(null);
		btnCancelar.setVisible(true);
	}

	public TelaConsultaGeral(ETipo tipoSelecionado, Tela telaCadastro) {
		this.tipoSelecionado = tipoSelecionado;
		this.telaCadastro = telaCadastro;
		inicializarLayoutEEventos();
		mostrarCampos();
		btnCancelar.setVisible(false);
	}

	private void inicializarLayoutEEventos() {
		setTitle("Consulta");
		setBounds(100, 100, 750, 450);
		getContentPane().setLayout(null);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 0, 724, 162);
		panel_1.setLayout(null);
		panel_1.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		getContentPane().add(panel_1);

		comboBoxTipo = new JComboBox<ETipo>();
		comboBoxTipo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selecionarTipo();
				mostrarCampos();
			}
		});
		comboBoxTipo.setModel(new DefaultComboBoxModel<ETipo>(new ETipo[] { ETipo.Cliente, ETipo.Fornecedor,
				ETipo.Reserva, ETipo.Locacao, ETipo.Produto, ETipo.Quarto }));
		comboBoxTipo.setBounds(45, 11, 183, 20);
		panel_1.add(comboBoxTipo);

		if (tipoSelecionado != null) {
			comboBoxTipo.setEnabled(false);
		}

		JLabel lblTipo = new JLabel("Tipo");
		lblTipo.setBounds(10, 14, 46, 14);
		panel_1.add(lblTipo);

		dataDe = new JDateChooser();
		dataDe.setBounds(65, 93, 87, 20);
		panel_1.add(dataDe);

		JLabel lblDe = new JLabel("De");
		lblDe.setBounds(21, 93, 46, 14);
		panel_1.add(lblDe);

		JLabel lblAte = new JLabel("Até");
		lblAte.setBounds(225, 93, 30, 14);
		panel_1.add(lblAte);

		dataAte = new JDateChooser();
		dataAte.setBounds(265, 93, 87, 20);
		panel_1.add(dataAte);

		JLabel lblPalavrachave = new JLabel("Palavra-Chave");
		lblPalavrachave.setBounds(10, 42, 128, 14);
		panel_1.add(lblPalavrachave);

		textFieldPalavraChave = new JTextField();
		textFieldPalavraChave.setBounds(104, 42, 511, 20);
		panel_1.add(textFieldPalavraChave);
		textFieldPalavraChave.setColumns(10);

		JLabel lblCampo = new JLabel("Campo");
		lblCampo.setBounds(412, 14, 57, 14);
		panel_1.add(lblCampo);

		comboBoxCampo = new JComboBox<Object>();
		comboBoxCampo.setBounds(479, 11, 136, 20);
		panel_1.add(comboBoxCampo);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cancelar();
			}
		});
		btnCancelar.setBounds(625, 128, 89, 23);
		panel_1.add(btnCancelar);

		JButton btnMostrar = new JButton("Mostrar");
		btnMostrar.setBounds(625, 58, 89, 23);
		panel_1.add(btnMostrar);

		btnSelecionar = new JButton("Selecionar");
		btnSelecionar.setEnabled(false);
		btnSelecionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selecionar();
			}
		});
		btnSelecionar.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnSelecionar.setBounds(625, 10, 89, 23);
		panel_1.add(btnSelecionar);

		btnMostrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mostrar();
			}
		});

		JLabel lblTotal = new JLabel("");
		lblTotal.setBounds(215, 303, 146, 17);
		lblTotal.setFont(new Font("Tahoma", Font.BOLD, 14));
		getContentPane().add(lblTotal);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 173, 714, 217);
		getContentPane().add(scrollPane);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent me) {
				if (me.getClickCount() == 2) {
					selecionar();
				}
			}
		});
		scrollPane.setViewportView(table);

	}

	protected void cancelar() {
		comboBoxTipo.getModel().setSelectedItem(null);
		comboBoxCampo.getModel().setSelectedItem(null);
		table.setModel(new DefaultTableModel());
		textFieldPalavraChave.setText("");
	}

	private void statusBtnSelecionar() {
		if (tipoSelecionado == null) {
			btnSelecionar.setEnabled(false);
		} else if (telaCadastro == null) {
			btnSelecionar.setEnabled(false);
		} else {
			btnSelecionar.setEnabled(true);
		}
	}

	protected void mostrar() {
		if (textFieldPalavraChave.getText().isEmpty()) {
			if (tipoSelecionado != null) {
				if (tipoSelecionado == ETipo.Reserva && isDataValida()) {
					mostrarReserva();
				} else if (tipoSelecionado == ETipo.Locacao && isDataValida()) {
					mostrarLocacao();
				} else {
					try {
						listaDados = UtilVector.rsParaVector(dao.listar());
						listaColunas = dao.getCamposBDAnalitico();
						table.setModel(construirTableModel());
						statusBtnSelecionar();
					} catch (ClassNotFoundException | SQLException e) {
//
					}
				}
			}
		} else {
			try {
				ResultSet rs = dao.listarFiltro(((String) comboBoxCampo.getModel().getSelectedItem()),
						textFieldPalavraChave.getText());
				listaDados = UtilVector.rsParaVector(rs);
				listaColunas = dao.getCamposBDAnalitico();
				table.setModel(construirTableModel());
				statusBtnSelecionar();
			} catch (ClassNotFoundException | SQLException e) {
//
			}
		}
	}

	protected void mostrarReserva() {
		if (dataDe.getDate() == null && dataAte.getDate() == null) {
			try {
				dao = new ReservaDAO();
				listaDados = UtilVector.rsParaVector(dao.listar());
				listaColunas = dao.getCamposBDAnalitico();
				table.setModel(construirTableModel());
			} catch (ClassNotFoundException | SQLException e) {
//
			}
		} else if (dataDe.getDate() != null && dataAte.getDate() != null) {
			try {
				ReservaDAO dao = new ReservaDAO();
				listaDados = UtilVector.rsParaVector(dao.listarPorDatas(dataDe.getDate(), dataAte.getDate()));
				listaColunas = dao.getCamposBDAnalitico();
				table.setModel(construirTableModel());
			} catch (ClassNotFoundException | SQLException e) {
//
			}
		}
	}

	protected void mostrarLocacao() {
		if (dataDe.getDate() == null && dataAte.getDate() == null) {
			try {
				dao = new LocacaoDAO();
				listaDados = UtilVector.rsParaVector(dao.listar());
				listaColunas = dao.getCamposBDAnalitico();
				table.setModel(construirTableModel());
			} catch (ClassNotFoundException | SQLException e) {
//
			}
		} else if (dataDe.getDate() != null && dataAte.getDate() != null) {
			try {
				LocacaoDAO dao = new LocacaoDAO();
				listaDados = UtilVector.rsParaVector(dao.listarPorDatas(dataDe.getDate(), dataAte.getDate()));
				listaColunas = dao.getCamposBDAnalitico();
				table.setModel(construirTableModel());
			} catch (ClassNotFoundException | SQLException e) {
//
			}
		}
	}

	protected void selecionar() {

		long linhaSelecionada = -1;
		try {
			linhaSelecionada = Long.valueOf((table.getValueAt(table.getSelectedRow(), 0)).toString());
		} catch (NumberFormatException | java.lang.ArrayIndexOutOfBoundsException e) {
			JOptionPane.showMessageDialog(null, "Selecione um registro na tabela");
		}

		if ((telaCadastro != null) && linhaSelecionada >= 0) {
			telaCadastro.setConsulta(linhaSelecionada, tipoSelecionado);

			this.dispose();
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
		if (tipoSelecionado == ETipo.Quarto) {
			for (int i = 0; i < tableModel.getRowCount(); i++) {
				boolean b = (boolean) tableModel.getValueAt(i, 3);
				if (b) {
					tableModel.setValueAt("Disponível", i, 3);
				} else {
					tableModel.setValueAt("Indisponível", i, 3);
				}
			}
		}
		return tableModel;
	}

	protected void selecionarTipo() {
		tipoSelecionado = (ETipo) comboBoxTipo.getModel().getSelectedItem();
	}

	private boolean isDataValida() {

		boolean valida = true;
		valida = (dataAte.getDate() != null && dataDe.getDate() == null) ? false : valida;
		valida = (dataAte.getDate() == null && dataDe.getDate() != null) ? false : valida;
		if (dataDe.getDate() != null && dataAte.getDate() != null) {
			LocalDate ate = UtilDatas.dateToLocalDate(dataAte.getDate());
			LocalDate de = UtilDatas.dateToLocalDate(dataDe.getDate());
			valida = (ate.isBefore(de)) ? false : valida;
		}
		return valida;
	}

	private void mostrarCampos() {
		if (tipoSelecionado == ETipo.Cliente) {
			dataDe.setEnabled(false);
			dataAte.setEnabled(false);
			textFieldPalavraChave.setEnabled(true);
			comboBoxCampo.setEnabled(true);
			comboBoxTipo.getModel().setSelectedItem(ETipo.Cliente);

			try {
				dao = new ClienteDAO();
				comboBoxCampo.setModel(new DefaultComboBoxModel<Object>(Cliente.getCampos().toArray()));
			} catch (ClassNotFoundException | SQLException e) {
//
			}

		} else if (tipoSelecionado == ETipo.Fornecedor) {
			dataDe.setEnabled(false);
			dataAte.setEnabled(false);
			textFieldPalavraChave.setEnabled(true);
			comboBoxCampo.setEnabled(true);
			comboBoxTipo.getModel().setSelectedItem(ETipo.Fornecedor);

			try {
				dao = new FornecedorDAO();
				comboBoxCampo.setModel(new DefaultComboBoxModel<Object>(Fornecedor.getCampos().toArray()));
			} catch (ClassNotFoundException | SQLException e) {
//
			}
		} else if (tipoSelecionado == ETipo.Produto) {
			dataDe.setEnabled(false);
			dataAte.setEnabled(false);
			textFieldPalavraChave.setEnabled(true);
			comboBoxCampo.setEnabled(true);
			comboBoxTipo.getModel().setSelectedItem(ETipo.Produto);

			try {
				dao = new ProdutoDAO();
				comboBoxCampo.setModel(new DefaultComboBoxModel<Object>(Produto.getCampos().toArray()));
			} catch (ClassNotFoundException | SQLException e) {
//
			}

		} else if (tipoSelecionado == ETipo.Quarto) {
			dataDe.setEnabled(false);
			dataAte.setEnabled(false);
			textFieldPalavraChave.setEnabled(false);
			textFieldPalavraChave.setText("");
			comboBoxCampo.setEnabled(false);
			comboBoxTipo.getModel().setSelectedItem(ETipo.Quarto);

			try {
				dao = new QuartoDAO();
				comboBoxCampo.setModel(new DefaultComboBoxModel<Object>(Quarto.getCampos().toArray()));
			} catch (ClassNotFoundException | SQLException e) {
//
			}
		} else if (tipoSelecionado == ETipo.Reserva) {
			comboBoxTipo.getModel().setSelectedItem(ETipo.Reserva);
			textFieldPalavraChave.setEnabled(true);
			comboBoxCampo.setEnabled(true);

			try {
				dao = new ReservaDAO();
				comboBoxCampo.setModel(new DefaultComboBoxModel<Object>(Locacao.getCampos().toArray()));
			} catch (ClassNotFoundException | SQLException e) {
//
			}
		} else if (tipoSelecionado == ETipo.Locacao) {
			comboBoxTipo.getModel().setSelectedItem(ETipo.Locacao);
			textFieldPalavraChave.setEnabled(true);
			comboBoxCampo.setEnabled(true);
			try {
				dao = new LocacaoDAO();
				comboBoxCampo.setModel(new DefaultComboBoxModel<Object>(Locacao.getCampos().toArray()));
			} catch (ClassNotFoundException | SQLException e) {
//
			}
		}
	}

}
