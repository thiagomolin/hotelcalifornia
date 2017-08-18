package hotel.telas.estoque;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;

public class TelaEstoque extends JInternalFrame {
	private static final long serialVersionUID = 1L;
	
	private JTextField textFieldQuantidade;
	
	private JComboBox<Object> comboBoxCodigo;
	private JComboBox<Object> comboBoxProduto;

	
	public TelaEstoque() {
		setClosable(true);
		setTitle("Estoque");
		setBounds(100, 100, 540, 440);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		panel.setBounds(376, 0, 148, 410);
		getContentPane().add(panel);
		
		JButton btnProcessar = new JButton("Processar");
		btnProcessar.setBounds(29, 41, 89, 23);
		panel.add(btnProcessar);
		
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
		
		JLabel lblCdigo = new JLabel("Tipo da Movimentação");
		lblCdigo.setBounds(25, 62, 115, 14);
		panel_1.add(lblCdigo);
		
		comboBoxCodigo = new JComboBox<Object>();
		comboBoxCodigo.setBounds(158, 59, 92, 20);
		panel_1.add(comboBoxCodigo);
		
		JButton buttonConsulta = new JButton("...");
		buttonConsulta.setBounds(254, 58, 18, 23);
		panel_1.add(buttonConsulta);
		
		JLabel lblCadastroDeCliente = new JLabel("Estoque");
		lblCadastroDeCliente.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblCadastroDeCliente.setBounds(10, 11, 146, 17);
		panel_1.add(lblCadastroDeCliente);
		
		JLabel lblNome = new JLabel("Produto");
		lblNome.setBounds(30, 123, 63, 14);
		getContentPane().add(lblNome);
		
		JLabel lblCpf = new JLabel("Quantidade");
		lblCpf.setBounds(30, 159, 63, 14);
		getContentPane().add(lblCpf);
		
		comboBoxProduto = new JComboBox<Object>();
		comboBoxProduto.setBounds(120, 124, 92, 20);
		getContentPane().add(comboBoxProduto);
		
		JButton buttonConsultaProduto = new JButton("...");
		buttonConsultaProduto.setBounds(216, 123, 18, 23);
		getContentPane().add(buttonConsultaProduto);
		
		textFieldQuantidade = new JTextField();
		textFieldQuantidade.setBounds(120, 156, 114, 20);
		getContentPane().add(textFieldQuantidade);
		textFieldQuantidade.setColumns(10);

	}
}
