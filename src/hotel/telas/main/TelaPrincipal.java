package hotel.telas.main;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTabbedPane;

import hotel.telas.cadastro.TelaCadastroCliente;
import hotel.telas.cadastro.TelaCadastroFornecedor;
import hotel.telas.cadastro.TelaCadastroLocacao;
import hotel.telas.cadastro.TelaCadastroProduto;
import hotel.telas.cadastro.TelaCadastroQuarto;
import hotel.telas.cadastro.TelaCadastroReserva;
import hotel.telas.consulta.ETipo;
import hotel.telas.consulta.TelaConsultaEstoque;
import hotel.telas.consulta.TelaConsultaGeral;
import hotel.telas.estoque.TelaAcertoEstoque;
import hotel.telas.estoque.TelaEntradaEstoque;
import hotel.telas.financeiro.TelaFinanceiroExtrato;
import hotel.telas.financeiro.TelaFinanceiroFecharConta;
import hotel.telas.financeiro.TelaFinanceiroLancamento;

public class TelaPrincipal extends JFrame {
	private static final long serialVersionUID = 1L;

	private JTabbedPane tabbedPane;
	
	private long usuarioLogado;

	public TelaPrincipal() {
		super();
		usuarioLogado = 1; //TODO: Alterar para valor dinamico
		inicializarLayoutEEventos();
	}

	// LAYOUT E EVENTOS
	private void inicializarLayoutEEventos() {
		// Frame
		setResizable(false);
		setBounds(100, 100, 550, 520);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// Frame
		// Tabbed Pane
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 537, 462);
		getContentPane().add(tabbedPane);
		// Tabbed Pane

		criarMenus();
	}

	private void criarMenus() {
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnCadastro = new JMenu("Cadastro");
		menuBar.add(mnCadastro);

		JMenuItem mntmCliente = new JMenuItem("Cliente");
		mntmCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				criarTela(ETipo.Cliente);
			}
		});
		mnCadastro.add(mntmCliente);

		JMenuItem mntmFornecedor = new JMenuItem("Fornecedor");
		mntmFornecedor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				criarTela(ETipo.Fornecedor);
			}
		});
		mnCadastro.add(mntmFornecedor);

		JMenuItem mntmProduto = new JMenuItem("Produto");
		mntmProduto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				criarTela(ETipo.Produto);
			}
		});
		mnCadastro.add(mntmProduto);

		JMenuItem mntmQuarto = new JMenuItem("Quarto");
		mntmQuarto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				criarTela(ETipo.Quarto);
			}
		});
		mnCadastro.add(mntmQuarto);

		JMenu mnConsulta = new JMenu("Consulta");
		menuBar.add(mnConsulta);

		JMenuItem mntmConsultaGeral = new JMenuItem("Consulta Geral");
		mntmConsultaGeral.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				criarTela(ETipo.Consulta);
			}
		});
		mnConsulta.add(mntmConsultaGeral);
		
		JMenu mnReserva = new JMenu("Reserva");
		menuBar.add(mnReserva);
		
		JMenuItem mntmGerenciarReserva = new JMenuItem("Gerenciar");
		mntmGerenciarReserva.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				criarTela(ETipo.Reserva);				
			}
		});
		mnReserva.add(mntmGerenciarReserva);

		JMenu mnLocacao = new JMenu("Locacao");
		menuBar.add(mnLocacao);

		JMenuItem mntmGerenciarLocacao = new JMenuItem("Gerenciar");
		mntmGerenciarLocacao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				criarTela(ETipo.Locacao);
			}
		});
		mnLocacao.add(mntmGerenciarLocacao);

		JMenu mnEstoque = new JMenu("Estoque");
		menuBar.add(mnEstoque);

		JMenuItem mntmGerenciarEstoque = new JMenuItem("Entrada");
		mntmGerenciarEstoque.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				criarTela(ETipo.Estoque);			
			}
		});
		mnEstoque.add(mntmGerenciarEstoque);

		JMenuItem mntmConsultarEstoque = new JMenuItem("Acerto");
		mntmConsultarEstoque.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				criarTela(ETipo.Acerto);						
			}
		});
		mnEstoque.add(mntmConsultarEstoque);
		
		JMenuItem mntmConsulta = new JMenuItem("Consulta");
		mntmConsulta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				criarTela(ETipo.EstoqueConsulta);					
			}
		});
		mnEstoque.add(mntmConsulta);

		JMenu mnFinanceiro = new JMenu("Financeiro");
		menuBar.add(mnFinanceiro);

		JMenuItem mntmFecharConta = new JMenuItem("Fechar Conta");
		mntmFecharConta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				criarTela(ETipo.FinanceiroFecharConta);				
			}
		});
		mnFinanceiro.add(mntmFecharConta);

		JMenuItem mntmNovoLancamento = new JMenuItem("Novo Lançamento");
		mntmNovoLancamento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				criarTela(ETipo.FinanceiroLancamento);				
			}
		});
		mnFinanceiro.add(mntmNovoLancamento);

		JMenuItem mntmExtrato = new JMenuItem("Extrato");
		mntmExtrato.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				criarTela(ETipo.Extrato);
			}
		});
		mnFinanceiro.add(mntmExtrato);
		getContentPane().setLayout(null);
	}
	// LAYOUT E EVENTOS

	// CRIAÇÃO DE TELAS
	protected boolean checarTela() {

		return false;

	}

	protected void criarTela(ETipo tipoTela) {
		if (tipoTela == ETipo.Cliente) {
			TelaCadastroCliente tela = new TelaCadastroCliente(this);
			tabbedPane.addTab("Cadastro Cliente", null, tela, null);
			tabbedPane.setSelectedComponent(tela);
		} else if (tipoTela == ETipo.Fornecedor) {
			TelaCadastroFornecedor tela = new TelaCadastroFornecedor(this);
			tabbedPane.addTab("Cadastro Fornecedor", null, tela, null);
			tabbedPane.setSelectedComponent(tela);
		} else if (tipoTela == ETipo.FinanceiroLancamento) {
			TelaFinanceiroLancamento tela = new TelaFinanceiroLancamento(this);
			tabbedPane.addTab("Novo lancamento em locação", null, tela, null);
			tabbedPane.setSelectedComponent(tela);
		} else if (tipoTela == ETipo.FinanceiroFecharConta) {
			TelaFinanceiroFecharConta tela = new TelaFinanceiroFecharConta(this);
			tabbedPane.addTab("Fechar conta", null, tela, null);
			tabbedPane.setSelectedComponent(tela);
		}  else if (tipoTela == ETipo.Produto) {
			TelaCadastroProduto tela = new TelaCadastroProduto(this);
			tabbedPane.addTab("Cadastro Produto", null, tela, null);
			tabbedPane.setSelectedComponent(tela);
		} else if (tipoTela == ETipo.Quarto) {
			TelaCadastroQuarto tela = new TelaCadastroQuarto(this);
			tabbedPane.addTab("Cadastro Quarto", null, tela, null);
			tabbedPane.setSelectedComponent(tela);
		} else if (tipoTela == ETipo.Locacao) {
			TelaCadastroLocacao tela = new TelaCadastroLocacao(this);
			tabbedPane.addTab("Cadastro Locação", null, tela, null);
			tabbedPane.setSelectedComponent(tela);
		} else if (tipoTela == ETipo.Reserva) {
			TelaCadastroReserva tela = new TelaCadastroReserva(this);
			tabbedPane.addTab("Cadastro Reserva", null, tela, null);
			tabbedPane.setSelectedComponent(tela);
		}else if (tipoTela == ETipo.Estoque) {
			TelaEntradaEstoque tela = new TelaEntradaEstoque(this);
			tabbedPane.addTab("Entrada de estoque", null, tela, null);
			tabbedPane.setSelectedComponent(tela);
		}else if (tipoTela == ETipo.Acerto) {
			TelaAcertoEstoque tela = new TelaAcertoEstoque(this);
			tabbedPane.addTab("Acerto de estoque", null, tela, null);
			tabbedPane.setSelectedComponent(tela);
		}else if (tipoTela == ETipo.EstoqueConsulta) {
			new TelaConsultaEstoque().setVisible(true);;
		}else if (tipoTela == ETipo.Consulta) {
			new TelaConsultaGeral().setVisible(true);
		}else if(tipoTela == ETipo.Extrato) {
			TelaFinanceiroExtrato tela = new TelaFinanceiroExtrato(this);
			tabbedPane.addTab("Extrato Financeiro", null, tela, null);
			tabbedPane.setSelectedComponent(tela);
		}
	}
	// CRIAÇÃO DE TELAS

	// MANIPULAÇÃO DE COMPONENTES
	public void fecharTela(JInternalFrame j) {
		tabbedPane.remove(j);
	}
	// MANIPULAÇÃO DE COMPONENTES

	public long getUsuarioLogado() {
		return usuarioLogado;
	}


	public void setUsuarioLogado(long usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}
	// MAIN
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaPrincipal tela = new TelaPrincipal();
					tela.setVisible(true);
				} catch (Exception e) {
				}
			}
		});
	}
	// MAIN

}
