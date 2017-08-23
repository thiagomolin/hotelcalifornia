package hotel.classes.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import hotel.classes.TipoDeQuarto;

public class TipoDeQuartoDAO extends DAO{
		
	public TipoDeQuartoDAO() throws ClassNotFoundException, SQLException {
		super();
	}

	public List<TipoDeQuarto> getLista() throws SQLException, ClassNotFoundException {
		String sqlQuery = "SELECT * FROM quarto_tipo ORDER BY id";

		try {
			PreparedStatement stmt = this.conexao.getConnection().prepareStatement(sqlQuery);
			ResultSet rs = stmt.executeQuery();

			List<TipoDeQuarto> tipoQuarto = new ArrayList<>();

			while (rs.next()) {
				tipoQuarto.add(parser(rs));
			}

			return tipoQuarto;
		} catch (SQLException e) {
			throw e;
		}
	}
	
	public TipoDeQuarto listarPorID(long id) throws SQLException {
			String sqlQuery = "SELECT quarto_tipo.id, quarto_tipo.ds_tipo_quarto FROM `quarto_tipo` INNER JOIN quarto ON quarto.fk_tipo_quarto = quarto_tipo.id WHERE quarto.fk_tipo_quarto = ?";

			try {
				PreparedStatement stmt = this.conexao.getConnection().prepareStatement(sqlQuery);
				stmt.setLong(1, id);
				ResultSet rs = stmt.executeQuery();
				TipoDeQuarto tipoDeQuarto = null;
				if (rs.next()) {
					tipoDeQuarto = parser(rs);
				}

				return tipoDeQuarto;
			} catch (SQLException e) {
				throw e;
			}
		}
	
	public TipoDeQuarto listarPorIDQuarto(long id) throws SQLException {
		String sqlQuery = "SELECT quarto_tipo.id, quarto_tipo.ds_tipo_quarto FROM `quarto_tipo` INNER JOIN quarto ON quarto.fk_tipo_quarto = quarto_tipo.id WHERE quarto.id = ?";

		try {
			PreparedStatement stmt = this.conexao.getConnection().prepareStatement(sqlQuery);
			stmt.setLong(1, id);
			ResultSet rs = stmt.executeQuery();
			TipoDeQuarto tipoDeQuarto = null;
			if (rs.next()) {
				tipoDeQuarto = parser(rs);
			}

			return tipoDeQuarto;
		} catch (SQLException e) {
			throw e;
		}
	}
		
	
	private TipoDeQuarto parser(ResultSet resultSet) throws SQLException {
		TipoDeQuarto q = new TipoDeQuarto(resultSet.getLong("id"), resultSet.getString("ds_tipo_quarto"));
		return q;
	}
	
	@Override
	public ResultSet listar() throws SQLException, ClassNotFoundException {
		return null;
	}

	@Override
	public ResultSet listarFiltro(String campo, String busca) throws SQLException, ClassNotFoundException {
		return null;
	}
	

	@Override
	public Vector<String> getCamposBD() {
		return null;
	}
	

}
