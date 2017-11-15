package hotel.telas.setup;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.JTextArea;

import hotel.infra.ConexaoJDBC;
import hotel.infra.ConexaoMariaDBJDBC;
import hotel.util.UtilCredenciaisBD;

public final class CriadorBD extends JFrame {
	private static final long serialVersionUID = 1L;

	public static final String SQL_FILE_PATH = "hotelcalifornia.sql";

	private String arquivoSemComentarios = "";

	private List<String> comandos;

	private JTextArea textArea;
	private JProgressBar barra;

	private boolean deveCriarBD;
	private boolean deveCriarTabelas;

	private String usuarioDB;
	private String senhaDB;
	private String db;
	private String ip;
	private String porta;

	public CriadorBD(boolean deveCriarBD, boolean deveCriarTabelas) {
		super();
		this.deveCriarBD = deveCriarBD;
		this.deveCriarTabelas = deveCriarTabelas;
		inicializarLayoutEEventos();
		lerArquivoIni();
		comandos = new ArrayList<String>();
	}

	private void lerArquivoIni() {
		String[] credenciais = UtilCredenciaisBD.lerArquivoIni();
		usuarioDB = credenciais[0];
		senhaDB = credenciais[1];
		db = credenciais[2];
		ip = credenciais[3];
		porta = credenciais[4];
	}

	public void executar() {
		if (deveCriarBD) {
			criarBD();
		}

		if (deveCriarTabelas) {
			removerComentarios();
			buscarComandos();
			execucutarComandos();
		}
	}

	private void criarBD() {
		try {
			ConexaoJDBC con = new ConexaoMariaDBJDBC(usuarioDB, senhaDB, ip, porta);
			Statement stmt = con.getConnection().createStatement();
			String query = "CREATE DATABASE " + db;
			stmt.executeQuery(query);

		} catch (Exception e) {
		}
	}

	private void inicializarLayoutEEventos() {
		setTitle("Configurando o Banco de dados");
		getContentPane().setLayout(null);
		setBounds(250, 250, 450, 300);
		barra = new JProgressBar();
		barra.setBounds(121, 21, 303, 23);
		barra.setStringPainted(true);
		getContentPane().add(barra);
		barra.setValue(0);

		textArea = new JTextArea();
		textArea.setBounds(10, 67, 414, 183);
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		getContentPane().add(textArea);

		JLabel lblConcluido = new JLabel("Concluído:");
		lblConcluido.setBounds(10, 24, 101, 14);
		getContentPane().add(lblConcluido);

	}

	private void removerComentarios() {
		try {
			BufferedReader br = new BufferedReader(new FileReader(SQL_FILE_PATH));
			String line;
			while ((line = br.readLine()) != null) {
				String primeiros2 = "";
				if (line.length() >= 2) {
					primeiros2 = line.substring(0, 2);
				}

				if (primeiros2.equals("//") || primeiros2.equals("/*") || primeiros2.equals("--")) {
					continue;
				} else {
					arquivoSemComentarios += line;
				}
			}

			br.close();
		} catch (IOException e) {
//
		}

	}

	private void buscarComandos() {
		Scanner scan;
		scan = new Scanner(arquivoSemComentarios);
		scan.useDelimiter(Pattern.compile(";"));

		while (scan.hasNext()) {
			String comando = scan.next();

			comandos.add(comando);
		}

		scan.close();
	}

	private void execucutarComandos() {
		int totalComandos = comandos.size();
		int porcentagemPorComando = 100 / totalComandos;
		barra.setMaximum(totalComandos);

		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					ConexaoJDBC con = new ConexaoMariaDBJDBC(usuarioDB, senhaDB, db, ip, porta);

					for (String comando : comandos) {
						textArea.setText(comando);
						PreparedStatement stmt = con.getConnection().prepareStatement(comando);
						stmt.execute();
						barra.setValue(barra.getValue() + porcentagemPorComando);
						Thread.sleep(50);
					}

					JOptionPane.showMessageDialog(null, "Tabelas criadas e populadas com sucesso!", "Sucesso",
							JOptionPane.INFORMATION_MESSAGE);
					sair();
				} catch (ClassNotFoundException | SQLException | InterruptedException e) {
				}
			}
		});

		t.start();
	}

	protected void sair() {
		this.dispose();
	}
}
