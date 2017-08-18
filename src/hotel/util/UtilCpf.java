package hotel.util;

public class UtilCpf {
	
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
	
}
