package hotel.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JOptionPane;

import hotel.telas.setup.CredenciaisBD;

public class UtilCredenciaisBD {
	public static String[] lerArquivoIni() {
		String[] lines = {"", "", ""};
		try {
			FileReader f = new FileReader(CredenciaisBD.NOME_ARQUIVO_INI);
			
			BufferedReader b = new BufferedReader(f);
			
			String line = null;
			int i = 0;
			
			while((line = b.readLine()) != null) {
				lines[i] = line;
				i++;
			}
			
			b.close();
					
			
			
		}catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "Arquivo de configuração não encontrado");
		}catch(IOException e) {
			JOptionPane.showMessageDialog(null, "ERRO de Leitura");
		}
		return lines;	
	}
}
