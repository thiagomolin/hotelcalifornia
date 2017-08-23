package hotel.classes.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import hotel.classes.Os;

public class OsDAO extends DAO {

	public OsDAO() throws ClassNotFoundException, SQLException {
		super();
	}

	public void inserir(Os os) throws SQLException, ClassNotFoundException {
		String sqlQuery = "INSERT INTO os (fk_funcionario, fk_quarto, fk_servico, ds_servico) VALUES (?,?,?,?)";

		try {
			PreparedStatement stmt = this.conexao.getConnection().prepareStatement(sqlQuery);
			stmt.setLong(1, os.getFkFuncionario());
			stmt.setLong(2, os.getFkQuarto());
			stmt.setLong(3, os.getFkServico());
			stmt.setString(4, os.getDsServico());

			stmt.executeUpdate();

			this.conexao.commit();
		} catch (SQLException e) {
			this.conexao.rollback();
			throw e;
		}
	}

	public void alterar(Os os) throws SQLException, ClassNotFoundException {
		String sqlQuery = "UPDATE os SET fk_funcionario = ?, fk_quarto = ?, fk_servico = ?, ds_servico = ? WHERE id = ?";

		try {
			PreparedStatement stmt = this.conexao.getConnection().prepareStatement(sqlQuery);
			stmt.setLong(1, os.getFkFuncionario());
			stmt.setLong(2, os.getFkQuarto());
			stmt.setLong(3, os.getFkServico());
			stmt.setString(4, os.getDsServico());
			stmt.setLong(5, os.getId());


			stmt.executeUpdate();

			this.conexao.commit();
		} catch (SQLException e) {
			this.conexao.rollback();
			throw e;
		}
	}

	public void excluir(Os os) throws SQLException, ClassNotFoundException {
		String sqlQuery = "DELETE FROM os WHERE id = ?";

		try {
			PreparedStatement stmt = this.conexao.getConnection().prepareStatement(sqlQuery);
			stmt.setLong(1, os.getId());

			stmt.executeUpdate();

			this.conexao.commit();
		} catch (SQLException e) {
			this.conexao.rollback();
			throw e;
		}
	}

	public Os selecionar(Os os) throws SQLException, ClassNotFoundException {
		String sqlQuery = "SELECT * FROM os WHERE id = ?";

		try {
			PreparedStatement stmt = this.conexao.getConnection().prepareStatement(sqlQuery);
			stmt.setLong(1, os.getId());

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
	
	public List<Os> getLista() throws SQLException, ClassNotFoundException {
		String sqlQuery = "SELECT * FROM os ORDER BY id";

		try {
			PreparedStatement stmt = this.conexao.getConnection().prepareStatement(sqlQuery);
			ResultSet rs = stmt.executeQuery();

			List<Os> os = new ArrayList<>();

			while (rs.next()) {
				os.add(parser(rs));
			}

			return os;
		} catch (SQLException e) {
			throw e;
		}
	}
	
	 public ResultSet listar() throws SQLException, ClassNotFoundException {
	        String sqlQuery = "SELECT * FROM Os ORDER BY id";

	        try {
	            PreparedStatement stmt = this.conexao.getConnection().prepareStatement(sqlQuery);
	            ResultSet rs = stmt.executeQuery();

	            return rs;
	        } catch (SQLException e) {
	            throw e;
	        }
	    }
	
	
	private Os parser(ResultSet resultSet) throws SQLException {
		Os o = new Os(resultSet.getLong("id"), resultSet.getLong("fk_funcionario"), resultSet.getLong("fk_quarto"), resultSet.getLong("fk_servico"), resultSet.getString("ds_servico") );
		return o;
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

