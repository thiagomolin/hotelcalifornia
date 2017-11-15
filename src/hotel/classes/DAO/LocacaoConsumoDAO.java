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

	public ResultSet listar(long id) throws SQLException, ClassNotFoundException {
		String sqlQuery = "SELECT cliente.nm_cliente, "
						+ "produto.ds_produto, usuario.ds_usuario, locacao_consumo.nr_quantidade, "
						+ "produto.nr_valor_venda, produto.nr_valor_venda * locacao_consumo.nr_quantidade as total, "
						+ "locacao_consumo.fk_locacao, locacao_consumo.dt_atual, "
						+ "locacao_consumo.fk_produto " + 
						  "FROM locacao_consumo " + 
						  "INNER JOIN locacao ON locacao.id = locacao_consumo.fk_locacao " + 
						  "INNER JOIN cliente ON locacao.fk_cliente = cliente.id " + 
						  "INNER JOIN produto ON produto.id = locacao_consumo.fk_produto " + 
						  "INNER JOIN usuario ON usuario.id = locacao_consumo.fk_usuario " + 
						  "WHERE locacao_consumo.fk_locacao = ? " + 
						  "ORDER BY produto.id DESC";

		try {
			PreparedStatement stmt = this.conexao.getConnection().prepareStatement(sqlQuery);
			stmt.setLong(1, id);
			ResultSet rs = stmt.executeQuery();

			return rs;
		} catch (SQLException e) {
			throw e;
		}
	}

	public List<LocacaoConsumo> getLista(long fkLocacao) throws SQLException, ClassNotFoundException {
		String sqlQuery = "SELECT * FROM locacao_consumo WHERE fk_locacao = ? ORDER BY id";

		try {
			PreparedStatement stmt = this.conexao.getConnection().prepareStatement(sqlQuery);
			stmt.setLong(1, fkLocacao);
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
		lista.add("Cliente");
		lista.add("Produto");
		lista.add("Usuário");
		lista.add("Quantidade");
		
		
		return lista;
	}
	
	public Vector<String> getCamposBD() {
		Vector<String> lista = new Vector<String>();
		lista.add("Cliente");
		lista.add("Produto");
		lista.add("Usuário");
		lista.add("Quant.");
		lista.add("Preço");
		lista.add("Total");
		
		
		return lista;
	}

	@Override
	public ResultSet listarFiltro(String campo, String busca) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultSet listar() throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

}
