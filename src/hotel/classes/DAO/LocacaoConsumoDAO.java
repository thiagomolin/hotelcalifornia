package hotel.classes.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import hotel.classes.LocacaoConsumo;

public class LocacaoConsumoDAO extends DAO {

	public LocacaoConsumoDAO() throws ClassNotFoundException, SQLException {
		super();
	}

	public void inserir(LocacaoConsumo locacaoConsumo) throws SQLException, ClassNotFoundException {
		String sqlQuery = "INSERT INTO locacao_consumo (fk_locacao, fk_produto, fk_usuario, nr_quantidade, dt_atual) VALUES (?, ?, ?, ?, ?)";

		try {
			PreparedStatement stmt = this.conexao.getConnection().prepareStatement(sqlQuery);
			stmt.setLong(1, locacaoConsumo.getFk_locacao());
			stmt.setLong(2, locacaoConsumo.getFk_produto());
			stmt.setLong(3, locacaoConsumo.getFk_usuario());
			stmt.setInt(4, locacaoConsumo.getNr_quantidade());
			stmt.setDate(5, locacaoConsumo.getDt_AtualSQL());

			stmt.executeUpdate();

			this.conexao.commit();
		} catch (SQLException e) {
			this.conexao.rollback();
			throw e;
		}
	}

	public LocacaoConsumo selecionar(long id) throws SQLException, ClassNotFoundException {
		String sqlQuery = "SELECT * FROM locacao_consumo WHERE id = ?";

		try {
			PreparedStatement stmt = this.conexao.getConnection().prepareStatement(sqlQuery);
			stmt.setLong(1, id);

			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				return parser(rs);
			}

			this.conexao.commit();
		} catch (SQLException e) {
			this.conexao.rollback();
			throw e;
		}

		return null;
	}

	public ResultSet listar() throws SQLException, ClassNotFoundException {
		String sqlQuery = "";

		try {
			PreparedStatement stmt = this.conexao.getConnection().prepareStatement(sqlQuery);
			ResultSet rs = stmt.executeQuery();

			return rs;
		} catch (SQLException e) {
			throw e;
		}
	}

	public List<LocacaoConsumo> getLista() throws SQLException, ClassNotFoundException {
		String sqlQuery = "SELECT * FROM locacaoconsumo ORDER BY id";

		try {
			PreparedStatement stmt = this.conexao.getConnection().prepareStatement(sqlQuery);
			ResultSet rs = stmt.executeQuery();

			List<LocacaoConsumo> locacaoConsumo = new ArrayList<>();

			while (rs.next()) {
				locacaoConsumo.add(parser(rs));
			}

			return locacaoConsumo;
		} catch (SQLException e) {
			throw e;
		}
	}

	private LocacaoConsumo parser(ResultSet resultSet) throws SQLException {
		LocalDate data = resultSet.getDate("dt_atual").toLocalDate();
		
		LocacaoConsumo p = new LocacaoConsumo(resultSet.getLong("id"), 
											  resultSet.getLong("fk_locacao"),
											  resultSet.getLong("fk_produto"),
											  resultSet.getLong("fk_usuario"),
											  resultSet.getInt("nr_quantidade"),
											  data);
		return p;
	}

	@Override
	public Vector<String> getCamposBDAnalitico() {
		Vector<String> lista = new Vector<String>();
		lista.add("ID");
		lista.add("Locação");
		lista.add("Produto");
		lista.add("Usuário");
		lista.add("Quantidade");
		
		
		return lista;
	}

	@Override
	public ResultSet listarFiltro(String campo, String busca) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

}
