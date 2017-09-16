package hotel.telas.consulta;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;

import hotel.classes.DAO.MovimentoEstoqueDAO;
import hotel.util.UtilVector;

public class TelaConsultaEstoque extends JFrame {
	private static final long serialVersionUID = 1L;

	private JTextField textFieldPalavraChave;
	private JComboBox<Object> comboBoxCampo;
	private JButton btnCancelar;

	private Vector<Vector<Object>> listaDados;
	private Vector<String> listaColunas;

	private JTable table;

	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JRadioButton rdbtnAnalitico;
	private JRadioButton rdbtnSintetico;

	public TelaConsultaEstoque() {
		super();
		inicializarLayoutEEventos();
		inicializarComboBoxCampo();
		rdbtnSintetico.setSelected(true);
	}

	private void inicializarLayoutEEventos() {
		setTitle("Consulta de Estoque");
		setBounds(100, 100, 750, 450);
		getContentPane().setLayout(null);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 0, 724, 162);
		panel_1.setLayout(null);
		panel_1.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		getContentPane().add(panel_1);

		JLabel lblPalavrachave = new JLabel("Palavra-Chave");
		lblPalavrachave.setBounds(10, 61, 89, 14);
		panel_1.add(lblPalavrachave);

		textFieldPalavraChave = new JTextField();
		textFieldPalavraChave.setBounds(104, 61, 511, 20);
		panel_1.add(textFieldPalavraChave);
		textFieldPalavraChave.setColumns(10);

		JLabel lblCampo = new JLabel("Campo");
		lblCampo.setBounds(348, 14, 57, 14);
		panel_1.add(lblCampo);

		comboBoxCampo = new JComboBox<Object>();
		comboBoxCampo.setBounds(415, 11, 160, 20);
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
		btnMostrar.setBounds(625, 10, 89, 23);
		panel_1.add(btnMostrar);

		rdbtnSintetico = new JRadioButton("Sintético");
		buttonGroup.add(rdbtnSintetico);
		rdbtnSintetico.setBounds(24, 10, 102, 23);
		panel_1.add(rdbtnSintetico);

		rdbtnAnalitico = new JRadioButton("Analítico");
		buttonGroup.add(rdbtnAnalitico);
		rdbtnAnalitico.setBounds(128, 10, 102, 23);
		panel_1.add(rdbtnAnalitico);

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

		scrollPane.setViewportView(table);

	}
	
	private void inicializarComboBoxCampo() {
		String[] campos = {"Produto", "Usuario"};
		comboBoxCampo.setModel(new DefaultComboBoxModel<Object>(campos));
	}
	
	protected void cancelar() {
		comboBoxCampo.getModel().setSelectedItem(null);
		table.setModel(new DefaultTableModel());
		textFieldPalavraChave.setText("");
	}

	protected void mostrar() {
		if(rdbtnAnalitico.isSelected()) {
			mostrarAnalitico();
		}else {
			mostrarSintetico();
		}		
	}

	private void mostrarAnalitico() {
		try {
			MovimentoEstoqueDAO dao = new MovimentoEstoqueDAO();
			if (textFieldPalavraChave.getText().isEmpty()) {
				listaDados = UtilVector.rsParaVector(dao.listar());
				listaColunas = dao.getCamposBDAnalitico();
				table.setModel(construirTableModel());
			} else {
				ResultSet rs = dao.listarFiltro(((String) comboBoxCampo.getModel().getSelectedItem()),
						textFieldPalavraChave.getText());
				listaDados = UtilVector.rsParaVector(rs);
				listaColunas = dao.getCamposBDAnalitico();
				table.setModel(construirTableModel());
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}		
	}

	private void mostrarSintetico() {
		try {
			MovimentoEstoqueDAO dao = new MovimentoEstoqueDAO();
			listaColunas = dao.getCamposBDSintetico();
			if (textFieldPalavraChave.getText().isEmpty()) {
				listaDados = UtilVector.rsParaVector(dao.listarSintetico());
				table.setModel(construirTableModel());
			} else {
				ResultSet rs = dao.listarFiltro(((String) comboBoxCampo.getModel().getSelectedItem()),
						textFieldPalavraChave.getText());
				listaDados = UtilVector.rsParaVector(rs);
				table.setModel(construirTableModel());
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
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
		return tableModel;
	}

}
