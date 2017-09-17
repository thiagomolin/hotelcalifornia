package hotel.telas.financeiro;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Locale;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import hotel.classes.DAO.MovimentoFinanceiroEntradaDAO;
import hotel.classes.DAO.MovimentoFinanceiroEntradasSaidasDAO;
import hotel.classes.DAO.MovimentoFinanceiroSaidaDAO;
import hotel.telas.cadastro.Tela;
import hotel.telas.consulta.ETipo;
import hotel.telas.main.TelaPrincipal;
import hotel.util.UtilVector;

public class TelaFinanceiroExtrato extends Tela {
	private static final long serialVersionUID = 1L;

	private TelaPrincipal telaPrincipal;
	private JComboBox<Object> comboBoxTipo;
	private JButton btnMostrar;
	private JButton btnCancelar;
	private JButton btnSair;
	private JDateChooser dataDe;
	private JDateChooser dataAte;
	private JLabel lblTotal;
	private JCheckBox chckbxSintetico;
	private Vector<Vector<Object>> listaDados;
	private Vector<String> listaColunas;
	private JScrollPane scrollPane;
	private JTable table;

	public TelaFinanceiroExtrato(TelaPrincipal telaPrincipal) {
		this.telaPrincipal = telaPrincipal;
		inicializarLayoutEEventos();
	}

	public void inicializarLayoutEEventos() {
		setClosable(true);
		setTitle("Extrato");
		setBounds(100, 100, 540, 440);
		getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		panel.setBounds(376, 0, 148, 410);
		getContentPane().add(panel);
		panel.setLayout(null);

		btnMostrar = new JButton("Mostrar");
		btnMostrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mostrar();
			}
		});
		btnMostrar.setBounds(29, 41, 89, 23);
		panel.add(btnMostrar);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cancelar();
			}
		});
		btnCancelar.setBounds(29, 99, 89, 23);
		panel.add(btnCancelar);

		btnSair = new JButton("Sair");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				sair();
			}
		});
		btnSair.setBounds(29, 365, 89, 23);
		panel.add(btnSair);

		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		panel_1.setBounds(0, 0, 380, 127);
		getContentPane().add(panel_1);

		comboBoxTipo = new JComboBox<Object>();
		comboBoxTipo
				.setModel(new DefaultComboBoxModel<Object>(new String[] { "Entradas", "Saídas", "Entradas e Saídas" }));
		comboBoxTipo.setBounds(90, 39, 120, 20);
		panel_1.add(comboBoxTipo);

		JLabel lblTipo = new JLabel("Tipo");
		lblTipo.setBounds(34, 42, 46, 14);
		panel_1.add(lblTipo);

		dataDe = new JDateChooser();
		dataDe.setBounds(90, 96, 87, 20);
		panel_1.add(dataDe);

		chckbxSintetico = new JCheckBox("Sintético");
		chckbxSintetico.setBounds(245, 38, 97, 23);
		panel_1.add(chckbxSintetico);

		JLabel lblDe = new JLabel("De");
		lblDe.setBounds(34, 102, 46, 14);
		panel_1.add(lblDe);

		JLabel lblAte = new JLabel("Até");
		lblAte.setBounds(198, 102, 46, 14);
		panel_1.add(lblAte);

		dataAte = new JDateChooser();
		dataAte.setBounds(254, 96, 87, 20);
		panel_1.add(dataAte);

		lblTotal = new JLabel("");
		lblTotal.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTotal.setBounds(90, 382, 146, 17);
		getContentPane().add(lblTotal);

		JLabel label = new JLabel("TOTAL:");
		label.setFont(new Font("Tahoma", Font.BOLD, 14));
		label.setBounds(20, 382, 60, 17);
		getContentPane().add(label);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 138, 332, 210);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
	}

	private void sair() {
		telaPrincipal.fecharTela(this);
	}

	private void cancelar() {
		dataAte.setDate(null);
		dataDe.setDate(null);
		lblTotal.setText("");
	}

	public void mostrar() {
		if (comboBoxTipo.getSelectedItem() == "Entradas") {
			mostrarEntrada();
			valorTotal();
		}
		else if (comboBoxTipo.getSelectedItem() == "Saídas") {
			mostrarSaida();
			valorTotal();
		}
		else {
			mostrarTudo();
			valorTotal();
		}
		
	}

	public void mostrarEntrada() {
		if (!chckbxSintetico.isSelected()) {
			if(dataDe.getDate() == null && dataAte.getDate() == null) {
				try {
					MovimentoFinanceiroEntradaDAO dao = new MovimentoFinanceiroEntradaDAO();
					listaDados = UtilVector.rsParaVector(dao.listar());
					listaColunas = dao.getCamposBDAnalitico();
					table.setModel(construirTableModel());
				} catch (ClassNotFoundException | SQLException e) {
					e.printStackTrace();
				}
			}else if(dataDe.getDate() != null && dataAte.getDate() != null){
				try {
					MovimentoFinanceiroEntradaDAO dao = new MovimentoFinanceiroEntradaDAO();
					listaDados = UtilVector.rsParaVector(dao.listarPorDatasAnalitico(dataDe.getDate(), dataAte.getDate()));
					listaColunas = dao.getCamposBDAnalitico();
					table.setModel(construirTableModel());
				} catch (ClassNotFoundException | SQLException e) {
					e.printStackTrace();
				}
			}
		} else {
			if(dataDe.getDate() == null && dataAte.getDate() == null) {
				try {
					MovimentoFinanceiroEntradaDAO dao = new MovimentoFinanceiroEntradaDAO();
					listaDados = UtilVector.rsParaVector(dao.listarSintetico());
					listaColunas = dao.getCamposBDSintetico();
					table.setModel(construirTableModel());
				} catch (ClassNotFoundException | SQLException e) {
					e.printStackTrace();
				}
			}else if(dataDe.getDate() != null && dataAte.getDate() != null){
				try {
					MovimentoFinanceiroEntradaDAO dao = new MovimentoFinanceiroEntradaDAO();
					listaDados = UtilVector.rsParaVector(dao.listarPorDatasSintetico(dataDe.getDate(), dataAte.getDate()));
					listaColunas = dao.getCamposBDSintetico();
					table.setModel(construirTableModel());
				} catch (ClassNotFoundException | SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void mostrarSaida() {
		if (!chckbxSintetico.isSelected()) {
			if(dataDe.getDate() == null && dataAte.getDate() == null) {
				try {
					MovimentoFinanceiroSaidaDAO dao = new MovimentoFinanceiroSaidaDAO();
					listaDados = UtilVector.rsParaVector(dao.listar());
					listaColunas = dao.getCamposBDAnalitico();
					table.setModel(construirTableModel());
				} catch (ClassNotFoundException | SQLException e) {
					e.printStackTrace();
				}
			}else if(dataDe.getDate() != null && dataAte.getDate() != null){
				try {
					MovimentoFinanceiroSaidaDAO dao = new MovimentoFinanceiroSaidaDAO();
					listaDados = UtilVector.rsParaVector(dao.listarPorDatasAnalitico(dataDe.getDate(), dataAte.getDate()));
					listaColunas = dao.getCamposBDAnalitico();
					table.setModel(construirTableModel());
				} catch (ClassNotFoundException | SQLException e) {
					e.printStackTrace();
				}
			}
		} else {
			if(dataDe.getDate() == null && dataAte.getDate() == null) {
				try {
					MovimentoFinanceiroSaidaDAO dao = new MovimentoFinanceiroSaidaDAO();
					listaDados = UtilVector.rsParaVector(dao.listarSintetico());
					listaColunas = dao.getCamposBDSintetico();
					table.setModel(construirTableModel());
				} catch (ClassNotFoundException | SQLException e) {
					e.printStackTrace();
				}
			}else if(dataDe.getDate() != null && dataAte.getDate() != null){
				try {
					MovimentoFinanceiroSaidaDAO dao = new MovimentoFinanceiroSaidaDAO();
					listaDados = UtilVector.rsParaVector(dao.listarPorDatasSintetico(dataDe.getDate(), dataAte.getDate()));
					listaColunas = dao.getCamposBDSintetico();
					table.setModel(construirTableModel());
				} catch (ClassNotFoundException | SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void mostrarTudo() {
		if (!chckbxSintetico.isSelected()) {
			if(dataDe.getDate() == null && dataAte.getDate() == null) {
				try {
					MovimentoFinanceiroEntradasSaidasDAO dao = new MovimentoFinanceiroEntradasSaidasDAO();
					listaDados = UtilVector.rsParaVector(dao.listar());
					listaColunas = dao.getCamposBDAnalitico();
					table.setModel(construirTableModel());
				} catch (ClassNotFoundException | SQLException e) {
					e.printStackTrace();
				}
			}else if(dataDe.getDate() != null && dataAte.getDate() != null){
				try {
					MovimentoFinanceiroEntradasSaidasDAO dao = new MovimentoFinanceiroEntradasSaidasDAO();
					listaDados = UtilVector.rsParaVector(dao.listarPorDatasAnalitico(dataDe.getDate(), dataAte.getDate()));
					listaColunas = dao.getCamposBDAnalitico();
					table.setModel(construirTableModel());
				} catch (ClassNotFoundException | SQLException e) {
					e.printStackTrace();
				}
			}
		} else {
			if(dataDe.getDate() == null && dataAte.getDate() == null) {
				try {
					MovimentoFinanceiroEntradasSaidasDAO dao = new MovimentoFinanceiroEntradasSaidasDAO();
					listaDados = UtilVector.rsParaVector(dao.listarSintetico());
					listaColunas = dao.getCamposBDSintetico();
					table.setModel(construirTableModel());
				} catch (ClassNotFoundException | SQLException e) {
					e.printStackTrace();
				}
			}else if(dataDe.getDate() != null && dataAte.getDate() != null){
				try {
					MovimentoFinanceiroEntradasSaidasDAO dao = new MovimentoFinanceiroEntradasSaidasDAO();
					listaDados = UtilVector.rsParaVector(dao.listarPorDatasSintetico(dataDe.getDate(), dataAte.getDate()));
					listaColunas = dao.getCamposBDSintetico();
					table.setModel(construirTableModel());
				} catch (ClassNotFoundException | SQLException e) {
					e.printStackTrace();
				}
			}
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

	public void valorTotal() {
		float soma = 0;
		for (Vector<Object> lista : listaDados) {
			soma += (float) lista.get(1);
		}
		
		lblTotal.setText(String.format(Locale.ROOT, "%.2f", soma));
	}

	public void setConsulta(Long id, ETipo tipo) {

	}
}
