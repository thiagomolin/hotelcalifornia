package hotel.classes.DAO;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.JOptionPane;

import hotel.classes.Quarto;

public class QuartoDAO extends DAO {

	public QuartoDAO() throws ClassNotFoundException, SQLException {
		super();
	}

	public void inserir(Quarto quarto) throws SQLException, ClassNotFoundException {
		String sqlQuery = "INSERT INTO quarto (nr_quarto, fk_tipo_quarto, fk_reserva, fk_locacao, vf_disponivel) VALUES (?, ?, ?, ?, ?)";

		try {
			PreparedStatement stmt = this.conexao.getConnection().prepareStatement(sqlQuery);
			stmt.setString(1, quarto.getNrQuarto());
			stmt.setLong(2, quarto.getFkTipoQuarto());
			stmt.setNull(3, Types.BIGINT);
			stmt.setNull(4, Types.BIGINT);
			stmt.setBoolean(5, quarto.isDisponivel());
			
			stmt.executeUpdate();

			this.conexao.commit();
		} catch (SQLException e) {
			this.conexao.rollback();
			throw e;
		}
	}

	public void alterar(Quarto quarto) throws SQLException, ClassNotFoundException {
		String sqlQuery = "UPDATE quarto SET nr_quarto = ?, fk_tipo_quarto = ?, vf_disponivel = ? WHERE id = ?";

		try {
			PreparedStatement stmt = this.conexao.getConnection().prepareStatement(sqlQuery);
			stmt.setString(1, quarto.getNrQuarto());
			stmt.setLong(2, quarto.getFkTipoQuarto());
			stmt.setBoolean(3, quarto.isDisponivel());
			stmt.setLong(4, quarto.getId());

			stmt.executeUpdate();

			this.conexao.commit();
		} catch (SQLException e) {
			this.conexao.rollback();
			throw e;
		}
	}

	public void excluir(Quarto quarto) throws SQLException, ClassNotFoundException {
		String sqlQuery = "DELETE FROM quarto WHERE id = ?";

		try {
			PreparedStatement stmt = this.conexao.getConnection().prepareStatement(sqlQuery);
			stmt.setLong(1, quarto.getId());

			stmt.executeUpdate();

			this.conexao.commit();
		} catch (SQLException e) {
			this.conexao.rollback();
			throw e;
		}
	}

	public Quarto selecionar(Long id) throws SQLException, ClassNotFoundException {
		String sqlQuery = "SELECT * FROM quarto WHERE id = ?";

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
		String sqlQuery = "SELECT * FROM quarto ORDER BY id";

		try {
			PreparedStatement stmt = this.conexao.getConnection().prepareStatement(sqlQuery);
			ResultSet rs = stmt.executeQuery();

			return rs;
		} catch (SQLException e) {
			throw e;
		}
	}
	
	public Quarto selecionarQuartoDisponivel(Date entrada, Date saida) {
		String sqlQuery = "SELECT * FROM quarto WHERE quarto.id NOT IN "
				+ "(SELECT quarto.id FROM quarto INNER JOIN reserva ON quarto.id = reserva.fk_quarto "
				+ "WHERE ((? > dt_saida) OR ((? < dt_saida) "
				+ "AND (? < dt_entrada))) AND fk_status = 1)"
				+ " AND  quarto.vf_disponivel = 1";
		
		try {
			PreparedStatement stmt = this.conexao.getConnection().prepareStatement(sqlQuery);
			stmt.setDate(1, entrada);
			stmt.setDate(2, entrada);
			stmt.setDate(3, saida);
			ResultSet rs = stmt.executeQuery();
			
			if (rs.next()) {
				return parser(rs);
			}else {
				JOptionPane.showMessageDialog(null, "Eeeita, nao tem cuarto");
			}

		} catch (SQLException e) {
			
		}
		
		return null;
	}
	
	public ResultSet listarFiltro(String campo, String busca) throws SQLException, ClassNotFoundException {
		return null;		
	}

	public List<Quarto> getLista() throws SQLException, ClassNotFoundException {
		String sqlQuery = "SELECT * FROM quarto ORDER BY nr_quarto";

		try {
			PreparedStatement stmt = this.conexao.getConnection().prepareStatement(sqlQuery);
			ResultSet rs = stmt.executeQuery();

			List<Quarto> quarto = new ArrayList<>();

			while (rs.next()) {
				quarto.add(parser(rs));
			}

			return quarto;
		} catch (SQLException e) {
			throw e;
		}
	}

	private Quarto parser(ResultSet resultSet) throws SQLException {
		Quarto q = new Quarto(resultSet.getLong("id"), resultSet.getString("nr_quarto"),resultSet.getLong("fk_tipo_quarto"),resultSet.getLong("fk_reserva"),resultSet.getLong("fk_locacao"), resultSet.getBoolean("vf_disponivel"));
		return q;
	}

	@Override
	public Vector<String> getCamposBD(){
		Vector<String> lista = new Vector<String>();
		lista.add("ID");
		lista.add("Numero do Quarto");
		lista.add("Tipo de Quarto");
		lista.add("Disponibilidade");
		
		return lista;
	}


}
