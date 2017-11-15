package hotel.classes.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import hotel.classes.Locacao;
import hotel.util.UtilDatas;

public class LocacaoDAO extends DAO {

	public LocacaoDAO() throws ClassNotFoundException, SQLException {
		super();
	}

	public void inserir(Locacao locacao) throws SQLException, ClassNotFoundException {
		String sqlQuery = "INSERT INTO locacao(fk_cliente, fk_quarto, dt_entrada, dt_saida, fk_status) VALUES (?,?,?,?,?)";

		try {
			PreparedStatement stmt = this.conexao.getConnection().prepareStatement(sqlQuery);
			stmt.setLong(1, locacao.getFkCliente());
			stmt.setLong(2, locacao.getFkQuarto());
			stmt.setDate(3, locacao.getDtEntradaSQL());
			stmt.setDate(4, locacao.getDtSaidaSQL());
			stmt.setLong(5, locacao.getFkStatus());

			stmt.executeUpdate();

			this.conexao.commit();
		} catch (SQLException e) {
			this.conexao.rollback();
			throw e;
		}
	}
	public void alterarStatusLocacao(long id, long status) throws SQLException {
		String sqlQuery = "UPDATE locacao SET fk_status = ? WHERE id = ?";

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

	public void alterar(Locacao locacao) throws SQLException, ClassNotFoundException {
		String sqlQuery = "UPDATE locacao SET fk_cliente = ?, fk_quarto = ?, dt_entrada = ?, dt_saida = ?, fk_status = ? WHERE id = ?";

		try {
			PreparedStatement stmt = this.conexao.getConnection().prepareStatement(sqlQuery);
			stmt.setLong(1, locacao.getFkCliente());
			stmt.setLong(2, locacao.getFkQuarto());
			stmt.setDate(3, locacao.getDtEntradaSQL());
			stmt.setDate(4, locacao.getDtSaidaSQL());
			stmt.setLong(5, locacao.getFkStatus());
			stmt.setLong(6, locacao.getId());

			stmt.executeUpdate();

			this.conexao.commit();
		} catch (SQLException e) {
			this.conexao.rollback();
			throw e;
		}
	}

	public void excluir(Locacao locacao) throws SQLException, ClassNotFoundException {
		String sqlQuery = "DELETE FROM locacao WHERE id = ?";

		try {
			PreparedStatement stmt = this.conexao.getConnection().prepareStatement(sqlQuery);
			stmt.setLong(1, locacao.getId());

			stmt.executeUpdate();

			this.conexao.commit();
		} catch (SQLException e) {
			this.conexao.rollback();
			throw e;
		}
	}

	public Locacao selecionar(long id) throws SQLException, ClassNotFoundException {
		String sqlQuery = "SELECT * FROM locacao WHERE id = ?";

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
		String sqlQuery = "SELECT locacao.id, cliente.nm_cliente, quarto.nr_quarto, locacao.dt_entrada, locacao.dt_saida, status.ds_status "
						+ "FROM locacao "
						+ "INNER JOIN cliente ON cliente.id = locacao.fk_cliente "
						+ "INNER JOIN quarto ON quarto.id = locacao.fk_quarto "
						+ "INNER JOIN status ON status.id = locacao.fk_status";

		try {
			PreparedStatement stmt = this.conexao.getConnection().prepareStatement(sqlQuery);
			ResultSet rs = stmt.executeQuery();

			return rs;
		} catch (SQLException e) {
			throw e;
		}
	}

	public ResultSet listarPorDatas(Date dataDe, Date dataAte) throws SQLException {
		
        String sqlQuery = "SELECT locacao.id, cliente.nm_cliente, quarto.nr_quarto, locacao.dt_entrada, locacao.dt_saida, status.ds_status "
				+ "FROM locacao "
				+ "INNER JOIN cliente ON cliente.id = locacao.fk_cliente "
				+ "INNER JOIN quarto ON quarto.id = locacao.fk_quarto "
				+ "INNER JOIN status ON status.id = locacao.fk_status "
        		+	"WHERE locacao.dt_entrada >= ? AND locacao.dt_saida <= ?";
        		

        try {
			PreparedStatement stmt = this.conexao.getConnection().prepareStatement(sqlQuery);
			stmt.setDate(1, UtilDatas.dateToSQLDate(dataDe));
			stmt.setDate(2, UtilDatas.dateToSQLDate(dataAte));

			
            ResultSet rs = stmt.executeQuery();

            return rs;
        } catch (SQLException e) {
            throw e;
        }
    }
	
	public List<Locacao> getLista() throws SQLException, ClassNotFoundException {
		String sqlQuery = "SELECT * FROM locacao ORDER BY id";

		try {
			PreparedStatement stmt = this.conexao.getConnection().prepareStatement(sqlQuery);
			ResultSet rs = stmt.executeQuery();

			List<Locacao> locacao = new ArrayList<>();

			while (rs.next()) {
				locacao.add(parser(rs));
			}

			return locacao;
		} catch (SQLException e) {
			throw e;
		}
	}
	
	public List<Locacao> getListaAtiva() throws SQLException, ClassNotFoundException {
		String sqlQuery = "SELECT * FROM locacao WHERE fk_status = 1 ORDER BY id";

		try {
			PreparedStatement stmt = this.conexao.getConnection().prepareStatement(sqlQuery);
			ResultSet rs = stmt.executeQuery();

			List<Locacao> locacao = new ArrayList<>();

			while (rs.next()) {
				locacao.add(parser(rs));
			}

			return locacao;
		} catch (SQLException e) {
			throw e;
		}
	}
	
	private Locacao parser(ResultSet resultSet) throws SQLException {
		LocalDate entrada = resultSet.getDate("dt_entrada").toLocalDate();
		LocalDate saida = resultSet.getDate("dt_saida").toLocalDate();

		Locacao r = new Locacao(resultSet.getLong("id"), resultSet.getLong("fk_cliente"), resultSet.getLong("fk_quarto"), entrada, saida, resultSet.getLong("fk_status"));
		return r;
	}

	public Vector<String> getCamposBDAnalitico(){
		Vector<String> lista = new Vector<String>();
		lista.add("ID");
		lista.add("Cliente");
		lista.add("Quarto");
		lista.add("Entrada");
		lista.add("Saida");
		lista.add("Status");
		
		return lista;
	}

	@Override
	public ResultSet listarFiltro(String campo, String busca) throws SQLException, ClassNotFoundException {
		if (campo == Locacao.getCampos().get(0)) {
			String sqlQuery = "SELECT locacao.id, cliente.nm_cliente, quarto.nr_quarto, `dt_entrada`, `dt_saida`, status.ds_status " 
					+ "FROM `locacao` "
					+ "INNER JOIN cliente ON cliente.id = locacao.fk_cliente " 
					+ "INNER JOIN quarto ON quarto.id = locacao.fk_quarto " 
					+ "INNER JOIN status ON status.id = locacao.fk_status " 
					+ "WHERE cliente.nm_cliente LIKE ?";
			try {
				PreparedStatement stmt = this.conexao.getConnection().prepareStatement(sqlQuery);
				stmt.setString(1, "%" + busca + "%");
				ResultSet rs = stmt.executeQuery();
				return rs;
			} catch (SQLException e) {
				throw e;
			} 
		}else{
			String sqlQuery = "SELECT locacao.id, cliente.nm_cliente, quarto.nr_quarto, `dt_entrada`, `dt_saida`, status.ds_status "
					+ "FROM `locacao` "
					+ "INNER JOIN cliente ON cliente.id = locacao.fk_cliente "
					+ "INNER JOIN quarto ON quarto.id = locacao.fk_quarto "
					+ "INNER JOIN status ON status.id = locacao.fk_status "
					+ "WHERE quarto.nr_quarto LIKE ?";
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
