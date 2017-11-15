package hotel.classes.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import hotel.classes.Fornecedor;

public class FornecedorDAO extends DAO {

	public FornecedorDAO() throws ClassNotFoundException, SQLException {
		super();
	}

	public void inserir(Fornecedor fornecedor) throws SQLException, ClassNotFoundException {
		String sqlQuery = "INSERT INTO fornecedor(nm_fornecedor, nr_CNPJ, nr_telefone, ds_email, ds_endereco, fk_cidade, fk_estado, fK_pais) VALUES (?,?,?,?,?,?,?,?)";

		try {
			PreparedStatement stmt = this.conexao.getConnection().prepareStatement(sqlQuery);
			stmt.setString(1, fornecedor.getNmFornecedor());
			stmt.setString(2, fornecedor.getNrCnpj());
			stmt.setString(3, fornecedor.getNrTelefone());
			stmt.setString(4, fornecedor.getDsEmail());
			stmt.setString(5, fornecedor.getDsEndereco());
			stmt.setLong(6, fornecedor.getFkCidade());
			stmt.setLong(7, fornecedor.getFkEstado());
			stmt.setLong(8, fornecedor.getFkPais());

			stmt.executeUpdate();

			this.conexao.commit();
		} catch (SQLException e) {
			this.conexao.rollback();
			throw e;
		}
	}

	public void alterar(Fornecedor fornecedor) throws SQLException, ClassNotFoundException {
		String sqlQuery = "UPDATE fornecedor SET nm_fornecedor = ?, nr_CNPJ = ?, nr_telefone = ?, ds_email = ?, ds_endereco = ?, fk_cidade = ?, fk_estado = ?, fK_pais = ?  WHERE id = ?";

		try {
			PreparedStatement stmt = this.conexao.getConnection().prepareStatement(sqlQuery);
			stmt.setString(1, fornecedor.getNmFornecedor());
			stmt.setString(2, fornecedor.getNrCnpj());
			stmt.setString(3, fornecedor.getNrTelefone());
			stmt.setString(4, fornecedor.getDsEmail());
			stmt.setString(5, fornecedor.getDsEndereco());
			stmt.setLong(6, fornecedor.getFkCidade());
			stmt.setLong(7, fornecedor.getFkEstado());
			stmt.setLong(8, fornecedor.getFkPais());
			stmt.setLong(9, fornecedor.getId());

			stmt.executeUpdate();

			this.conexao.commit();
		} catch (SQLException e) {
			this.conexao.rollback();
			throw e;
		}
	}

	public void excluir(Fornecedor fornecedor) throws SQLException, ClassNotFoundException {
		String sqlQuery = "DELETE FROM fornecedor WHERE id = ?";

		try {
			PreparedStatement stmt = this.conexao.getConnection().prepareStatement(sqlQuery);
			stmt.setLong(1, fornecedor.getId());

			stmt.executeUpdate();

			this.conexao.commit();
		} catch (SQLException e) {
			this.conexao.rollback();
			throw e;
		}
	}

	public Fornecedor selecionar(long id) throws SQLException, ClassNotFoundException {
		String sqlQuery = "SELECT * FROM fornecedor WHERE id = ?";

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
		String sqlQuery = "SELECT fornecedor.id, `nm_fornecedor`, `nr_cnpj`, `nr_telefone`, `ds_endereco`, `ds_email`, cidade.ds_cidade, estado.ds_estado, pais.ds_pais "
				+ "FROM fornecedor " + "INNER JOIN pais ON pais.id = fornecedor.fk_pais "
				+ "INNER JOIN estado ON estado.id = fornecedor.fk_estado "
				+ "INNER JOIN cidade ON cidade.id = fornecedor.fk_cidade";

		try {
			PreparedStatement stmt = this.conexao.getConnection().prepareStatement(sqlQuery);
			ResultSet rs = stmt.executeQuery();

			return rs;
		} catch (SQLException e) {
			throw e;
		}
	}
	
	public ResultSet listarFiltro(String campo, String busca) throws SQLException, ClassNotFoundException {
		if (campo == Fornecedor.getCampos().get(0)) {
			String sqlQuery = "SELECT fornecedor.id, `nm_fornecedor`, `nr_cnpj`, `nr_telefone`, `ds_endereco`, `ds_email`, cidade.ds_cidade, estado.ds_estado, pais.ds_pais "
					+ "FROM fornecedor " + "INNER JOIN pais ON pais.id = fornecedor.fk_pais "
					+ "INNER JOIN estado ON estado.id = fornecedor.fk_estado "
					+ "INNER JOIN cidade ON cidade.id = fornecedor.fk_cidade "
					+ "WHERE nm_fornecedor LIKE ?";
			try {
				PreparedStatement stmt = this.conexao.getConnection().prepareStatement(sqlQuery);
				stmt.setString(1, "%" + busca + "%");
				ResultSet rs = stmt.executeQuery();

				return rs;
			} catch (SQLException e) {
				throw e;
			} 
		}else{
			String sqlQuery = "SELECT fornecedor.id, `nm_fornecedor`, `nr_cnpj`, `nr_telefone`, `ds_endereco`, `ds_email`, cidade.ds_cidade, estado.ds_estado, pais.ds_pais "
					+ "FROM fornecedor " + "INNER JOIN pais ON pais.id = fornecedor.fk_pais "
					+ "INNER JOIN estado ON estado.id = fornecedor.fk_estado "
					+ "INNER JOIN cidade ON cidade.id = fornecedor.fk_cidade "
					+ "WHERE nr_cnpj LIKE ?";
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

	public List<Fornecedor> getLista() throws SQLException, ClassNotFoundException {
		String sqlQuery = "SELECT * FROM fornecedor ORDER BY id";

		try {
			PreparedStatement stmt = this.conexao.getConnection().prepareStatement(sqlQuery);
			ResultSet rs = stmt.executeQuery();

			List<Fornecedor> fornecedor = new ArrayList<>();

			while (rs.next()) {
				fornecedor.add(parser(rs));
			}

			return fornecedor;
		} catch (SQLException e) {
			throw e;
		}
	}

	private Fornecedor parser(ResultSet resultSet) throws SQLException {
		Fornecedor q = new Fornecedor(resultSet.getLong("id"), resultSet.getString("nm_fornecedor"),
				resultSet.getString("nr_CNPJ"), resultSet.getString("nr_telefone"), resultSet.getString("ds_email"),
				resultSet.getString("ds_endereco"), resultSet.getLong("fk_cidade"), resultSet.getLong("fk_estado"),
				resultSet.getLong("fK_pais"));
		return q;
	}

	public Vector<String> getCamposBDAnalitico() {
		Vector<String> lista = new Vector<String>();
		lista.add("id");
		lista.add("Nome");
		lista.add("CNPJ");
		lista.add("Telefone");
		lista.add("Email");
		lista.add("Endereço");
		lista.add("Cidade");
		lista.add("Estado");
		lista.add("País");

		return lista;
	}

	

}
