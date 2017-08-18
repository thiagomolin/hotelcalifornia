package hotel.classes.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import hotel.classes.Cidade;

public class CidadeDAO extends DAO{

	public CidadeDAO() throws ClassNotFoundException, SQLException {
		super();
	}

	public ResultSet listar() throws SQLException, ClassNotFoundException {
		String sqlQuery = "SELECT * FROM cidade ORDER BY id";

		try {
			PreparedStatement stmt = this.conexao.getConnection().prepareStatement(sqlQuery);
			ResultSet rs = stmt.executeQuery();

			return rs;
		} catch (SQLException e) {
			throw e;
		}
	}

	public List<Cidade> listarPorEstado(long id) throws SQLException, ClassNotFoundException {
		String sqlQuery = "SELECT cidade.id,cidade.ds_cidade FROM `cidade` INNER JOIN estado ON estado.id = cidade.id_estado WHERE cidade.id_estado = ?";

		try {
			PreparedStatement stmt = this.conexao.getConnection().prepareStatement(sqlQuery);
			stmt.setLong(1, id);
			ResultSet rs = stmt.executeQuery();

			List<Cidade> cidade = new ArrayList<>();

			while (rs.next()) {
				cidade.add(parser(rs));
			}

			return cidade;
		} catch (SQLException e) {
			throw e;
		}
	}

	public Cidade listarPorId(long id) throws SQLException, ClassNotFoundException {
		String sqlQuery = "SELECT * FROM `cidade` WHERE id = ?";

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

	private Cidade parser(ResultSet resultSet) throws SQLException {
		Cidade c = new Cidade(resultSet.getLong("id"), resultSet.getString("ds_cidade"));
		return c;
	}

	@Override
	public Vector<String> getCamposBD() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultSet listarFiltro(String campo, String busca) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

}
