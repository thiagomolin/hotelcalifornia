package hotel.util;

public class UtilVerificadores {
	
	public static boolean isCpfValido(String cpf) {

		if (cpf.length() == 11) {
			char[] cpfChar = new char[11];
			for (int i = 0; i < cpfChar.length; i++) {
				cpfChar[i] = cpf.charAt(i);
			}

			int j = 10;
			int resultado1 = 0;
			for (int i = 0; i < 9; i++) {
				int numero = Character.getNumericValue(cpfChar[i]);
				resultado1 += numero * j;
				j--;
			}

			int resultado2 = ((resultado1 * 10) % 11 == 10) ? 0 : (resultado1 * 10) % 11;
			if (resultado2 == Character.getNumericValue(cpfChar[9])) {
				int j2 = 11;
				int resultado3 = 0;
				for (int i = 0; i < 10; i++) {
					int numero = Character.getNumericValue(cpfChar[i]);
					resultado3 += numero * j2;
					j2--;
				}

				int resultado4 = ((resultado3 * 10) % 11 == 10) ? 0 : (resultado3 * 10) % 11;

				if (resultado4 == Character.getNumericValue(cpfChar[10])) {
					return true;
				} else {
					return false;
				}

			} else {
				return false;
			}

		} else {
			return false;
		}
	}
	
	public static boolean isCnpjValido(String cnpj){
		if (cnpj.length() == 14) {
			char[] cnpjChar = new char[14];
			for (int i = 0; i < cnpjChar.length; i++) {
				cnpjChar[i] = cnpj.charAt(i);
			}

			int j = 2;
			int resultado1 = 0;
			for (int i = 11; i>=0; i--) {
				int numero = Character.getNumericValue(cnpjChar[i]);
				resultado1 += numero * j;
				j = (j==9)?2:j+1;
				
			}
			int resultado2 = ((resultado1 % 11) == 0 || (resultado1 % 11) == 1) ? 0 : 11-(resultado1 % 11);
			if ((resultado2)== (Character.getNumericValue(cnpjChar[12]))) {
				int j2 = 2;
				int resultado3 = 0;
				for (int i = 12; i>=0; i--) {
					int numero = Character.getNumericValue(cnpjChar[i]);
					resultado3 += numero * j2;
					j2 = (j2==9)?2:j2+1;
				}
				int resultado4 = (resultado3 % 11 == 0 || resultado3 % 11 == 1) ? 0 : 11-(resultado3 % 11);
				if ((resultado4)== (Character.getNumericValue(cnpjChar[13]))) {
					return true;
				} else {
					return false;
				}

			} else {
				return false;
			}

		} else {
			return false;
		}
	}
	
}
