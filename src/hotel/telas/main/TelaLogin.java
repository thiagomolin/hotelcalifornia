package hotel.telas.main;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import hotel.classes.Usuario;
import hotel.classes.DAO.UsuarioDAO;
import hotel.telas.setup.CredenciaisBD;

public class TelaLogin extends JFrame{
	private static final long serialVersionUID = 1L;
	
	private JTextField textFieldUsuario;
	private JPasswordField textFieldSenha;
	
	private UsuarioDAO dao;
	
	public TelaLogin() {
		super();
		inicializarLayoutEEventos();
	}

	private void inicializarLayoutEEventos() {
		setBounds(100, 100, 450, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		textFieldUsuario = new JTextField();
		textFieldUsuario.setBounds(99, 161, 89, 20);
		getContentPane().add(textFieldUsuario);
		textFieldUsuario.setColumns(10);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				login();
			}
		});
		btnLogin.setBounds(172, 192, 89, 23);
		getContentPane().add(btnLogin);
		
		JLabel lblUsurio = new JLabel("Usuário");
		lblUsurio.setBounds(119, 136, 46, 14);
		getContentPane().add(lblUsurio);
		
		JLabel lblSenha = new JLabel("Senha");
		lblSenha.setBounds(260, 136, 46, 14);
		getContentPane().add(lblSenha);
		
		textFieldSenha = new JPasswordField();
		textFieldSenha.setBounds(234, 161, 89, 20);
		getContentPane().add(textFieldSenha);
	}

	protected void login() {
		Usuario usuario = null;
		try {
			dao = new UsuarioDAO();
			usuario = dao.selecionar(textFieldUsuario.getText());
		} catch (ClassNotFoundException | SQLException e) {			
		}
		
		if(usuario == null) {
			JOptionPane.showMessageDialog(null, "Usuario ou senha incorreto");
		}else if(usuario.getDsSenha().equals(String.copyValueOf(textFieldSenha.getPassword()))) {
			TelaPrincipal tela = new TelaPrincipal();
			tela.setUsuarioLogado(usuario.getId()); 
			tela.setVisible(true);	
			this.dispose();
		}else {
			JOptionPane.showMessageDialog(null, "Usuario ou senha incorreto");
		}
	}
	
	
	
	
	
	// MAIN
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaLogin tela = new TelaLogin();
					tela.setEnabled(false);
					tela.setVisible(true);
					
					CredenciaisBD cbd = new CredenciaisBD(tela);
					cbd.setVisible(true);
					cbd.testeDeConexao();
				} catch (Exception e) {
				}
			}
		});
	}
	// MAIN

	
	
	
}
