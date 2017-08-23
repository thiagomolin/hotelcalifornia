package hotel.classes.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import hotel.classes.Cliente;

public class ClienteDAO extends DAO {

	public ClienteDAO() throws ClassNotFoundException, SQLException {
		super();

	}

	public void inserir(Cliente cliente) throws SQLException, ClassNotFoundException {
		String sqlQuery = "INSERT INTO cliente(nm_cliente, nr_cpf, nr_telefone, ds_email, ds_endereco, fk_cidade, fk_estado, fK_pais) VALUES (?,?,?,?,?,?,?,?)";

		try {
			PreparedStatement stmt = this.conexao.getConnection().prepareStatement(sqlQuery);
			stmt.setString(1, cliente.getNmCliente());
			stmt.setString(2, cliente.getNrCpf());
			stmt.setString(3, cliente.getNrTelefone());
			stmt.setString(4, cliente.getDsEmail());
			stmt.setString(5, cliente.getDsEndereco());
			stmt.setLong(6, cliente.getFkCidade());
			stmt.setLong(7, cliente.getFkEstado());
			stmt.setLong(8, cliente.getFkPais());

			stmt.executeUpdate();

			this.conexao.commit();
		} catch (SQLException e) {
			this.conexao.rollback();
			throw e;
		}
	}

	public void alterar(Cliente cliente) throws SQLException, ClassNotFoundException {
		String sqlQuery = "UPDATE cliente SET nm_cliente = ?, nr_cpf = ?, nr_telefone = ?, ds_email = ?, ds_endereco = ?, fk_cidade = ?, fk_estado = ?, fK_pais = ? WHERE id = ?";

		try {
			PreparedStatement stmt = this.conexao.getConnection().prepareStatement(sqlQuery);
			stmt.setString(1, cliente.getNmCliente());
			stmt.setString(2, cliente.getNrCpf());
			stmt.setString(3, cliente.getNrTelefone());
			stmt.setString(4, cliente.getDsEmail());
			stmt.setString(5, cliente.getDsEndereco());
			stmt.setLong(6, cliente.getFkCidade());
			stmt.setLong(7, cliente.getFkEstado());
			stmt.setLong(8, cliente.getFkPais());
			stmt.setLong(9, cliente.getId());

			stmt.executeUpdate();

			this.conexao.commit();
		} catch (SQLException e) {
			this.conexao.rollback();
			throw e;
		}
	}

	public void excluir(Cliente cliente) throws SQLException, ClassNotFoundException {
		String sqlQuery = "DELETE FROM cliente WHERE id = ?";

		try {
			PreparedStatement stmt = this.conexao.getConnection().prepareStatement(sqlQuery);
			stmt.setLong(1, cliente.getId());

			stmt.executeUpdate();

			this.conexao.commit();
		} catch (SQLException e) {
			this.conexao.rollback();
			throw e;
		}
	}

	public Cliente selecionar(long id) throws SQLException, ClassNotFoundException {
		String sqlQuery = "SELECT * FROM cliente WHERE id = ?";

		try {
			PreparedStatement stmt = this.conexao.getConnection().prepareStatement(sqlQuery);
			stmt.setLong(1, id);
			ResultSet rs = stmt.executeQuery();
			Cliente parser = null;
			if(rs.next()) {
		        parser = parser(rs);
			}
			return parser;
			
		} catch (SQLException e) {
			this.conexao.rollback();
			throw e;
		}
	}

	public ResultSet listar() throws SQLException, ClassNotFoundException {
		String sqlQuery = "SELECT cliente.id, `nm_cliente`, `nr_cpf`, `nr_telefone`, `ds_endereco`, `ds_email`, cidade.ds_cidade, estado.ds_estado, pais.ds_pais "
				+ "FROM cliente "
				+ "INNER JOIN pais ON pais.id = cliente.fk_pais "
				+ "INNER JOIN estado ON estado.id = cliente.fk_estado "
				+ "INNER JOIN cidade ON cidade.id = cliente.fk_cidade";
		try {
			PreparedStatement stmt = this.conexao.getConnection().prepareStatement(sqlQuery);
			ResultSet rs = stmt.executeQuery();
			return rs;
		} catch (SQLException e) {
			throw e;
		}
	}	

	public ResultSet listarFiltro(String campo, String busca) throws SQLException, ClassNotFoundException {
		if (campo == Cliente.getCampos().get(0)) {
			String sqlQuery = "SELECT cliente.id, `nm_cliente`, `nr_cpf`, `nr_telefone`, `ds_endereco`, `ds_email`, cidade.ds_cidade, estado.ds_estado, pais.ds_pais "
					+ "FROM cliente " 
					+ "INNER JOIN pais ON pais.id = cliente.fk_pais "
					+ "INNER JOIN estado ON estado.id = cliente.fk_estado "
					+ "INNER JOIN cidade ON cidade.id = cliente.fk_cidade "
					+ "WHERE nm_cliente LIKE ?";
			try {
				PreparedStatement stmt = this.conexao.getConnection().prepareStatement(sqlQuery);
				stmt.setString(1, "%" + busca + "%");
				ResultSet rs = stmt.executeQuery();
				return rs;
			} catch (SQLException e) {
				throw e;
			} 
		}else{
			String sqlQuery = "SELECT cliente.id, `nm_cliente`, `nr_cpf`, `nr_telefone`, `ds_endereco`, `ds_email`, cidade.ds_cidade, estado.ds_estado, pais.ds_pais "
					+ "FROM cliente " 
					+ "INNER JOIN pais ON pais.id = cliente.fk_pais "
					+ "INNER JOIN estado ON estado.id = cliente.fk_estado "
					+ "INNER JOIN cidade ON cidade.id = cliente.fk_cidade " 
					+ "WHERE nr_cpf LIKE ?";
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
	
	public List<Cliente> getLista() throws SQLException, ClassNotFoundException {
		String sqlQuery = "SELECT * FROM cliente ORDER BY id";

		try {
			PreparedStatement stmt = this.conexao.getConnection().prepareStatement(sqlQuery);
			ResultSet rs = stmt.executeQuery();

			List<Cliente> cliente = new ArrayList<>();

			while (rs.next()) {
				cliente.add(parser(rs));
			}

			return cliente;
		} catch (SQLException e) {
			throw e;
		}
	}
	
	public Vector<String> getCamposBDAnalitico(){
		Vector<String> lista = new Vector<String>();
		lista.add("ID");
		lista.add("Nome");
		lista.add("CPF");
		lista.add("Telefone");
		lista.add("Email");
		lista.add("Endereço");
		lista.add("Cidade");
		lista.add("Estado");
		lista.add("País");
		
		return lista;
	}

	private Cliente parser(ResultSet resultSet) throws SQLException {
		Cliente c = new Cliente(resultSet.getLong("id"), resultSet.getString("nm_cliente"),
				resultSet.getString("nr_cpf"), resultSet.getString("nr_telefone"), resultSet.getString("ds_email"),
				resultSet.getString("ds_endereco"), resultSet.getLong("fk_cidade"), resultSet.getLong("fk_estado"),
				resultSet.getLong("fk_pais"));
		return c;
	}


}
