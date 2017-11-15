package hotel.classes.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import hotel.classes.Estado;

public class EstadoDAO extends DAO{


	public EstadoDAO() throws ClassNotFoundException, SQLException {
		super();
	}

	public ResultSet listar() throws SQLException, ClassNotFoundException {
		String sqlQuery = "SELECT * FROM estado ORDER BY id";

		try {
			PreparedStatement stmt = this.conexao.getConnection().prepareStatement(sqlQuery);
			ResultSet rs = stmt.executeQuery();

			return rs;
		} catch (SQLException e) {
			throw e;
		}
	}

	public List<Estado> listarPorPais(long id) throws SQLException, ClassNotFoundException {
		String sqlQuery = "SELECT estado.id, estado.fk_pais, estado.ds_estado FROM `estado` INNER JOIN pais ON pais.id = estado.fk_pais WHERE estado.fk_pais = ?";

		try {
			PreparedStatement stmt = this.conexao.getConnection().prepareStatement(sqlQuery);
			stmt.setLong(1, id);
			ResultSet rs = stmt.executeQuery();

			List<Estado> estado = new ArrayList<>();

			while (rs.next()) {
				estado.add(parser(rs));
			}

			return estado;
		} catch (SQLException e) {
			throw e;
		}
	}

	public Estado listarPorId(long id) throws SQLException, ClassNotFoundException {
		String sqlQuery = "SELECT * FROM `estado` WHERE id = ?";

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

	private Estado parser(ResultSet resultSet) throws SQLException {
		Estado e = new Estado(resultSet.getLong("id"), resultSet.getLong("fk_pais"), resultSet.getString("ds_estado"));
		return e;
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
