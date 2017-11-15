package hotel.classes.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import hotel.classes.Produto;

public class ProdutoDAO extends DAO {

	public ProdutoDAO() throws ClassNotFoundException, SQLException {
		super();
	}

	public void inserir(Produto produto) throws SQLException, ClassNotFoundException {
		String sqlQuery = "INSERT INTO produto (ds_produto, fk_fornecedor, nr_valor_compra, nr_valor_venda, vf_consumivel) VALUES (?, ?, ?, ?, ?)";

		try {
			PreparedStatement stmt = this.conexao.getConnection().prepareStatement(sqlQuery);
			stmt.setString(1, produto.getDsProduto());
			stmt.setLong(2, produto.getFkFornecedor());
			stmt.setFloat(3, produto.getNrValorCompra());
			stmt.setFloat(4, produto.getNrValorVenda());
			stmt.setBoolean(5, produto.isConsumivel());

			stmt.executeUpdate();

			this.conexao.commit();
		} catch (SQLException e) {
			this.conexao.rollback();
			throw e;
		}
	}

	public void alterar(Produto produto) throws SQLException, ClassNotFoundException {
		String sqlQuery = "UPDATE produto SET ds_produto = ?, fk_fornecedor = ?, nr_valor_compra = ?, nr_valor_venda = ?, vf_consumivel = ? WHERE id = ?";

		try {
			PreparedStatement stmt = this.conexao.getConnection().prepareStatement(sqlQuery);
			stmt.setString(1, produto.getDsProduto());
			stmt.setLong(2, produto.getFkFornecedor());
			stmt.setFloat(3, produto.getNrValorCompra());
			stmt.setFloat(4, produto.getNrValorVenda());
			stmt.setBoolean(5, produto.isConsumivel());
			stmt.setLong(6, produto.getId());

			stmt.executeUpdate();

			this.conexao.commit();
		} catch (SQLException e) {
			this.conexao.rollback();
			throw e;
		}
	}

	public void excluir(Produto produto) throws SQLException, ClassNotFoundException {
		String sqlQuery = "DELETE FROM produto WHERE id = ?";

		try {
			PreparedStatement stmt = this.conexao.getConnection().prepareStatement(sqlQuery);
			stmt.setLong(1, produto.getId());

			stmt.executeUpdate();

			this.conexao.commit();
		} catch (SQLException e) {
			this.conexao.rollback();
			throw e;
		}
	}

	public Produto selecionar(long id) throws SQLException, ClassNotFoundException {
		String sqlQuery = "SELECT * FROM produto WHERE id = ?";

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
		String sqlQuery = "SELECT produto.id, ds_produto, nm_fornecedor, nr_valor_compra, nr_valor_venda, vf_consumivel, fk_fornecedor"
						+ " FROM produto"
						+ " INNER JOIN fornecedor ON fornecedor.id = produto.fk_fornecedor";

		try {
			PreparedStatement stmt = this.conexao.getConnection().prepareStatement(sqlQuery);
			ResultSet rs = stmt.executeQuery();

			return rs;
		} catch (SQLException e) {
			throw e;
		}
	}

	public List<Produto> getLista() throws SQLException, ClassNotFoundException {
		String sqlQuery = "SELECT * FROM produto ORDER BY id";

		try {
			PreparedStatement stmt = this.conexao.getConnection().prepareStatement(sqlQuery);
			ResultSet rs = stmt.executeQuery();

			List<Produto> produto = new ArrayList<>();

			while (rs.next()) {
				produto.add(parser(rs));
			}

			return produto;
		} catch (SQLException e) {
			throw e;
		}
	}

	private Produto parser(ResultSet resultSet) throws SQLException {
		Produto p = new Produto(resultSet.getLong("id"), resultSet.getString("ds_produto"), resultSet.getLong("fk_fornecedor"),
				resultSet.getFloat("nr_valor_compra"), resultSet.getFloat("nr_valor_venda"),
				resultSet.getBoolean("vf_consumivel"));
		return p;
	}

	@Override
	public Vector<String> getCamposBDAnalitico() {
		Vector<String> lista = new Vector<String>();
		lista.add("ID");
		lista.add("Nome");
		lista.add("Fornecedor");
		lista.add("Vlr. Compra");
		lista.add("Vlr. Venda");
		
		
		return lista;
	}

	@Override
	public ResultSet listarFiltro(String campo, String busca) throws SQLException, ClassNotFoundException {
		if (campo == Produto.getCampos().get(0)) {
			String sqlQuery = "SELECT produto.id, produto.ds_produto, fornecedor.nm_fornecedor, nr_valor_compra, nr_valor_venda, vf_consumivel, fk_fornecedor "
					+ "FROM produto "
					+ "INNER JOIN fornecedor ON fornecedor.id = produto.fk_fornecedor " 
					+ "WHERE produto.ds_produto LIKE ?";
			try {
				PreparedStatement stmt = this.conexao.getConnection().prepareStatement(sqlQuery);
				stmt.setString(1, "%" + busca + "%");
				ResultSet rs = stmt.executeQuery();
				return rs;
			} catch (SQLException e) {
				throw e;
			} 
		}else{
			String sqlQuery = "SELECT produto.id, ds_produto, fornecedor.nm_fornecedor, nr_valor_compra, nr_valor_venda, vf_consumivel, fk_fornecedor"
					+ " FROM produto"
					+ " INNER JOIN fornecedor ON fornecedor.id = produto.fk_fornecedor "
					+ "WHERE fornecedor.nm_fornecedor LIKE ?";
			try {
				PreparedStatement stmt = this.conexao.getConnection().prepareStatement(sqlQuery);
				stmt.setString(1, "%" + busca + "%");
				ResultSet rs = stmt.executeQuery();
				return rs;
			} catch (SQLException e) {
				throw e;
			} 
		}
	}

}
