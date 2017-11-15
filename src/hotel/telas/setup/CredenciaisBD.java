package hotel.telas.setup;

import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;

import hotel.infra.ConexaoJDBC;
import hotel.infra.ConexaoMariaDBJDBC;
import hotel.telas.main.TelaLogin;
import hotel.util.UtilCredenciaisBD;

public final class CredenciaisBD extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTextField textFieldUsuario;
	private JTextField textFieldSenha;

	public final static String NOME_ARQUIVO_INI = "BDConfig.ini";

	private String usuarioDB;
	private String senhaDB;
	private String db;
	private String ip;
	private String porta;

	private TelaLogin tela;
	private JTextField textFieldDB;
	private JCheckBox deveCriarBD;
	private JTextField txtIp;
	private JTextField txtPorta;

	public CredenciaisBD(TelaLogin tela2) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.tela = tela2;
		usuarioDB = "";
		senhaDB = "";
		db = "";
		ip = "";
		porta = "";
		String[] lines = UtilCredenciaisBD.lerArquivoIni();
		usuarioDB = lines[0];
		senhaDB = lines[1];
		db = lines[2];
		ip = lines[3];
		porta = lines[4];

		inicializarLayoutEEventos();

		textFieldUsuario.setText(usuarioDB);
		textFieldSenha.setText(senhaDB);
		textFieldDB.setText(db);
		ip = ip.isEmpty()?"localhost":ip;
		porta = porta.isEmpty()?"3306":porta;
		txtIp.setText(ip);
		txtPorta.setText(porta);

	}

	public void testeDeConexao() {
		if (isConexaoValida()) {

			if (deveCriarBD.isSelected() || deveCriarTabelas()) {
				CriadorBD c = new CriadorBD(deveCriarBD.isSelected(), deveCriarTabelas());
				c.setVisible(true);
				c.executar();
			}
			tela.setEnabled(true);
			this.dispose();
		}
	}

	private boolean deveCriarTabelas() {
		try {
			ConexaoJDBC con = new ConexaoMariaDBJDBC(usuarioDB, senhaDB, db, ip, porta);
			Statement stmt = con.getConnection().createStatement();
			ResultSet result = stmt.executeQuery("SELECT * " + "FROM information_schema.tables "
					+ "WHERE table_schema = '" + db + "' " + "AND table_name = 'cidade' " + "LIMIT 1");

			return !result.next();
		} catch (Exception e) {
		}
		return true;
	}

	private void inicializarLayoutEEventos() {
		setTitle("Configuração");
		getContentPane().setLayout(null);
		setBounds(250, 250, 467, 355);
		JTextPane panelInfo = new JTextPane();
		panelInfo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panelInfo.setEditable(false);
		panelInfo.setBackground(SystemColor.control);
		panelInfo.setText("Informe abaixo as credenciais de seu servidor de BD MySQL:");
		panelInfo.setBounds(30, 11, 380, 48);
		getContentPane().add(panelInfo);

		textFieldUsuario = new JTextField();
		textFieldUsuario.setBounds(209, 70, 86, 20);
		textFieldUsuario.setText("");
		getContentPane().add(textFieldUsuario);
		textFieldUsuario.setColumns(10);

		textFieldSenha = new JTextField();
		textFieldSenha.setColumns(10);
		textFieldSenha.setBounds(209, 106, 86, 20);
		textFieldSenha.setText("");
		getContentPane().add(textFieldSenha);
		
		txtIp = new JTextField();
		txtIp.setText("");
		txtIp.setColumns(10);
		txtIp.setBounds(209, 172, 86, 20);
		getContentPane().add(txtIp);
		
		txtPorta = new JTextField();
		txtPorta.setText("");
		txtPorta.setColumns(10);
		txtPorta.setBounds(209, 200, 86, 20);
		getContentPane().add(txtPorta);
		
		deveCriarBD = new JCheckBox("");
		deveCriarBD.setBounds(209, 240, 56, 23);
		deveCriarBD.setSelected(false);
		getContentPane().add(deveCriarBD);
		
		JLabel lblPorta = new JLabel("Porta:");
		lblPorta.setBounds(94, 203, 86, 14);
		getContentPane().add(lblPorta);

		JLabel lblUsuario = new JLabel("Usuario:");
		lblUsuario.setBounds(94, 73, 86, 14);
		getContentPane().add(lblUsuario);

		JLabel lblSenha = new JLabel("Senha:");
		lblSenha.setBounds(94, 109, 86, 14);
		getContentPane().add(lblSenha);

		JButton btnOK = new JButton("OK");
		btnOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setarCampos();
				testeDeConexao();
			}
		});
		btnOK.setBounds(155, 270, 89, 23);
		getContentPane().add(btnOK);

		JLabel labelBD = new JLabel("Nome BD:");
		labelBD.setBounds(94, 144, 86, 14);
		getContentPane().add(labelBD);
		
		JLabel lblCriarTabelasmockData = new JLabel("Criar novo BD:");
		lblCriarTabelasmockData.setBounds(94, 245, 150, 14);
		getContentPane().add(lblCriarTabelasmockData);
		
		JLabel lblIp = new JLabel("Ip:");
		lblIp.setBounds(94, 175, 86, 14);
		getContentPane().add(lblIp);

		textFieldDB = new JTextField();
		textFieldDB.setText("");
		textFieldDB.setColumns(10);
		textFieldDB.setBounds(209, 141, 86, 20);
		getContentPane().add(textFieldDB);

	}

	private void setarCampos() {
		usuarioDB = textFieldUsuario.getText();
		senhaDB = textFieldSenha.getText();
		db = textFieldDB.getText();
		ip = txtIp.getText();
		porta = txtPorta.getText();
	}

	private boolean isConexaoValida() {
		try {
			ConexaoJDBC con = new ConexaoMariaDBJDBC(usuarioDB, senhaDB, ip, porta);
			Statement stmt = con.getConnection().createStatement();
			ResultSet result;
			if (!deveCriarBD.isSelected()) {
				result = stmt.executeQuery(
						"SELECT SCHEMA_NAME FROM INFORMATION_SCHEMA.SCHEMATA WHERE SCHEMA_NAME = '" + db + "'");
			} else {
				result = stmt.executeQuery("SHOW DATABASES");
			}

			if (!result.next()) {
				throw new Exception();
			}

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
			bw.newLine();
			bw.write(db);
			bw.newLine();
			bw.write(ip);
			bw.newLine();
			bw.write(porta);

			bw.close();

		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "ERRO DE gravação");
		}
	}
}
