package hotel.telas.financeiro;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EtchedBorder;

import com.toedter.calendar.JDateChooser;

import hotel.telas.cadastro.Tela;
import hotel.telas.consulta.ETipos;
import hotel.telas.main.TelaPrincipal;

public class TelaFinanceiroExtrato extends Tela {
	private static final long serialVersionUID = 1L;
	
	private TelaPrincipal telaPrincipal;
	
	private JTable table;
	private JComboBox<Object> comboBoxTipo;
	private JButton btnMostrar;
	private JButton btnCancelar;
	private JButton btnSair;
	private JDateChooser dataDe;
	private JDateChooser dataAte;
	private JLabel lblTotal;
	private JCheckBox chckbxSintetico;
	
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
		comboBoxTipo.setModel(new DefaultComboBoxModel<Object>(new String[] {"Entradas", "Saídas", "Entradas e Saídas"}));
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
		
		table = new JTable();
		table.setBounds(25, 138, 341, 170);
		getContentPane().add(table);
		
		lblTotal = new JLabel("");
		lblTotal.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTotal.setBounds(90, 382, 146, 17);
		getContentPane().add(lblTotal);
		
		JLabel label = new JLabel("TOTAL:");
		label.setFont(new Font("Tahoma", Font.BOLD, 14));
		label.setBounds(20, 382, 60, 17);
		getContentPane().add(label);
	}

	private void sair() {
		telaPrincipal.fecharTela(this);
	}
	
	private void cancelar() {
		dataAte.setDate(null);
		dataDe.setDate(null);
		lblTotal.setText("");
	}

	public void setConsulta(Long id, ETipos tipo) {
			
	}
	
	

}
