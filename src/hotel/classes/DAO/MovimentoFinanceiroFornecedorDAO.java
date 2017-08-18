package hotel.classes.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import hotel.classes.MovimentoFinanceiroFornecedor;

public class MovimentoFinanceiroFornecedorDAO  extends DAO {

	public MovimentoFinanceiroFornecedorDAO() throws ClassNotFoundException, SQLException {
		super();
	}

	public void inserir(MovimentoFinanceiroFornecedor mov) throws SQLException, ClassNotFoundException {
		String sqlQuery = "INSERT INTO financeiro_fornecedor(fk_fornecedor, fk_produto, fk_usuario, nr_quantidade, dt_atual) VALUES (?,?,?,?,?)";

		try {
			PreparedStatement stmt = this.conexao.getConnection().prepareStatement(sqlQuery);
			stmt.setLong(1, mov.getFkFornecedor());
			stmt.setLong(2, mov.getFkProduto());
			stmt.setLong(3, mov.getFkUsuario());
			stmt.setInt(4, mov.getNrQuantidade());
			stmt.setDate(5, mov.getDtAtualSQL());

			stmt.executeUpdate();

			this.conexao.commit();
		} catch (SQLException e) {
			this.conexao.rollback();
			throw e;
		}
	}
	
	public List<MovimentoFinanceiroFornecedor> listar(LocalDate dataInicial, LocalDate dataFinal) throws SQLException, ClassNotFoundException {
        String sqlQuery = "SELECT * FROM financeiro_fornecedor WHERE dt_atual >= ? AND dt_atual <= ? ORDER BY id DESC";

        try {
            PreparedStatement stmt = this.conexao.getConnection().prepareStatement(sqlQuery);
			stmt.setDate(1, java.sql.Date.valueOf(dataInicial));
			stmt.setDate(2, java.sql.Date.valueOf(dataFinal));
			
            ResultSet rs = stmt.executeQuery();

            List<MovimentoFinanceiroFornecedor> mov = new ArrayList<>();

            while (rs.next()) {
            	mov.add(parser(rs));
            }

            return mov;
        } catch (SQLException e) {
            throw e;
        }
    }
	
	public ResultSet listar() throws SQLException, ClassNotFoundException {
        String sqlQuery = "SELECT * FROM financeiro_fornecedor ORDER BY id DESC";

        try {
            PreparedStatement stmt = this.conexao.getConnection().prepareStatement(sqlQuery);			
            ResultSet rs = stmt.executeQuery();

            return rs;
        } catch (SQLException e) {
            throw e;
        }
    }

	private MovimentoFinanceiroFornecedor parser(ResultSet resultSet) throws SQLException {
		LocalDate dtAtual = resultSet.getDate("dt_atual").toLocalDate();
		
		MovimentoFinanceiroFornecedor r = new MovimentoFinanceiroFornecedor(resultSet.getLong("id"), resultSet.getLong("fk_fornecedor"), 
				resultSet.getLong("fk_produto"), 
				resultSet.getLong("fk_usuario"), 
				resultSet.getInt("nr_quantidade"), 
				dtAtual);
		return r;
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
