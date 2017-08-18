package hotel.classes.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import hotel.infra.ConexaoJDBC;
import hotel.infra.ConexaoMariaDBJDBC;

public abstract class DAO {

	protected final ConexaoJDBC conexao;

	public DAO() throws ClassNotFoundException, SQLException {
		this.conexao = new ConexaoMariaDBJDBC();
	}
	
	public abstract ResultSet listar() throws SQLException, ClassNotFoundException;
	
	public abstract ResultSet listarFiltro(String campo, String busca) throws SQLException, ClassNotFoundException;
	
	public abstract Vector<String> getCamposBD();
	
}
