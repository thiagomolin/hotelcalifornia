package hotel.classes.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import hotel.classes.MovimentoEstoque;

public class MovimentoEstoqueDAO extends DAO {

	public MovimentoEstoqueDAO() throws ClassNotFoundException, SQLException {
		super();
	}

	public void inserir(MovimentoEstoque mov) throws SQLException, ClassNotFoundException {
		String sqlQuery = "INSERT INTO movimento_estoque(fk_produto, fk_usuario, fk_tipo_movimento, nr_quantidade, dt_atual) VALUES (?,?,?,?,?)";

		try {
			PreparedStatement stmt = this.conexao.getConnection().prepareStatement(sqlQuery);
			stmt.setLong(1, mov.getFkProduto());
			stmt.setLong(2, mov.getFkUsuario());
			stmt.setLong(3, mov.getFkTipoMovimento());
			stmt.setInt(4, mov.getNrQuantidade());
			stmt.setDate(5, mov.getDtAtualSQL());

			stmt.executeUpdate();

			this.conexao.commit();
		} catch (SQLException e) {
			this.conexao.rollback();
			throw e;
		}
	}
	
	public List<MovimentoEstoque> listarPorProduto(long fkProduto) throws SQLException, ClassNotFoundException {
        String sqlQuery = "SELECT * FROM movimento_estoque WHERE fk_produto = ? ORDER BY id DESC";

        try {
            PreparedStatement stmt = this.conexao.getConnection().prepareStatement(sqlQuery);
			stmt.setLong(1, fkProduto);
			
            ResultSet rs = stmt.executeQuery();

            List<MovimentoEstoque> mov = new ArrayList<>();

            while (rs.next()) {
            	mov.add(parser(rs));
            }

            return mov;
        } catch (SQLException e) {
            throw e;
        }
    }
	
	public List<MovimentoEstoque> listarPorTipoMovimento(long fkTipoMovimento) throws SQLException, ClassNotFoundException {
        String sqlQuery = "SELECT * FROM movimento_estoque WHERE fk_tipo_movimento = ? ORDER BY id DESC";

        try {
            PreparedStatement stmt = this.conexao.getConnection().prepareStatement(sqlQuery);
			stmt.setLong(1, fkTipoMovimento);
			
            ResultSet rs = stmt.executeQuery();

            List<MovimentoEstoque> mov = new ArrayList<>();

            while (rs.next()) {
            	mov.add(parser(rs));
            }

            return mov;
        } catch (SQLException e) {
            throw e;
        }
    }
	
	public List<MovimentoEstoque> listarPorData(LocalDate dataInicial, LocalDate dataFinal) throws SQLException, ClassNotFoundException {
        String sqlQuery = "SELECT * FROM movimento_estoque WHERE dt_atual >= ? AND dt_atual <= ?";

        try {
            PreparedStatement stmt = this.conexao.getConnection().prepareStatement(sqlQuery);
			stmt.setDate(1, java.sql.Date.valueOf(dataInicial));
			stmt.setDate(2, java.sql.Date.valueOf(dataFinal));
			
            ResultSet rs = stmt.executeQuery();

            List<MovimentoEstoque> mov = new ArrayList<>();

            while (rs.next()) {
            	mov.add(parser(rs));
            }

            return mov;
        } catch (SQLException e) {
            throw e;
        }
    }
	
	public List<MovimentoEstoque> listarPorUsuario(long fkUsuario) throws SQLException, ClassNotFoundException {
        String sqlQuery = "SELECT * FROM movimento_estoque WHERE fk_usuario = ? ORDER BY id DESC";

        try {
            PreparedStatement stmt = this.conexao.getConnection().prepareStatement(sqlQuery);
			stmt.setLong(1, fkUsuario);
			
            ResultSet rs = stmt.executeQuery();

            List<MovimentoEstoque> mov = new ArrayList<>();

            while (rs.next()) {
            	mov.add(parser(rs));
            }

            return mov;
        } catch (SQLException e) {
            throw e;
        }
    }
	
	@Override
	public Vector<String> getCamposBD(){
		Vector<String> lista = new Vector<String>();
		lista.add("ID");
		lista.add("Produto");
		lista.add("Usuario");
		lista.add("Tipo Mov.");
		lista.add("Quantidade");
		lista.add("Data");
		
		return lista;
	}

	@Override
	public ResultSet listar() throws SQLException, ClassNotFoundException {
		String sqlQuery = "SELECT movimento_estoque.id, ds_produto, ds_usuario, ds_movimento, nr_quantidade, dt_atual "
						+ "FROM movimento_estoque "
						+ "INNER JOIN produto ON produto.id = movimento_estoque.fk_produto "
						+ "INNER JOIN usuario ON usuario.id = movimento_estoque.fk_usuario "
						+ "INNER JOIN tipo_movimento ON tipo_movimento.id = movimento_estoque.fk_tipo_movimento";
						

		try {
			PreparedStatement stmt = this.conexao.getConnection().prepareStatement(sqlQuery);
			ResultSet rs = stmt.executeQuery();

			return rs;
		} catch (SQLException e) {
			throw e;
		}
	}
	
	
		
	
	private MovimentoEstoque parser(ResultSet resultSet) throws SQLException {
		LocalDate dtAtual = resultSet.getDate("dt_atual").toLocalDate();

		
		MovimentoEstoque r = new MovimentoEstoque(resultSet.getLong("id"), resultSet.getLong("fk_produto"), 
				resultSet.getLong("fk_usuario"), resultSet.getLong("fk_tipo_movimento"), 
				resultSet.getInt("nr_quantidade"), dtAtual);
		return r;
	}



	@Override
	public ResultSet listarFiltro(String campo, String busca) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	
	
	
}
