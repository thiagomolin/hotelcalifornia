package hotel.telas.setup;

import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import hotel.classes.DAO.CidadeDAO;
import hotel.infra.ConexaoJDBC;
import hotel.infra.ConexaoMariaDBJDBC;

public class InicializaBD {

	private String usuarioDB;
	private String senhaDB;
	private String db;

	public InicializaBD(String usuarioDB, String senhaDB, String db) {
		this.usuarioDB = usuarioDB;
		this.senhaDB = senhaDB;
		this.db = db;
	}

	public boolean isBDValido() {
		try {
			CidadeDAO c = new CidadeDAO();

			if (c.getNumRegistros() == 0) {
				throw new Exception();
			}

			return true;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public void inicializarBD() {
		try {
			ConexaoJDBC con = new ConexaoMariaDBJDBC(usuarioDB, senhaDB, db);

			//TODO: carai
			
			
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

	}
	
	
	
	
	
	
	
	
	
	
}
