package hotel.telas.consulta;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import hotel.classes.Cliente;
import hotel.classes.Fornecedor;
import hotel.classes.Funcionario;
import hotel.classes.Produto;
import hotel.classes.Quarto;
import hotel.classes.Locacao;
import hotel.classes.DAO.ClienteDAO;
import hotel.classes.DAO.DAO;
import hotel.classes.DAO.FornecedorDAO;
import hotel.classes.DAO.FuncionarioDAO;
import hotel.classes.DAO.ProdutoDAO;
import hotel.classes.DAO.QuartoDAO;
import hotel.classes.DAO.LocacaoDAO;
import hotel.telas.cadastro.Tela;
import hotel.util.UtilVector;

public class TelaConsultaGeral extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private JTextField textFieldPalavraChave;
	
	private JComboBox<ETipos> comboBoxTipo;
	private JComboBox<Object> comboBoxCampo;
	
	private JDateChooser dateChooserDe;
	private JDateChooser dateChooserAte;
			
	private JButton btnSelecionar;
	private JButton btnCancelar;
	
	private JPanel panel;

	private DAO dao;
	private Tela telaCadastro;

	private Vector<Vector<Object>> listaDados;
	private Vector<String> listaColunas;

	private ETipos tipoSelecionado = null;
	private JTable table;

	public TelaConsultaGeral() {
		inicializarLayoutEEventos();
		comboBoxTipo.getModel().setSelectedItem(null);
		panel.setVisible(false);
		btnCancelar.setVisible(true);
	}

	public TelaConsultaGeral(ETipos tipoSelecionado, Tela telaCadastro) {
		this.tipoSelecionado = tipoSelecionado;
		this.telaCadastro = telaCadastro;
		inicializarLayoutEEventos();
		mostrarCampos();
		panel.setVisible(false);
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

		comboBoxTipo = new JComboBox<ETipos>();
		comboBoxTipo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selecionarTipo();
				mostrarCampos();
			}
		});
		comboBoxTipo.setModel(new DefaultComboBoxModel<ETipos>(new ETipos[] { ETipos.Cliente, ETipos.Fornecedor,
				ETipos.Funcionario, ETipos.Reserva, ETipos.Locacao, ETipos.Produto, ETipos.Quarto }));
		comboBoxTipo.setBounds(45, 11, 183, 20);
		panel_1.add(comboBoxTipo);

		if (tipoSelecionado != null) {
			comboBoxTipo.setEnabled(false);
		}

		JLabel lblTipo = new JLabel("Tipo");
		lblTipo.setBounds(10, 14, 46, 14);
		panel_1.add(lblTipo);

		dateChooserDe = new JDateChooser();
		dateChooserDe.setBounds(65, 93, 87, 20);
		panel_1.add(dateChooserDe);

		JLabel lblDe = new JLabel("De");
		lblDe.setBounds(21, 93, 46, 14);
		panel_1.add(lblDe);

		JLabel lblAt = new JLabel("Até");
		lblAt.setBounds(225, 93, 30, 14);
		panel_1.add(lblAt);

		dateChooserAte = new JDateChooser();
		dateChooserAte.setBounds(265, 93, 87, 20);
		panel_1.add(dateChooserAte);

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

		panel = new JPanel();
		panel.setBounds(501, 73, 114, 80);
		panel_1.add(panel);
		panel.setLayout(null);
		panel.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));

		JRadioButton rdbtnAtiva = new JRadioButton("Ativa");
		rdbtnAtiva.setBounds(6, 7, 102, 23);
		panel.add(rdbtnAtiva);

		JRadioButton rdbtnInativa = new JRadioButton("Inativa");
		rdbtnInativa.setBounds(6, 30, 102, 23);
		panel.add(rdbtnInativa);

		JRadioButton rdbtnFinalizada = new JRadioButton("Finalizada");
		rdbtnFinalizada.setBounds(6, 51, 102, 23);
		panel.add(rdbtnFinalizada);

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
				try {
					listaDados = UtilVector.rsParaVector(dao.listar());
					listaColunas = dao.getCamposBD();
					table.setModel(construirTableModel());
					statusBtnSelecionar();
				} catch (ClassNotFoundException | SQLException e) {
					e.printStackTrace();
				}
			} 
		}else {
			try {
				ResultSet rs = dao.listarFiltro(((String) comboBoxCampo.getModel().getSelectedItem()), textFieldPalavraChave.getText());
				listaDados = UtilVector.rsParaVector(rs);
				listaColunas = dao.getCamposBD();
				table.setModel(construirTableModel());
				statusBtnSelecionar();				
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
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
		if (tipoSelecionado == ETipos.Quarto) {
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
		tipoSelecionado = (ETipos) comboBoxTipo.getModel().getSelectedItem();
	}

	private void mostrarCampos() {
		if (tipoSelecionado == ETipos.Cliente) {
			dateChooserDe.setEnabled(false);
			dateChooserAte.setEnabled(false);
			panel.setVisible(false);
			textFieldPalavraChave.setEnabled(true);
			comboBoxCampo.setEnabled(true);
			comboBoxTipo.getModel().setSelectedItem(ETipos.Cliente);
			try {
				dao = new ClienteDAO();
				comboBoxCampo.setModel(new DefaultComboBoxModel<Object>(Cliente.getCampos().toArray()));
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}

		} else if (tipoSelecionado == ETipos.Fornecedor) {
			panel.setVisible(false);
			dateChooserDe.setEnabled(false);
			dateChooserAte.setEnabled(false);
			textFieldPalavraChave.setEnabled(true);
			comboBoxCampo.setEnabled(true);
			comboBoxTipo.getModel().setSelectedItem(ETipos.Fornecedor);
			try {
				dao = new FornecedorDAO();
				comboBoxCampo.setModel(new DefaultComboBoxModel<Object>(Fornecedor.getCampos().toArray()));
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		} else if (tipoSelecionado == ETipos.Funcionario) {
			panel.setVisible(false);
			dateChooserDe.setEnabled(false);
			dateChooserAte.setEnabled(false);
			textFieldPalavraChave.setEnabled(true);
			comboBoxCampo.setEnabled(true);
			comboBoxTipo.getModel().setSelectedItem(ETipos.Funcionario);
			try {
				dao = new FuncionarioDAO();
				comboBoxCampo.setModel(new DefaultComboBoxModel<Object>(Funcionario.getCampos().toArray()));
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}

		} else if (tipoSelecionado == ETipos.Produto) {
			panel.setVisible(false);
			dateChooserDe.setEnabled(false);
			dateChooserAte.setEnabled(false);
			textFieldPalavraChave.setEnabled(true);
			comboBoxCampo.setEnabled(true);
			comboBoxTipo.getModel().setSelectedItem(ETipos.Produto);
			try {
				dao = new ProdutoDAO();
				comboBoxCampo.setModel(new DefaultComboBoxModel<Object>(Produto.getCampos().toArray()));
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}

		} else if (tipoSelecionado == ETipos.Quarto) {
			panel.setVisible(false);
			dateChooserDe.setEnabled(false);
			dateChooserAte.setEnabled(false);
			textFieldPalavraChave.setEnabled(false);
			comboBoxCampo.setEnabled(false);
			comboBoxTipo.getModel().setSelectedItem(ETipos.Quarto);
			try {
				dao = new QuartoDAO();
				comboBoxCampo.setModel(new DefaultComboBoxModel<Object>(Quarto.getCampos().toArray()));
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		} else if (tipoSelecionado == ETipos.Reserva) {
			panel.setVisible(true);
			comboBoxTipo.getModel().setSelectedItem(ETipos.Reserva);
			textFieldPalavraChave.setEnabled(true);
			comboBoxCampo.setEnabled(true);
			try {
				dao = new LocacaoDAO();
				comboBoxCampo.setModel(new DefaultComboBoxModel<Object>(Locacao.getCampos().toArray()));
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		}

	}
}
