package hotel.classes.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import hotel.classes.Funcionario;

public class FuncionarioDAO extends DAO {

	public FuncionarioDAO() throws ClassNotFoundException, SQLException {
		super();
	}

	public void inserir(Funcionario funcionario) throws SQLException, ClassNotFoundException {
		String sqlQuery = "INSERT INTO funcionario(nm_funcionario, nr_cpf, nr_telefone, ds_email, ds_endereco, fk_cidade, fk_estado, fK_pais, fk_cargo) VALUES (?,?,?,?,?,?,?,?,?)";

		try {
			PreparedStatement stmt = this.conexao.getConnection().prepareStatement(sqlQuery);
			stmt.setString(1, funcionario.getNmFuncionario());
			stmt.setString(2, funcionario.getNrCpf());
			stmt.setString(3, funcionario.getNrTelefone());
			stmt.setString(4, funcionario.getDsEmail());
			stmt.setString(5, funcionario.getDsEndereco());
			stmt.setLong(6, funcionario.getFkCidade());
			stmt.setLong(7, funcionario.getFkEstado());
			stmt.setLong(8, funcionario.getFkPais());
			stmt.setLong(9, funcionario.getFkCargo());

			stmt.executeUpdate();

			this.conexao.commit();
		} catch (SQLException e) {
			this.conexao.rollback();
			throw e;
		}
	}

	public void alterar(Funcionario funcionario) throws SQLException, ClassNotFoundException {
		String sqlQuery = "UPDATE funcionario SET nm_funcionario = ?, nr_cpf = ?, nr_telefone = ?, ds_email = ?, ds_endereco = ?, fk_cidade = ?, fk_estado = ?, fK_pais = ?, fk_cargo = ?  WHERE id = ?";

		try {
			PreparedStatement stmt = this.conexao.getConnection().prepareStatement(sqlQuery);
			stmt.setString(1, funcionario.getNmFuncionario());
			stmt.setString(2, funcionario.getNrCpf());
			stmt.setString(3, funcionario.getNrTelefone());
			stmt.setString(4, funcionario.getDsEmail());
			stmt.setString(5, funcionario.getDsEndereco());
			stmt.setLong(6, funcionario.getFkCidade());
			stmt.setLong(7, funcionario.getFkEstado());
			stmt.setLong(8, funcionario.getFkPais());
			stmt.setLong(9, funcionario.getFkCargo());
			stmt.setLong(10, funcionario.getId());

			stmt.executeUpdate();

			this.conexao.commit();
		} catch (SQLException e) {
			this.conexao.rollback();
			throw e;
		}
	}

	public void excluir(Funcionario funcionario) throws SQLException, ClassNotFoundException {
		String sqlQuery = "DELETE FROM funcionario WHERE id = ?";

		try {
			PreparedStatement stmt = this.conexao.getConnection().prepareStatement(sqlQuery);
			stmt.setLong(1, funcionario.getId());

			stmt.executeUpdate();

			this.conexao.commit();
		} catch (SQLException e) {
			this.conexao.rollback();
			throw e;
		}
	}

	public Funcionario selecionar(long id) throws SQLException, ClassNotFoundException {
		String sqlQuery = "SELECT * FROM funcionario WHERE id = ?";

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
		String sqlQuery = "SELECT funcionario.id, `nm_funcionario`, cargo.ds_cargo, `nr_cpf`, `nr_telefone`, `ds_email`, `ds_endereco`,  cidade.ds_cidade, estado.ds_estado, pais.ds_pais "
				+ "FROM funcionario "
				+ "INNER JOIN pais ON pais.id = funcionario.fk_pais "
				+ "INNER JOIN estado ON estado.id = funcionario.fk_estado "
				+ "INNER JOIN cidade ON cidade.id = funcionario.fk_cidade "
				+ "INNER JOIN cargo ON cargo.id = funcionario.fk_cargo";

		try {
			PreparedStatement stmt = this.conexao.getConnection().prepareStatement(sqlQuery);
			ResultSet rs = stmt.executeQuery();

			return rs;
		} catch (SQLException e) {
			throw e;
		}
	}
	
	public ResultSet listarFiltro(String campo, String busca) throws SQLException, ClassNotFoundException {
		if (campo == Funcionario.getCampos().get(0)) {
			String sqlQuery = "SELECT funcionario.id, `nm_funcionario`, cargo.ds_cargo, `nr_cpf`, `nr_telefone`, `ds_email`, `ds_endereco`,  cidade.ds_cidade, estado.ds_estado, pais.ds_pais "
					+ "FROM funcionario " + "INNER JOIN pais ON pais.id = funcionario.fk_pais "
					+ "INNER JOIN estado ON estado.id = funcionario.fk_estado "
					+ "INNER JOIN cidade ON cidade.id = funcionario.fk_cidade "
					+ "INNER JOIN cargo ON cargo.id = funcionario.fk_cargo "
					+ "WHERE nm_funcionario LIKE ?";
			try {
				PreparedStatement stmt = this.conexao.getConnection().prepareStatement(sqlQuery);
				stmt.setString(1, "%" + busca + "%");
				ResultSet rs = stmt.executeQuery();

				return rs;
			} catch (SQLException e) {
				throw e;
			} 
		}else {
			String sqlQuery = "SELECT funcionario.id, `nm_funcionario`, cargo.ds_cargo, `nr_cpf`, `nr_telefone`, `ds_email`, `ds_endereco`,  cidade.ds_cidade, estado.ds_estado, pais.ds_pais "
					+ "FROM funcionario " + "INNER JOIN pais ON pais.id = funcionario.fk_pais "
					+ "INNER JOIN estado ON estado.id = funcionario.fk_estado "
					+ "INNER JOIN cidade ON cidade.id = funcionario.fk_cidade "
					+ "INNER JOIN cargo ON cargo.id = funcionario.fk_cargo "
					+ "WHERE nr_cpf LIKE ?";
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

	public List<Funcionario> getLista() throws SQLException, ClassNotFoundException {
		String sqlQuery = "SELECT * FROM funcionario ORDER BY id";

		try {
			PreparedStatement stmt = this.conexao.getConnection().prepareStatement(sqlQuery);
			ResultSet rs = stmt.executeQuery();

			List<Funcionario> funcionario = new ArrayList<>();

			while (rs.next()) {
				funcionario.add(parser(rs));
			}

			return funcionario;
		} catch (SQLException e) {
			throw e;
		}
	}

	private Funcionario parser(ResultSet resultSet) throws SQLException {
		Funcionario q = new Funcionario(resultSet.getLong("id"), resultSet.getString("nm_funcionario"), resultSet.getLong("fk_cargo"),
				resultSet.getString("nr_cpf"), resultSet.getString("nr_telefone"), resultSet.getString("ds_email"),
				resultSet.getString("ds_endereco"), resultSet.getLong("fk_cidade"), resultSet.getLong("fk_estado"),
				resultSet.getLong("fK_pais"));
		return q;
	}

	public Vector<String> getCamposBD() {
		Vector<String> lista = new Vector<String>();
		lista.add("id");
		lista.add("Nome");
		lista.add("Cargo");
		lista.add("CPF");
		lista.add("Telefone");
		lista.add("Email");
		lista.add("Endereço");
		lista.add("Cidade");
		lista.add("Estado");
		lista.add("País");

		return lista;
	}

}
