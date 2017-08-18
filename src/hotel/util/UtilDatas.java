package hotel.util;

import java.time.LocalDate;
import java.time.ZoneId;

public class UtilDatas {
	
	public static LocalDate dateToLocalDate(java.util.Date date) {
		return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();		
	}
	
	public static java.sql.Date dateToSQLDate(java.util.Date date){		
		return new java.sql.Date(date.getTime());
	}
	
}
