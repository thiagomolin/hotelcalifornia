package hotel.telas.os;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

public class TelaOs extends JInternalFrame {
	private static final long serialVersionUID = 1L;
	
	private JComboBox<Object> comboBoxCodigo;

	public TelaOs() {
		setClosable(true);
		setTitle("Consultar OS");
		setBounds(100, 100, 540, 440);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		panel.setBounds(376, 0, 148, 410);
		getContentPane().add(panel);
		
		JButton btnMostrar = new JButton("Mostrar");
		btnMostrar.setBounds(29, 41, 89, 23);
		panel.add(btnMostrar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(29, 100, 89, 23);
		panel.add(btnCancelar);
		
		JButton btnSair = new JButton("Sair");
		btnSair.setBounds(29, 365, 89, 23);
		panel.add(btnSair);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		panel_1.setBounds(0, 0, 380, 92);
		getContentPane().add(panel_1);
		
		JLabel lblCdigo = new JLabel("Código");
		lblCdigo.setBounds(113, 62, 46, 14);
		panel_1.add(lblCdigo);
		
		comboBoxCodigo = new JComboBox<Object>();
		comboBoxCodigo.setBounds(158, 59, 92, 20);
		panel_1.add(comboBoxCodigo);
		
		JButton buttonConsulta = new JButton("...");
		buttonConsulta.setBounds(254, 58, 18, 23);
		panel_1.add(buttonConsulta);
		
		JLabel lblCadastroDeCliente = new JLabel("Consultar OS");
		lblCadastroDeCliente.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblCadastroDeCliente.setBounds(10, 11, 146, 17);
		panel_1.add(lblCadastroDeCliente);
		
		JLabel lblNome = new JLabel("Serviço");
		lblNome.setBounds(30, 123, 63, 14);
		getContentPane().add(lblNome);
		
		JLabel lblCpf = new JLabel("Quarto");
		lblCpf.setBounds(30, 148, 46, 14);
		getContentPane().add(lblCpf);
		
		JLabel lblDescrioDoServio = new JLabel("Descrição do Serviço");
		lblDescrioDoServio.setBounds(30, 190, 130, 14);
		getContentPane().add(lblDescrioDoServio);
		
		JLabel lblServico = new JLabel("");
		lblServico.setBounds(178, 123, 46, 14);
		getContentPane().add(lblServico);
		
		JLabel labelQuarto = new JLabel("");
		labelQuarto.setBounds(178, 148, 46, 14);
		getContentPane().add(labelQuarto);
		
		JLabel labelDescricao = new JLabel("");
		labelDescricao.setBounds(178, 190, 148, 60);
		getContentPane().add(labelDescricao);
		
		JLabel lblResponsvel = new JLabel("Responsável");
		lblResponsvel.setBounds(30, 269, 130, 14);
		getContentPane().add(lblResponsvel);
		
		JLabel labelResponsavel = new JLabel("");
		labelResponsavel.setBounds(170, 269, 46, 14);
		getContentPane().add(labelResponsavel);

	}
}
