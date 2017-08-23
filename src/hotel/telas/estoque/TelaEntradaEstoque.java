package hotel.telas.estoque;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;

import hotel.classes.MovimentoEstoque;
import hotel.classes.MovimentoFinanceiroSaida;
import hotel.classes.Produto;
import hotel.classes.DAO.MovimentoEstoqueDAO;
import hotel.classes.DAO.MovimentoFinanceiroSaidaDAO;
import hotel.classes.DAO.ProdutoDAO;
import hotel.telas.cadastro.Tela;
import hotel.telas.consulta.ETipos;
import hotel.telas.consulta.TelaConsultaGeral;
import hotel.telas.main.TelaPrincipal;

public class TelaEntradaEstoque extends Tela {
	private static final long serialVersionUID = 1L;
	
	private JTextField textFieldQuantidade;
	
	private JComboBox<Object> comboBoxProduto;
	
	private TelaPrincipal telaPrincipal;
	
	public TelaEntradaEstoque(TelaPrincipal telaPrincipal) {
		super();
		this.telaPrincipal = telaPrincipal;
		inicializarLayoutEEventos();
		inicializarComboBoxProduto();
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
		btnProcessar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				processar();
			}
		});
		btnProcessar.setBounds(29, 41, 89, 23);
		panel.add(btnProcessar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(29, 100, 89, 23);
		panel.add(btnCancelar);
		
		JButton btnSair = new JButton("Sair");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sair();
			}
		});
		btnSair.setBounds(29, 365, 89, 23);
		panel.add(btnSair);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		panel_1.setBounds(0, 0, 380, 65);
		getContentPane().add(panel_1);
		
		JLabel lblNome = new JLabel("Produto");
		lblNome.setBounds(69, 87, 63, 14);
		getContentPane().add(lblNome);
		
		JLabel lblCpf = new JLabel("Quantidade");
		lblCpf.setBounds(69, 123, 63, 14);
		getContentPane().add(lblCpf);
		
		comboBoxProduto = new JComboBox<Object>();
		comboBoxProduto.setBounds(159, 88, 92, 20);
		getContentPane().add(comboBoxProduto);
		
		JButton buttonSelecionarProduto = new JButton("...");
		buttonSelecionarProduto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				consultar(ETipos.Produto);
			}
		});
		buttonSelecionarProduto.setBounds(255, 87, 18, 23);
		getContentPane().add(buttonSelecionarProduto);
		
		textFieldQuantidade = new JTextField();
		textFieldQuantidade.setBounds(159, 120, 114, 20);
		getContentPane().add(textFieldQuantidade);
		textFieldQuantidade.setColumns(10);

	}

	protected void processar() {
		if (formularioValido()) {
			gerarEstoque();
		}
	}
	
	private boolean formularioValido() {
		// TODO Auto-generated method stub
		return true;
	}

	private void gerarEstoque() {
		long fkProduto = getSelectedComboBoxProduto().getId();
		long fkUsuario = telaPrincipal.getUsuarioLogado();
		long fkTipoMovimento = 5; // Entrada por nota fiscal
		int nrQuantidade = getQuantidade();
		LocalDate dtAtual = LocalDate.now();
		
		MovimentoEstoque m = new MovimentoEstoque(fkProduto, fkUsuario, fkTipoMovimento, nrQuantidade, dtAtual);
		try {
			MovimentoEstoqueDAO mdao = new MovimentoEstoqueDAO();
			mdao.inserir(m);			
			
			ProdutoDAO p = new ProdutoDAO();
			float valorDeCompra = p.selecionar(fkProduto).getNrValorCompra();
			float total = ((float)nrQuantidade) * valorDeCompra;

			gerarFinanceiro(total);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void gerarFinanceiro(float total) {
		long fkProduto = getSelectedComboBoxProduto().getId();
		long fkUsuario = telaPrincipal.getUsuarioLogado();
		float nrValor = total;
		LocalDate dtAtual = LocalDate.now();
		
		MovimentoFinanceiroSaida mvmts = new MovimentoFinanceiroSaida(fkProduto, fkUsuario, nrValor, dtAtual);

		
		try {
			MovimentoFinanceiroSaidaDAO mvmtdao = new MovimentoFinanceiroSaidaDAO();
			mvmtdao.inserir(mvmts);			
		}catch (Exception e) {
			e.printStackTrace();			
		}
	}

	

	protected void sair() {
		telaPrincipal.fecharTela(this);
	}
	
	
	public void inicializarComboBoxProduto() {
		try {
			ProdutoDAO cl = new ProdutoDAO();
			List<Produto> produtos = cl.getLista();
			comboBoxProduto.setModel(new DefaultComboBoxModel<Object>(produtos.toArray()));
		} catch (ClassNotFoundException | SQLException ex) {
			ex.printStackTrace();
			ex.printStackTrace();
		}
	}
	
	protected void consultar(ETipos tipo) {
		TelaConsultaGeral telaConsulta = new TelaConsultaGeral(tipo, this);
		telaConsulta.setVisible(true);
	}
	
	
	
	
	
	public int getQuantidade() {
		return Integer.parseInt(textFieldQuantidade.getText());
	}
		
	public Produto getSelectedComboBoxProduto() {
		return (Produto) comboBoxProduto.getModel().getSelectedItem();
	}
	
	
	
	
	
	@Override
	public void setConsulta(Long id, ETipos tipo) {
		try {
			ProdutoDAO p = new ProdutoDAO();
			comboBoxProduto.getModel().setSelectedItem(p.selecionar(id));
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}


}
