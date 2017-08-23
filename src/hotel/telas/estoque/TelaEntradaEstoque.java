package hotel.telas.estoque;

import java.sql.SQLException;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;

import hotel.classes.Fornecedor;
import hotel.classes.DAO.FornecedorDAO;

public class TelaEntradaEstoque extends JInternalFrame {
	private static final long serialVersionUID = 1L;
	
	private JTextField textFieldQuantidade;
	
	private JComboBox<Object> comboBoxProduto;
	private JComboBox<Object> comboBoxFornecedor;

	
	public TelaEntradaEstoque() {
		inicializarLayoutEEventos();
	}
	
	private void inicializarLayoutEEventos() {
		setClosable(true);
		setTitle("Entrada no estoque por aquisição");
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


	public void inicializarComboBoxFornecedor() {
		try {
			FornecedorDAO cl = new FornecedorDAO();
			List<Fornecedor> fornecedores = cl.getLista();
			comboBoxFornecedor.setModel(new DefaultComboBoxModel<Object>(fornecedores.toArray()));
		} catch (ClassNotFoundException | SQLException ex) {
			ex.printStackTrace();
			ex.printStackTrace();
		}
	}


}
