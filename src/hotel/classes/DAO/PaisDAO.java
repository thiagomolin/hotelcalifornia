package hotel.classes.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import hotel.classes.Pais;

public class PaisDAO extends DAO{

	public PaisDAO() throws ClassNotFoundException, SQLException {
		super();
	}

	public ResultSet listar() throws SQLException, ClassNotFoundException {
		String sqlQuery = "SELECT * FROM pais ORDER BY id";

		try {
			PreparedStatement stmt = this.conexao.getConnection().prepareStatement(sqlQuery);
			ResultSet rs = stmt.executeQuery();

			return rs;
		} catch (SQLException e) {
			throw e;
		}
	}

	public List<Pais> getLista() throws SQLException, ClassNotFoundException {
		String sqlQuery = "SELECT * FROM pais ORDER BY id";

		try {
			PreparedStatement stmt = this.conexao.getConnection().prepareStatement(sqlQuery);
			ResultSet rs = stmt.executeQuery();
			
			List<Pais> paises = new ArrayList<Pais>();
			
			while(rs.next()) {
				paises.add(parser(rs));
			}
			
			return paises;
		} catch (SQLException e) {
			throw e;
		}
	}
	
	public Pais listarPorId(long id) throws SQLException, ClassNotFoundException {
		String sqlQuery = "SELECT * FROM `pais` WHERE id = ?";
		try {
			PreparedStatement stmt = this.conexao.getConnection().prepareStatement(sqlQuery);
			stmt.setLong(1, id);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				return parser(rs);
			}
		} catch (SQLException e) {
			throw e;
		}
		return null;
	}

	private Pais parser(ResultSet resultSet) throws SQLException {
		Pais p = new Pais(resultSet.getLong("id"), resultSet.getString("ds_pais"));
		return p;
	}

	@Override
	public Vector<String> getCamposBDAnalitico() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultSet listarFiltro(String campo, String busca) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

}
