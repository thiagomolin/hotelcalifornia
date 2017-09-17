package hotel.classes.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import hotel.classes.MovimentoFinanceiroSaida;
import hotel.util.UtilDatas;

public class MovimentoFinanceiroSaidaDAO  extends DAO {

	public MovimentoFinanceiroSaidaDAO() throws ClassNotFoundException, SQLException {
		super();
	}

	public void inserir(MovimentoFinanceiroSaida mov) throws SQLException, ClassNotFoundException {
		String sqlQuery = "INSERT INTO financeiro_saida (fk_produto, fk_usuario, nr_valor, dt_atual) VALUES (?,?,?,?)";

		try {
			PreparedStatement stmt = this.conexao.getConnection().prepareStatement(sqlQuery);
			stmt.setLong(1, mov.getFkProduto());
			stmt.setLong(2, mov.getFkUsuario());
			stmt.setFloat(3, mov.getNrValor());
			stmt.setDate(4, mov.getDtAtualSQL());

			stmt.executeUpdate();

			this.conexao.commit();
		} catch (SQLException e) {
			this.conexao.rollback();
			throw e;
		}
	}
	
	public List<MovimentoFinanceiroSaida> listar(LocalDate dataInicial, LocalDate dataFinal) throws SQLException, ClassNotFoundException {
        String sqlQuery = "SELECT * FROM financeiro_saida WHERE dt_atual >= ? AND dt_atual <= ? ORDER BY id DESC";

        try {
            PreparedStatement stmt = this.conexao.getConnection().prepareStatement(sqlQuery);
			stmt.setDate(1, java.sql.Date.valueOf(dataInicial));
			stmt.setDate(2, java.sql.Date.valueOf(dataFinal));
			
            ResultSet rs = stmt.executeQuery();

            List<MovimentoFinanceiroSaida> mov = new ArrayList<>();

            while (rs.next()) {
            	mov.add(parser(rs));
            }

            return mov;
        } catch (SQLException e) {
            throw e;
        }
    }
	
	public ResultSet listar() throws SQLException, ClassNotFoundException {
        String sqlQuery = "SELECT financeiro_saida.id, financeiro_saida.nr_valor, usuario.ds_usuario, financeiro_saida.dt_atual " + 
        		"FROM financeiro_saida " + 
        		"INNER JOIN usuario ON usuario.id = financeiro_saida.fk_usuario";

        try {
            PreparedStatement stmt = this.conexao.getConnection().prepareStatement(sqlQuery);			
            ResultSet rs = stmt.executeQuery();

            return rs;
        } catch (SQLException e) {
            throw e;
        }
    }
	
	public ResultSet listarSintetico() throws SQLException, ClassNotFoundException {
		String sqlQuery = "SELECT financeiro_saida.id, financeiro_saida.nr_valor, usuario.ds_usuario, financeiro_saida.dt_atual " + 
        		"FROM financeiro_saida " + 
        		"INNER JOIN usuario ON usuario.id = financeiro_saida.fk_usuario";

		try {
			PreparedStatement stmt = this.conexao.getConnection().prepareStatement(sqlQuery);
			ResultSet rs = stmt.executeQuery();

			return rs;
		} catch (SQLException e) {
			throw e;
		}
	}

	private MovimentoFinanceiroSaida parser(ResultSet resultSet) throws SQLException {
		LocalDate dtAtual = resultSet.getDate("dt_atual").toLocalDate();
		
		MovimentoFinanceiroSaida r = new MovimentoFinanceiroSaida(resultSet.getLong("id"), 
				resultSet.getLong("fk_produto"), 
				resultSet.getLong("fk_usuario"), 
				resultSet.getInt("nr_quantidade"), 
				dtAtual);
		return r;
	}

	public Vector<String> getCamposBDAnalitico() {
		Vector<String> lista = new Vector<String>();
		lista.add("ID");
		lista.add("Valor");
		lista.add("Usuário");
		lista.add("Data");

		return lista;
	}
	
	public Vector<String> getCamposBDSintetico() {
		Vector<String> lista = new Vector<String>();
		lista.add("ID");
		lista.add("Valor");

		return lista;
	}

	@Override
	public ResultSet listarFiltro(String campo, String busca) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}
	
	public ResultSet listarPorDatasAnalitico(Date dataDe, Date dataAte) throws SQLException {
		
        String sqlQuery = "SELECT financeiro_saida.id, financeiro_saida.nr_valor ,usuario.ds_usuario, financeiro_saida.dt_atual " + 
        		"FROM financeiro_saida " + 
        		"INNER JOIN usuario ON usuario.id = financeiro_saida.fk_usuario "+ 
        		"WHERE financeiro_saida.dt_atual <= ? AND financeiro_saida.dt_atual >= ?";
        		

        try {
			PreparedStatement stmt = this.conexao.getConnection().prepareStatement(sqlQuery);
			stmt.setDate(1, UtilDatas.dateToSQLDate(dataAte));
			stmt.setDate(2, UtilDatas.dateToSQLDate(dataDe));

			
            ResultSet rs = stmt.executeQuery();

            return rs;
        } catch (SQLException e) {
            throw e;
        }
    }
	public ResultSet listarPorDatasSintetico(Date dataDe, Date dataAte) throws SQLException {
		
        String sqlQuery = "SELECT financeiro_saida.id, financeiro_saida.nr_valor " + 
        		"FROM financeiro_saida " + 
        		"INNER JOIN usuario ON usuario.id = financeiro_saida.fk_usuario " + 
        		"WHERE financeiro_saida.dt_atual <= ? AND financeiro_saida.dt_atual >= ?";
        		

        try {
			PreparedStatement stmt = this.conexao.getConnection().prepareStatement(sqlQuery);
			stmt.setDate(1, UtilDatas.dateToSQLDate(dataAte));
			stmt.setDate(2, UtilDatas.dateToSQLDate(dataDe));

			
            ResultSet rs = stmt.executeQuery();

            return rs;
        } catch (SQLException e) {
            throw e;
        }
    }
		
	
	
	
	
	
	
	
	
	
}
