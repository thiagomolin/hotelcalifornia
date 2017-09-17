package hotel.classes.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import hotel.classes.Reserva;

public class ReservaDAO extends DAO {

	public ReservaDAO() throws ClassNotFoundException, SQLException {
		super();
	}

	public void inserir(Reserva reserva) throws SQLException, ClassNotFoundException {
		String sqlQuery = "INSERT INTO reserva(nm_cliente, nr_cpf, fk_quarto, dt_entrada, dt_saida, fk_status) VALUES (?,?,?,?,?,?)";

		try {
			PreparedStatement stmt = this.conexao.getConnection().prepareStatement(sqlQuery);
			stmt.setString(1, reserva.getNomeCliente());
			stmt.setString(2, reserva.getNrCpf());
			stmt.setLong(3, reserva.getFkQuarto());
			stmt.setDate(4, reserva.getDtEntradaSQL());
			stmt.setDate(5, reserva.getDtSaidaSQL());
			stmt.setLong(6, reserva.getFkStatus());

			stmt.executeUpdate();

			this.conexao.commit();
		} catch (SQLException e) {
			this.conexao.rollback();
			throw e;
		}
	}

	public void alterar(Reserva reserva) throws SQLException, ClassNotFoundException {
		String sqlQuery = "UPDATE reserva SET nm_cliente = ?, nr_cpf = ?, fk_quarto = ?, dt_entrada = ?, dt_saida = ?, fk_status = ? WHERE id = ?";

		try {
			PreparedStatement stmt = this.conexao.getConnection().prepareStatement(sqlQuery);
			stmt.setString(1, reserva.getNomeCliente());
			stmt.setString(2, reserva.getNrCpf());
			stmt.setLong(3, reserva.getFkQuarto());
			stmt.setDate(4, reserva.getDtEntradaSQL());
			stmt.setDate(5, reserva.getDtSaidaSQL());
			stmt.setLong(6, reserva.getFkStatus());
			stmt.setLong(7, reserva.getId());

			stmt.executeUpdate();

			this.conexao.commit();
		} catch (SQLException e) {
			this.conexao.rollback();
			throw e;
		}
	}

	public void excluir(Reserva reserva) throws SQLException, ClassNotFoundException {
		String sqlQuery = "DELETE FROM reserva WHERE id = ?";

		try {
			PreparedStatement stmt = this.conexao.getConnection().prepareStatement(sqlQuery);
			stmt.setLong(1, reserva.getId());

			stmt.executeUpdate();

			this.conexao.commit();
		} catch (SQLException e) {
			this.conexao.rollback();
			throw e;
		}
	}

	public Reserva selecionar(long id) throws SQLException, ClassNotFoundException {
		String sqlQuery = "SELECT * FROM reserva WHERE id = ?";

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
		String sqlQuery = "SELECT reserva.id, nm_cliente, nr_cpf, quarto.nr_quarto, dt_entrada, dt_saida, status.ds_status "
				+"FROM reserva "
				+"INNER JOIN quarto ON quarto.id = reserva.fk_quarto "
				+"INNER JOIN status ON status.id = reserva.fk_status ";

		try {
			PreparedStatement stmt = this.conexao.getConnection().prepareStatement(sqlQuery);
			ResultSet rs = stmt.executeQuery();

			return rs;
		} catch (SQLException e) {
			throw e;
		}
	}
	
	public void alterarStatusReserva(long id, long status) throws SQLException {
		String sqlQuery = "UPDATE reserva SET fk_status = ? WHERE id = ?";

		try {
			PreparedStatement stmt = this.conexao.getConnection().prepareStatement(sqlQuery);
			stmt.setLong(1, status);
			stmt.setLong(2, id);
			stmt.executeUpdate();
			

			this.conexao.commit();
		} catch (SQLException e) {
			this.conexao.rollback();
			throw e;
		}
	}
	
	public List<Reserva> getLista() throws SQLException, ClassNotFoundException {
		String sqlQuery = "SELECT * FROM reserva ORDER BY id";

		try {
			PreparedStatement stmt = this.conexao.getConnection().prepareStatement(sqlQuery);
			ResultSet rs = stmt.executeQuery();

			List<Reserva> reserva = new ArrayList<>();

			while (rs.next()) {
				reserva.add(parser(rs));
			}

			return reserva;
		} catch (SQLException e) {
			throw e;
		}
	}
	
	public List<Reserva> getListaAtiva() throws SQLException, ClassNotFoundException {
		String sqlQuery = "SELECT * FROM reserva WHERE fk_status != 2 ORDER BY id";

		try {
			PreparedStatement stmt = this.conexao.getConnection().prepareStatement(sqlQuery);
			ResultSet rs = stmt.executeQuery();

			List<Reserva> reserva = new ArrayList<>();

			while (rs.next()) {
				reserva.add(parser(rs));
			}

			return reserva;
		} catch (SQLException e) {
			throw e;
		}
	}
	
	private Reserva parser(ResultSet resultSet) throws SQLException {
		LocalDate entrada = resultSet.getDate("dt_entrada").toLocalDate();
		LocalDate saida = resultSet.getDate("dt_saida").toLocalDate();

		Reserva r = new Reserva(resultSet.getLong("id"), resultSet.getString("nm_cliente"),
				resultSet.getString("nr_cpf"), resultSet.getLong("fk_quarto"), entrada, saida, resultSet.getLong("fk_status"));
		return r;
	}

	public Vector<String> getCamposBDAnalitico(){
		Vector<String> lista = new Vector<String>();
		lista.add("ID");
		lista.add("Cliente");
		lista.add("Cpf");
		lista.add("Quarto");
		lista.add("Entrada");
		lista.add("Saida");
		lista.add("Status");
		
		return lista;
	}

	@Override
	public ResultSet listarFiltro(String campo, String busca) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

}
