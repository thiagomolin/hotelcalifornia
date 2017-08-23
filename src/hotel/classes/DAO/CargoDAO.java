package hotel.classes.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import hotel.classes.Cargo;

public class CargoDAO extends DAO{

	public CargoDAO() throws ClassNotFoundException, SQLException {
		super();
	}

	public ResultSet listar() throws SQLException, ClassNotFoundException {
		String sqlQuery = "SELECT * FROM cargo ORDER BY id";

		try {
			PreparedStatement stmt = this.conexao.getConnection().prepareStatement(sqlQuery);
			ResultSet rs = stmt.executeQuery();

			return rs;
		} catch (SQLException e) {
			throw e;
		}
	}

	public List<Cargo> getLista() throws SQLException, ClassNotFoundException {
		String sqlQuery = "SELECT * FROM cargo ORDER BY id";

		try {
			PreparedStatement stmt = this.conexao.getConnection().prepareStatement(sqlQuery);
			ResultSet rs = stmt.executeQuery();
			
			List<Cargo> cargoes = new ArrayList<Cargo>();
			
			while(rs.next()) {
				cargoes.add(parser(rs));
			}
			
			return cargoes;
		} catch (SQLException e) {
			throw e;
		}
	}
	
	public Cargo listarPorId(long id) throws SQLException, ClassNotFoundException {
		String sqlQuery = "SELECT * FROM `cargo` WHERE id = ?";
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

	private Cargo parser(ResultSet resultSet) throws SQLException {
		Cargo p = new Cargo(resultSet.getLong("id"), resultSet.getString("ds_cargo"));
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
