package hotel.telas.os;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import java.awt.CardLayout;
import javax.swing.Box;
import javax.swing.JSeparator;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JTextArea;

public class TelaConsultaOs extends JInternalFrame {

	
	public TelaConsultaOs() {
		setClosable(true);
		setTitle("Cadastro OS");
		setBounds(100, 100, 540, 440);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		panel.setBounds(376, 0, 148, 410);
		getContentPane().add(panel);
		
		JButton button = new JButton("Excluir");
		button.setBounds(29, 156, 89, 23);
		panel.add(button);
		
		JButton button_1 = new JButton("Alterar");
		button_1.setBounds(29, 122, 89, 23);
		panel.add(button_1);
		
		JButton button_2 = new JButton("Incluir");
		button_2.setBounds(29, 41, 89, 23);
		panel.add(button_2);
		
		JButton button_3 = new JButton("Cancelar");
		button_3.setBounds(29, 263, 89, 23);
		panel.add(button_3);
		
		JButton button_4 = new JButton("Sair");
		button_4.setBounds(29, 365, 89, 23);
		panel.add(button_4);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		panel_1.setBounds(0, 0, 380, 92);
		getContentPane().add(panel_1);
		
		JLabel lblCdigo = new JLabel("Código");
		lblCdigo.setBounds(113, 62, 46, 14);
		panel_1.add(lblCdigo);
		
		JComboBox comboBoxCodigo = new JComboBox();
		comboBoxCodigo.setBounds(158, 59, 92, 20);
		panel_1.add(comboBoxCodigo);
		
		JButton buttonConsulta = new JButton("...");
		buttonConsulta.setBounds(254, 58, 18, 23);
		panel_1.add(buttonConsulta);
		
		JLabel lblCadastroDeCliente = new JLabel("Cadastro de OS");
		lblCadastroDeCliente.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblCadastroDeCliente.setBounds(10, 11, 146, 17);
		panel_1.add(lblCadastroDeCliente);
		
		JLabel lblNome = new JLabel("Serviço");
		lblNome.setBounds(30, 123, 63, 14);
		getContentPane().add(lblNome);
		
		JLabel lblCpf = new JLabel("Quarto");
		lblCpf.setBounds(30, 148, 46, 14);
		getContentPane().add(lblCpf);
		
		JComboBox comboBoxServico = new JComboBox();
		comboBoxServico.setBounds(103, 120, 111, 20);
		getContentPane().add(comboBoxServico);
		
		JComboBox comboBoxQuarto = new JComboBox();
		comboBoxQuarto.setBounds(103, 145, 111, 20);
		getContentPane().add(comboBoxQuarto);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(30, 234, 314, 155);
		getContentPane().add(textArea);
		
		JLabel lblDescrioDoServio = new JLabel("Descrição do Serviço");
		lblDescrioDoServio.setBounds(30, 209, 130, 14);
		getContentPane().add(lblDescrioDoServio);

	}
}
