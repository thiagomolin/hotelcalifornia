package hotel.telas.financeiro;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EtchedBorder;

public class TelaFinanceiroFecharConta extends JInternalFrame {
	private static final long serialVersionUID = 1L;
	
	private JTable table;

	private JComboBox<Object> comboBoxCodigo;
	
	public TelaFinanceiroFecharConta() {
		setClosable(true);
		setTitle("Fechar Conta");
		setBounds(100, 100, 540, 440);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		panel.setBounds(376, 0, 148, 410);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JButton btnProcessar = new JButton("Processar");
		btnProcessar.setBounds(29, 41, 89, 23);
		panel.add(btnProcessar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(29, 99, 89, 23);
		panel.add(btnCancelar);
		
		JButton btnSair = new JButton("Sair");
		btnSair.setBounds(29, 365, 89, 23);
		panel.add(btnSair);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		panel_1.setBounds(0, 0, 380, 92);
		getContentPane().add(panel_1);
		
		JLabel lblCodigo = new JLabel("Código da Locação");
		lblCodigo.setBounds(49, 62, 99, 14);
		panel_1.add(lblCodigo);
		
		comboBoxCodigo = new JComboBox<Object>();
		comboBoxCodigo.setBounds(158, 59, 92, 20);
		panel_1.add(comboBoxCodigo);
		
		JButton buttonConsulta = new JButton("...");
		buttonConsulta.setBounds(254, 58, 18, 23);
		panel_1.add(buttonConsulta);
		
		table = new JTable();
		table.setBounds(20, 103, 341, 170);
		getContentPane().add(table);
		
		JLabel lblTotal = new JLabel("");
		lblTotal.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTotal.setBounds(215, 303, 146, 17);
		getContentPane().add(lblTotal);
		
		JLabel label = new JLabel("TOTAL:");
		label.setFont(new Font("Tahoma", Font.BOLD, 14));
		label.setBounds(46, 303, 146, 17);
		getContentPane().add(label);

	}
}
