package hotel.infra;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConexaoMariaDBJDBC implements ConexaoJDBC {

	private Connection connection = null;
	
	public ConexaoMariaDBJDBC(String usuario, String senha, String ip, String porta) throws SQLException, ClassNotFoundException {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (InstantiationException ex) {
			Logger.getLogger(ConexaoMariaDBJDBC.class.getName()).log(Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			Logger.getLogger(ConexaoMariaDBJDBC.class.getName()).log(Level.SEVERE, null, ex);
		}

		this.connection = DriverManager.getConnection("jdbc:mysql://"+ip+":"+porta+"/", usuario, senha);
		this.connection.setAutoCommit(false);
	}
	
	public ConexaoMariaDBJDBC(String usuario, String senha, String db, String ip, String porta) throws SQLException, ClassNotFoundException {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (InstantiationException ex) {
			Logger.getLogger(ConexaoMariaDBJDBC.class.getName()).log(Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			Logger.getLogger(ConexaoMariaDBJDBC.class.getName()).log(Level.SEVERE, null, ex);
		}

		this.connection = DriverManager.getConnection("jdbc:mysql://"+ip+":"+porta+"/" + db, usuario, senha);
		this.connection.setAutoCommit(false);
	}

	@Override
	public Connection getConnection() {
		return this.connection;
	}

	@Override
	public void close() {
		if (this.connection != null) {
			try {
				this.connection.close();
			} catch (SQLException ex) {
				Logger.getLogger(ConexaoMariaDBJDBC.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
	}

	@Override
	public void commit() throws SQLException {
		this.connection.commit();
		this.close();
	}

	@Override
	public void rollback() {
		if (this.connection != null) {
			try {
				this.connection.rollback();
			} catch (SQLException ex) {
				Logger.getLogger(ConexaoMariaDBJDBC.class.getName()).log(Level.SEVERE, null, ex);
			} finally {
				this.close();
			}
		}
	}

}