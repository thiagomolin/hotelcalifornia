package hotel.classes.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import hotel.classes.Servico;

public class ServicoDAO extends DAO {

	public ServicoDAO() throws ClassNotFoundException, SQLException {
		super();
	}

	public ResultSet listar() throws SQLException, ClassNotFoundException {
		String sqlQuery = "SELECT * FROM servico ORDER BY id";

		try {
			PreparedStatement stmt = this.conexao.getConnection().prepareStatement(sqlQuery);
			ResultSet rs = stmt.executeQuery();

			return rs;
		} catch (SQLException e) {
			throw e;
		}
	}

	public List<Servico> listarPorCargo(long id) throws SQLException, ClassNotFoundException {
		String sqlQuery = "SELECT servico.id,servico.ds_servico FROM `servico` INNER JOIN cargo ON cargo.id = servico.fk_cargo WHERE servico.fk_cargo = ?";

		try {
			PreparedStatement stmt = this.conexao.getConnection().prepareStatement(sqlQuery);
			stmt.setLong(1, id);
			ResultSet rs = stmt.executeQuery();

			List<Servico> servico = new ArrayList<>();

			while (rs.next()) {
				servico.add(parser(rs));
			}

			return servico;
		} catch (SQLException e) {
			throw e;
		}
	}

	public List<Servico> getLista() throws SQLException, ClassNotFoundException {
		String sqlQuery = "SELECT * FROM servico ORDER BY id";

		try {
			PreparedStatement stmt = this.conexao.getConnection().prepareStatement(sqlQuery);
			ResultSet rs = stmt.executeQuery();

			List<Servico> servicos = new ArrayList<>();

			while (rs.next()) {
				servicos.add(parser(rs));
			}

			return servicos;
		} catch (SQLException e) {
			throw e;
		}
	}

	private Servico parser(ResultSet resultSet) throws SQLException {
		Servico s = new Servico(resultSet.getLong("id"), resultSet.getString("ds_servico"));
		return s;
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
