package hotel.util;

import java.sql.SQLException;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

import hotel.classes.Cidade;
import hotel.classes.Estado;
import hotel.classes.Pais;
import hotel.classes.TipoDeQuarto;
import hotel.classes.DAO.CidadeDAO;
import hotel.classes.DAO.EstadoDAO;
import hotel.classes.DAO.PaisDAO;
import hotel.classes.DAO.TipoDeQuartoDAO;

public class UtilCombobox {

	public static Object inicializarComboBoxPais(JComboBox<Object> comboBox) {

		try {
			PaisDAO p = new PaisDAO();
			List<Pais> paises = p.getLista();
			comboBox.setModel(new DefaultComboBoxModel<Object>(paises.toArray()));

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return comboBox;
	}

	public static Object inicializarComboBoxEstado(JComboBox<Object> comboBox, long idPais) {

		try {
			EstadoDAO e = new EstadoDAO();
			List<Estado> estados = e.listarPorPais(idPais);
			comboBox.setModel(new DefaultComboBoxModel<Object>(estados.toArray()));

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return comboBox;
	}

	public static void inicializarComboBoxCidade(JComboBox<Object> comboBox, java.lang.Long idEstado) {
		if (idEstado != null) {
			try {
				CidadeDAO c = new CidadeDAO();
				List<Cidade> cidades = c.listarPorEstado(idEstado);
				comboBox.setModel(new DefaultComboBoxModel<Object>(cidades.toArray()));

			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			return;
		}

	}

	public static void inicializarComboBoxTipoQuarto(JComboBox<Object> comboBox) {
		try {
			TipoDeQuartoDAO tdq = new TipoDeQuartoDAO();
			List<TipoDeQuarto> tipoDeQuarto = tdq.getLista();
			comboBox.setModel(new DefaultComboBoxModel<Object>(tipoDeQuarto.toArray()));
		} catch (ClassNotFoundException | SQLException ex) {
			ex.printStackTrace();
			ex.printStackTrace();
		}
	}
}
