package hotel.telas.estoque;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;

public class TelaEntradaEstoque extends JInternalFrame {
	private static final long serialVersionUID = 1L;
	
	private JTextField textFieldQuantidade;
	
	private JComboBox<Object> comboBoxProduto;
	private JComboBox<Object> comboBoxFornecedor;

	
	public TelaEntradaEstoque() {
		setClosable(true);
		setTitle("Entrada no estoque por nota fiscal");
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
		panel_1.setBounds(0, 0, 380, 65);
		getContentPane().add(panel_1);
		
		JLabel lblCadastroDeCliente = new JLabel("Entrada no estoque por nota fiscal");
		lblCadastroDeCliente.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblCadastroDeCliente.setBounds(10, 11, 307, 17);
		panel_1.add(lblCadastroDeCliente);
		
		JLabel lblNome = new JLabel("Produto");
		lblNome.setBounds(64, 123, 63, 14);
		getContentPane().add(lblNome);
		
		JLabel lblCpf = new JLabel("Quantidade");
		lblCpf.setBounds(64, 159, 63, 14);
		getContentPane().add(lblCpf);
		
		comboBoxProduto = new JComboBox<Object>();
		comboBoxProduto.setBounds(154, 124, 92, 20);
		getContentPane().add(comboBoxProduto);
		
		JButton buttonSelecionarProduto = new JButton("...");
		buttonSelecionarProduto.setBounds(250, 123, 18, 23);
		getContentPane().add(buttonSelecionarProduto);
		
		textFieldQuantidade = new JTextField();
		textFieldQuantidade.setBounds(154, 156, 114, 20);
		getContentPane().add(textFieldQuantidade);
		textFieldQuantidade.setColumns(10);
		
		JLabel lblFornecedor = new JLabel("Fornecedor");
		lblFornecedor.setBounds(64, 89, 63, 14);
		getContentPane().add(lblFornecedor);
		
		comboBoxFornecedor = new JComboBox<Object>();
		comboBoxFornecedor.setBounds(154, 90, 92, 20);
		getContentPane().add(comboBoxFornecedor);
		
		JButton buttonSelecionarFornecedor = new JButton("...");
		buttonSelecionarFornecedor.setBounds(250, 89, 18, 23);
		getContentPane().add(buttonSelecionarFornecedor);

	}
}
