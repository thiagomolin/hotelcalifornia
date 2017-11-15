package hotel.classes.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import hotel.classes.Usuario;

public class UsuarioDAO extends DAO {

	public UsuarioDAO() throws ClassNotFoundException, SQLException {
		super();
	}

	public void inserir(Usuario usuario) throws SQLException, ClassNotFoundException {
		String sqlQuery = "INSERT INTO usuario (ds_usuario, ds_senha, vf_admin) VALUES (?,?,?)";

		try {
			PreparedStatement stmt = this.conexao.getConnection().prepareStatement(sqlQuery);
			stmt.setString(1, usuario.getDsUsuario());
			stmt.setString(2, usuario.getDsSenha());
			stmt.setString(3, usuario.isAdmin());

			stmt.executeUpdate();

			this.conexao.commit();
		} catch (SQLException e) {
			this.conexao.rollback();
			throw e;
		}
	}

	public void alterar(Usuario usuario) throws SQLException, ClassNotFoundException {
		String sqlQuery = "UPDATE usuario SET ds_usuario= ?, ds_senha= ?, vf_admin= ? WHERE id = ?";

		try {
			PreparedStatement stmt = this.conexao.getConnection().prepareStatement(sqlQuery);
			stmt.setString(1, usuario.getDsUsuario());
			stmt.setString(2, usuario.getDsSenha());
			stmt.setString(3, usuario.isAdmin());
			stmt.setLong(4, usuario.getId());

			stmt.executeUpdate();

			this.conexao.commit();
		} catch (SQLException e) {
			this.conexao.rollback();
			throw e;
		}
	}

	public void excluir(Usuario usuario) throws SQLException, ClassNotFoundException {
		String sqlQuery = "DELETE FROM usuario WHERE id = ?";

		try {
			PreparedStatement stmt = this.conexao.getConnection().prepareStatement(sqlQuery);
			stmt.setLong(1, usuario.getId());

			stmt.executeUpdate();

			this.conexao.commit();
		} catch (SQLException e) {
			this.conexao.rollback();
			throw e;
		}
	}

	public Usuario selecionar(long id) throws SQLException, ClassNotFoundException {
		String sqlQuery = "SELECT * FROM usuario WHERE id = ?";

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

	public Usuario selecionar(String ds_usuario) throws SQLException, ClassNotFoundException {
		String sqlQuery = "SELECT * FROM usuario WHERE ds_usuario = ?";

		try {
			PreparedStatement stmt = this.conexao.getConnection().prepareStatement(sqlQuery);
			stmt.setString(1, ds_usuario);

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
		String sqlQuery = "SELECT * FROM usuario ORDER BY id";

		try {
			PreparedStatement stmt = this.conexao.getConnection().prepareStatement(sqlQuery);
			ResultSet rs = stmt.executeQuery();

			return rs;
		} catch (SQLException e) {
			throw e;
		}
	}

	private Usuario parser(ResultSet resultSet) throws SQLException {
		Usuario u = new Usuario(resultSet.getLong("id"), resultSet.getString("ds_usuario"),
				resultSet.getString("ds_senha"), resultSet.getBoolean("vf_admin"));
		return u;
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
