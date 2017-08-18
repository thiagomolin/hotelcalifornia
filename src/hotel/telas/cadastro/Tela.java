package hotel.telas.cadastro;

import javax.swing.JInternalFrame;

import hotel.telas.consulta.ETipos;

public abstract class Tela extends JInternalFrame   {
	private static final long serialVersionUID = 1L;
	public abstract void setConsulta(Long id, ETipos tipo);
}
