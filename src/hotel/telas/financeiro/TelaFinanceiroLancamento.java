package hotel.telas.financeiro;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;

public class TelaFinanceiroLancamento extends JInternalFrame {
		private static final long serialVersionUID = 1L;
		
	private JTextField textFieldQuantidade;
	
	private JComboBox<Object> comboBoxCodigo;
	private JComboBox<Object> comboBoxProduto;

	
	public TelaFinanceiroLancamento() {
		setClosable(true);
		setTitle("Novo Lançamento");
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
		
		JLabel lblCdigo = new JLabel("C\u00F3digo da Reserva");
		lblCdigo.setBounds(49, 62, 99, 14);
		panel_1.add(lblCdigo);
		
		comboBoxCodigo = new JComboBox<Object>();
		comboBoxCodigo.setBounds(158, 59, 92, 20);
		panel_1.add(comboBoxCodigo);
		
		JButton buttonConsulta = new JButton("...");
		buttonConsulta.setBounds(254, 58, 18, 23);
		panel_1.add(buttonConsulta);
		
		JLabel lblCadastroDeCliente = new JLabel("Novo Lançamento");
		lblCadastroDeCliente.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblCadastroDeCliente.setBounds(10, 11, 146, 17);
		panel_1.add(lblCadastroDeCliente);
		
		JLabel lblNewLabel = new JLabel("Produto");
		lblNewLabel.setBounds(42, 114, 46, 14);
		getContentPane().add(lblNewLabel);
		
		comboBoxProduto = new JComboBox<Object> ();
		comboBoxProduto.setBounds(127, 111, 181, 20);
		getContentPane().add(comboBoxProduto);
		
		JButton buttonConsulta2 = new JButton("...");
		buttonConsulta2.setBounds(311, 110, 18, 23);
		getContentPane().add(buttonConsulta2);
		
		textFieldQuantidade = new JTextField();
		textFieldQuantidade.setBounds(127, 153, 181, 20);
		getContentPane().add(textFieldQuantidade);
		textFieldQuantidade.setColumns(10);
		
		JLabel lblQuantidade = new JLabel("Quantidade");
		lblQuantidade.setBounds(42, 156, 75, 14);
		getContentPane().add(lblQuantidade);

	}
}
