package hotel.telas.cadastro;

import javax.swing.JInternalFrame;

import hotel.telas.consulta.ETipo;

public abstract class Tela extends JInternalFrame   {
	private static final long serialVersionUID = 1L;
	public abstract void setConsulta(Long id, ETipo tipo);
}
