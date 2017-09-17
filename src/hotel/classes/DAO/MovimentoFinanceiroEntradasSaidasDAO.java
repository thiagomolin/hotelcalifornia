package hotel.classes.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Vector;

import hotel.util.UtilDatas;

public class MovimentoFinanceiroEntradasSaidasDAO extends DAO{

	public MovimentoFinanceiroEntradasSaidasDAO() throws ClassNotFoundException, SQLException {
			super();
		}
	
	public ResultSet listar() throws SQLException, ClassNotFoundException {
	        String sqlQuery = "SELECT usuario.ds_usuario, financeiro_entrada.nr_valor, financeiro_entrada.dt_lancamento AS data_l " + 
	        		"FROM financeiro_entrada " + 
	        		"INNER JOIN usuario ON usuario.id = financeiro_entrada.fk_usuario " + 
	        		"UNION ALL " + 
	        		"SELECT usuario.ds_usuario, financeiro_saida.nr_valor, financeiro_saida.dt_atual AS data_l " + 
	        		"FROM financeiro_saida " + 
	        		"INNER JOIN usuario ON usuario.id = financeiro_saida.fk_usuario " + 
	        		"ORDER BY data_l ";

	        try {
	            PreparedStatement stmt = this.conexao.getConnection().prepareStatement(sqlQuery);			
	            ResultSet rs = stmt.executeQuery();

	            return rs;
	        } catch (SQLException e) {
	            throw e;
	        }
	    }
		
	public ResultSet listarSintetico() throws SQLException, ClassNotFoundException {
			String sqlQuery = "SELECT financeiro_entrada.dt_lancamento AS data_l, financeiro_entrada.nr_valor " + 
	        		"FROM financeiro_entrada " + 
	        		"UNION ALL " + 
	        		"SELECT financeiro_saida.dt_atual AS data_l, financeiro_saida.nr_valor " + 
	        		"FROM financeiro_saida " + 
	        		"ORDER BY data_l ";

			try {
				PreparedStatement stmt = this.conexao.getConnection().prepareStatement(sqlQuery);
				ResultSet rs = stmt.executeQuery();

				return rs;
			} catch (SQLException e) {
				throw e;
			}
		}



	public Vector<String> getCamposBDAnalitico() {
			Vector<String> lista = new Vector<String>();
			lista.add("Usuário");
			lista.add("Valor");
			lista.add("Data");

			return lista;
		}
		
	public Vector<String> getCamposBDSintetico() {
			Vector<String> lista = new Vector<String>();
			lista.add("Data");
			lista.add("Valor");

			return lista;
		}

	
		
	public ResultSet listarPorDatasAnalitico(Date dataDe, Date dataAte) throws SQLException {
			
	        String sqlQuery = "SELECT usuario.ds_usuario, financeiro_entrada.nr_valor, financeiro_entrada.dt_lancamento AS data_l " + 
	        		"FROM financeiro_entrada " + 
	        		"INNER JOIN usuario ON usuario.id = financeiro_entrada.fk_usuario " + 
	        		"WHERE financeiro_entrada.dt_lancamento <= ? AND financeiro_entrada.dt_lancamento >= ? " + 
	        		"UNION ALL " + 
	        		"SELECT usuario.ds_usuario, financeiro_saida.nr_valor, financeiro_saida.dt_atual AS data_l " + 
	        		"FROM financeiro_saida " + 
	        		"INNER JOIN usuario ON usuario.id = financeiro_saida.fk_usuario " + 
	        		"WHERE financeiro_saida.dt_atual <= ? AND financeiro_saida.dt_atual >= ? " + 
	        		"ORDER BY data_l ";
	        		

	        try {
				PreparedStatement stmt = this.conexao.getConnection().prepareStatement(sqlQuery);
				stmt.setDate(1, UtilDatas.dateToSQLDate(dataAte));
				stmt.setDate(2, UtilDatas.dateToSQLDate(dataDe));
				stmt.setDate(3, UtilDatas.dateToSQLDate(dataAte));
				stmt.setDate(4, UtilDatas.dateToSQLDate(dataDe));

				
	            ResultSet rs = stmt.executeQuery();

	            return rs;
	        } catch (SQLException e) {
	            throw e;
	        }
	    }
		
	public ResultSet listarPorDatasSintetico(Date dataDe, Date dataAte) throws SQLException {
			
	        String sqlQuery = "SELECT financeiro_entrada.dt_lancamento AS data_l, financeiro_entrada.nr_valor " + 
	        		"FROM financeiro_entrada " +
	        		"WHERE financeiro_entrada.dt_lancamento <= ? AND financeiro_entrada.dt_lancamento >= ? " +
	        		"UNION ALL " + 
	        		"SELECT financeiro_saida.nr_valor, financeiro_saida.dt_atual AS data_l " + 
	        		"FROM financeiro_saida " + 
	        		"WHERE financeiro_saida.dt_atual <= ? AND financeiro_saida.dt_atual >= ? " +  
	        		"ORDER BY data_l";
	        		

	        try {
				PreparedStatement stmt = this.conexao.getConnection().prepareStatement(sqlQuery);
				stmt.setDate(1, UtilDatas.dateToSQLDate(dataAte));
				stmt.setDate(2, UtilDatas.dateToSQLDate(dataDe));
				stmt.setDate(3, UtilDatas.dateToSQLDate(dataAte));
				stmt.setDate(4, UtilDatas.dateToSQLDate(dataDe));

				
	            ResultSet rs = stmt.executeQuery();

	            return rs;
	        } catch (SQLException e) {
	            throw e;
	        }
	    }

		@Override
	public ResultSet listarFiltro(String campo, String busca) throws SQLException, ClassNotFoundException {
			// TODO Auto-generated method stub
			return null;
		}


}
