package hotel.util;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;

public class UtilVector {
	public static Vector<Vector<Object>> rsParaVector(ResultSet rs){
		Vector<Vector<Object>> dados = new Vector<Vector<Object>>();
		
		try {
			ResultSetMetaData metaData = rs.getMetaData();
			while (rs.next()) {
				Vector<Object> vector = new Vector<Object>();
				for (int i = 1; i <= metaData.getColumnCount(); i++) {
					vector.add(rs.getObject(i));
				}
				dados.add(vector);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return dados;
		
	}
}
