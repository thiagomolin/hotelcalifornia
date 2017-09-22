package hotel.telas.setup;

import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;

import hotel.infra.ConexaoJDBC;
import hotel.infra.ConexaoMariaDBJDBC;
import hotel.telas.main.TelaPrincipal;
import hotel.util.UtilCredenciaisBD;

public final class CredenciaisBD extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTextField textFieldUsuario;
	private JTextField textFieldSenha;

	public final static String NOME_ARQUIVO_INI = "BDConfig.ini";

	private String usuarioDB;
	private String senhaDB;

	private TelaPrincipal tela;

	public CredenciaisBD(TelaPrincipal tela) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.tela = tela;
		usuarioDB = "";
		senhaDB = "";
		String[] lines = UtilCredenciaisBD.lerArquivoIni();
		usuarioDB = lines[0];
		senhaDB = lines[1];

		inicializarLayoutEEventos();
	}

	public void testeDeConexao() {
		if (isConexãoValida()) {
			tela.setEnabled(true);
			this.dispose();
		}
	}

	protected void testeDeConexaoOK() {
		if (isConexãoValidaOK()) {
			tela.setEnabled(true);
			this.dispose();
		}
	}

	private void inicializarLayoutEEventos() {
		setTitle("Configuração");
		getContentPane().setLayout(null);
		setBounds(250, 250, 467, 280);
		JTextPane panelInfo = new JTextPane();
		panelInfo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panelInfo.setEditable(false);
		panelInfo.setBackground(SystemColor.control);
		panelInfo.setText("Informe abaixo as credenciais de seu servidor de BD MySQL:");
		panelInfo.setBounds(30, 11, 380, 48);
		getContentPane().add(panelInfo);

		textFieldUsuario = new JTextField();
		textFieldUsuario.setBounds(208, 70, 86, 20);
		textFieldUsuario.setText("");
		getContentPane().add(textFieldUsuario);
		textFieldUsuario.setColumns(10);

		textFieldSenha = new JTextField();
		textFieldSenha.setColumns(10);
		textFieldSenha.setBounds(208, 106, 86, 20);
		textFieldSenha.setText("");
		getContentPane().add(textFieldSenha);

		JLabel lblUsuario = new JLabel("Usuario:");
		lblUsuario.setBounds(114, 73, 46, 14);
		getContentPane().add(lblUsuario);

		JLabel lblSenha = new JLabel("Senha:");
		lblSenha.setBounds(114, 109, 46, 14);
		getContentPane().add(lblSenha);

		JButton btnOK = new JButton("OK");
		btnOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				testeDeConexaoOK();
			}
		});
		btnOK.setBounds(161, 172, 89, 23);
		getContentPane().add(btnOK);

	}

	private boolean isConexãoValida() {
		try {

			ConexaoJDBC con = new ConexaoMariaDBJDBC(usuarioDB, senhaDB);
			con.close();
			gravarArquivoIni();

			return true;

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Credenciais do BD incorretas ou serviço de BD não inicializado!");
		}
		return false;
	}

	private boolean isConexãoValidaOK() {
		try {

			usuarioDB = textFieldUsuario.getText();
			senhaDB = textFieldSenha.getText();

			ConexaoJDBC con = new ConexaoMariaDBJDBC(usuarioDB, senhaDB);
			con.close();
			gravarArquivoIni();

			return true;

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Credenciais do BD incorretas ou serviço de BD não inicializado!");
		}
		return false;
	}

	public void gravarArquivoIni() {
		try {
			FileWriter fw = new FileWriter(NOME_ARQUIVO_INI);

			BufferedWriter bw = new BufferedWriter(fw);

			bw.write(usuarioDB);
			bw.newLine();
			bw.write(senhaDB);

			bw.close();

		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "ERRO DE gravação");
		}
	}

}
