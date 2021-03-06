package hotel.classes.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import hotel.infra.ConexaoJDBC;
import hotel.infra.ConexaoMariaDBJDBC;
import hotel.util.UtilCredenciaisBD;

public abstract class DAO {

	protected final ConexaoJDBC conexao;

	public DAO() throws ClassNotFoundException, SQLException {
		String[] lines = UtilCredenciaisBD.lerArquivoIni();
		String usuarioDB = lines[0];
		String senhaDB = lines[1];
		String db = lines[2];
		String ip = lines[3];
		String porta = lines[4];
		this.conexao = new ConexaoMariaDBJDBC(usuarioDB, senhaDB, db, ip, porta);
	}
	
	public abstract ResultSet listar() throws SQLException, ClassNotFoundException;
	
	public abstract ResultSet listarFiltro(String campo, String busca) throws SQLException, ClassNotFoundException;
	
	public abstract Vector<String> getCamposBDAnalitico();
	
}
