package hotel.telas.os;

import java.sql.SQLException;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EtchedBorder;

import hotel.classes.Os;
import hotel.classes.Servico;
import hotel.classes.DAO.OsDAO;
import hotel.classes.DAO.ServicoDAO;
import hotel.telas.cadastro.Tela;
import hotel.telas.consulta.ETipos;
import hotel.telas.main.TelaPrincipal;

public class TelaOs extends Tela {
	private static final long serialVersionUID = 1L;
	
	private JComboBox<Object> comboBoxCodigo;
	private JComboBox<Object> comboBoxServico; 
	private JComboBox<Object> comboBoxQuarto;
	
	private TelaPrincipal telaPrincipal;
	
	public TelaOs(TelaPrincipal telaPrincipal) {
		super();
		this.setTelaPrincipal(telaPrincipal);
		inicializarLayoutEEventos();
		inicializarComboBoxCodigo();
		inicializarComboBoxServico();
		inicializarComboBoxQuarto();
	}

	private void inicializarLayoutEEventos() {
		setClosable(true);
		setTitle("Gerenciar OS");
		setBounds(100, 100, 540, 440);
		getContentPane().setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		panel_1.setBounds(0, 0, 380, 92);
		getContentPane().add(panel_1);
		
		JLabel lblCdigo = new JLabel("Código");
		lblCdigo.setBounds(112, 49, 46, 14);
		panel_1.add(lblCdigo);
		
		comboBoxCodigo = new JComboBox<Object>();
		comboBoxCodigo.setBounds(157, 46, 92, 20);
		panel_1.add(comboBoxCodigo);
		
		JButton buttonConsulta = new JButton("...");
		buttonConsulta.setBounds(253, 45, 18, 23);
		panel_1.add(buttonConsulta);
		
		JLabel lblNome = new JLabel("Serviço");
		lblNome.setBounds(30, 123, 63, 14);
		getContentPane().add(lblNome);
		
		JLabel lblCpf = new JLabel("Quarto");
		lblCpf.setBounds(30, 162, 46, 14);
		getContentPane().add(lblCpf);
		
		JLabel lblDescrioDoServio = new JLabel("Descrição do Serviço");
		lblDescrioDoServio.setBounds(30, 255, 130, 14);
		getContentPane().add(lblDescrioDoServio);
		
		comboBoxServico = new JComboBox<Object>();
		comboBoxServico.setBounds(158, 120, 92, 20);
		getContentPane().add(comboBoxServico);
		
		comboBoxQuarto = new JComboBox<Object>();
		comboBoxQuarto.setBounds(158, 159, 92, 20);
		getContentPane().add(comboBoxQuarto);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(156, 250, 170, 82);
		getContentPane().add(textArea);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		panel.setBounds(376, 0, 148, 410);
		getContentPane().add(panel);
		
		JButton button = new JButton("Excluir");
		button.setEnabled(false);
		button.setBounds(29, 232, 89, 23);
		panel.add(button);
		
		JButton button_1 = new JButton("Alterar");
		button_1.setEnabled(false);
		button_1.setBounds(29, 198, 89, 23);
		panel.add(button_1);
		
		JButton button_2 = new JButton("Incluir");
		button_2.setEnabled(false);
		button_2.setBounds(29, 148, 89, 23);
		panel.add(button_2);
		
		JButton button_3 = new JButton("Cancelar");
		button_3.setEnabled(false);
		button_3.setBounds(29, 305, 89, 23);
		panel.add(button_3);
		
		JButton button_4 = new JButton("Sair");
		button_4.setBounds(29, 365, 89, 23);
		panel.add(button_4);
		
		JButton button_5 = new JButton("Mostrar");
		button_5.setEnabled(false);
		button_5.setBounds(29, 21, 89, 23);
		panel.add(button_5);
		
		JButton buttonFinalizarOs = new JButton("Finalizar OS");
		buttonFinalizarOs.setEnabled(false);
		buttonFinalizarOs.setBounds(29, 83, 89, 23);
		panel.add(buttonFinalizarOs);

	}
	
	private void inicializarComboBoxCodigo() {
		try {
			OsDAO cl = new OsDAO();
			List<Os> os = cl.getLista();
			comboBoxCodigo.setModel(new DefaultComboBoxModel<Object>(os.toArray()));
		} catch (ClassNotFoundException | SQLException ex) {
			ex.printStackTrace();
			ex.printStackTrace();
		}
	}
	
	private void inicializarComboBoxServico() {
		try {
			ServicoDAO cl = new ServicoDAO();
			List<Servico> servicos = cl.getLista();
			comboBoxServico.setModel(new DefaultComboBoxModel<Object>(servicos.toArray()));
		} catch (ClassNotFoundException | SQLException ex) {
			ex.printStackTrace();
			ex.printStackTrace();
		}
	}	

	private void inicializarComboBoxQuarto() {
				
	}


	
	
	
	
	public void setConsulta(Long id, ETipos tipo) {
		// TODO Auto-generated method stub
		
	}

	public TelaPrincipal getTelaPrincipal() {
		return telaPrincipal;
	}

	public void setTelaPrincipal(TelaPrincipal telaPrincipal) {
		this.telaPrincipal = telaPrincipal;
	}


}
