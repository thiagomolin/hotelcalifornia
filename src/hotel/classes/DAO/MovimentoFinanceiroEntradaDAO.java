package hotel.classes.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import hotel.classes.MovimentoFinanceiroEntrada;
import hotel.util.UtilDatas;

public class MovimentoFinanceiroEntradaDAO  extends DAO {

	public MovimentoFinanceiroEntradaDAO() throws ClassNotFoundException, SQLException {
		super();
	}

	public void inserir(MovimentoFinanceiroEntrada mov) throws SQLException, ClassNotFoundException {
		String sqlQuery = "INSERT INTO financeiro_entrada (fk_locacao, fk_usuario, nr_valor, dt_lancamento) VALUES (?,?,?,?)";

		try {
			PreparedStatement stmt = this.conexao.getConnection().prepareStatement(sqlQuery);
			stmt.setLong(1, mov.getFkLocacao());
			stmt.setLong(2, mov.getFkUsuario());
			stmt.setFloat(3, mov.getNrValor());
			stmt.setDate(4, mov.getDtLancamentoSQL());

			stmt.executeUpdate();

			this.conexao.commit();
		} catch (SQLException e) {
			this.conexao.rollback();
			throw e;
		}
	}
	
	public List<MovimentoFinanceiroEntrada> listar(LocalDate dataInicial, LocalDate dataFinal) throws SQLException, ClassNotFoundException {
        String sqlQuery = "SELECT * FROM financeiro_entrada WHERE dt_lancamento >= ? AND dt_lancamento <= ? ORDER BY id DESC";

        try {
            PreparedStatement stmt = this.conexao.getConnection().prepareStatement(sqlQuery);
			stmt.setDate(1, java.sql.Date.valueOf(dataInicial));
			stmt.setDate(2, java.sql.Date.valueOf(dataFinal));
			
            ResultSet rs = stmt.executeQuery();

            List<MovimentoFinanceiroEntrada> mov = new ArrayList<>();

            while (rs.next()) {
            	mov.add(parser(rs));
            }

            return mov;
        } catch (SQLException e) {
            throw e;
        }
    }
	
	public ResultSet listar() throws SQLException, ClassNotFoundException {
        String sqlQuery = "SELECT financeiro_entrada.id, usuario.ds_usuario, financeiro_entrada.nr_valor, financeiro_entrada.dt_lancamento " + 
        		"FROM financeiro_entrada " + 
        		"INNER JOIN usuario ON usuario.id = financeiro_entrada.fk_usuario";

        try {
            PreparedStatement stmt = this.conexao.getConnection().prepareStatement(sqlQuery);			
            ResultSet rs = stmt.executeQuery();

            return rs;
        } catch (SQLException e) {
            throw e;
        }
    }
	
	public ResultSet listarSintetico() throws SQLException, ClassNotFoundException {
		String sqlQuery = "SELECT financeiro_entrada.id, financeiro_entrada.nr_valor " + 
        		"FROM financeiro_entrada " + 
        		"INNER JOIN usuario ON usuario.id = financeiro_entrada.fk_usuario";

		try {
			PreparedStatement stmt = this.conexao.getConnection().prepareStatement(sqlQuery);
			ResultSet rs = stmt.executeQuery();

			return rs;
		} catch (SQLException e) {
			throw e;
		}
	}
	
	private MovimentoFinanceiroEntrada parser(ResultSet resultSet) throws SQLException {
		LocalDate dtAtual = resultSet.getDate("dt_atual").toLocalDate();
		
		MovimentoFinanceiroEntrada r = new MovimentoFinanceiroEntrada(resultSet.getLong("id"), 
				resultSet.getLong("fk_locacao"), 
				resultSet.getLong("fk_usuario"), 
				resultSet.getInt("nr_quantidade"), 
				dtAtual);
		return r;
	}

	@Override
	public Vector<String> getCamposBDAnalitico() {
		Vector<String> lista = new Vector<String>();
		lista.add("ID");
		lista.add("Usuário");
		lista.add("Valor");
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
		
        String sqlQuery = "SELECT financeiro_entrada.id, usuario.ds_usuario, financeiro_entrada.nr_valor, financeiro_entrada.dt_lancamento " + 
        		"FROM financeiro_entrada  " + 
        		"INNER JOIN usuario ON usuario.id = financeiro_entrada.fk_usuario " + 
        		"WHERE financeiro_entrada.dt_lancamento <= ? AND financeiro_entrada.dt_lancamento >= ?";
        		

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
		
        String sqlQuery = "SELECT financeiro_entrada.id, financeiro_entrada.nr_valor" + 
        		"FROM financeiro_entrada " + 
        		"INNER JOIN usuario ON usuario.id = financeiro_entrada.fk_usuario " + 
        		"WHERE financeiro_entrada.dt_lancamento <= ? AND financeiro_entrada.dt_lancamento >= ?";
        		

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

