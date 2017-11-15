package hotel.classes.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import hotel.classes.Status;

public class StatusDAO extends DAO{

	public StatusDAO() throws ClassNotFoundException, SQLException {
		super();
	}

	public ResultSet listar() throws SQLException, ClassNotFoundException {
		String sqlQuery = "SELECT * FROM status ORDER BY id";

		try {
			PreparedStatement stmt = this.conexao.getConnection().prepareStatement(sqlQuery);
			ResultSet rs = stmt.executeQuery();

			return rs;
		} catch (SQLException e) {
			throw e;
		}
	}

	public List<Status> getLista() throws SQLException, ClassNotFoundException {
		String sqlQuery = "SELECT * FROM status ORDER BY id";

		try {
			PreparedStatement stmt = this.conexao.getConnection().prepareStatement(sqlQuery);
			ResultSet rs = stmt.executeQuery();
			
			List<Status> statuses = new ArrayList<Status>();
			
			while(rs.next()) {
				statuses.add(parser(rs));
			}
			
			return statuses;
		} catch (SQLException e) {
			throw e;
		}
	}
	
	public Status selecionar(long id) throws SQLException, ClassNotFoundException {
		String sqlQuery = "SELECT * FROM status WHERE id = ?";
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

	private Status parser(ResultSet resultSet) throws SQLException {
		Status p = new Status(resultSet.getLong("id"), resultSet.getString("ds_status"));
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
