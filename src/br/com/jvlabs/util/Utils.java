package br.com.jvlabs.util;

import java.math.BigDecimal;
import java.security.SecureRandom;

public class Utils {

	public static boolean validaZeroPara(BigDecimal... tamanhos) {
		for (BigDecimal bigDecimal : tamanhos) {
			if(bigDecimal.equals(BigDecimal.ZERO))
				return false;
		}


		return true;
	}

	public static String gerarCodigoAleatorio(Integer tamanho) {
		 final String caracteres = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
	     final SecureRandom random = new SecureRandom();
		
		 StringBuilder code = new StringBuilder(tamanho);
	        for (int i = 0; i < tamanho; i++) {
	            int index = random.nextInt(caracteres.length());
	            code.append(caracteres.charAt(index));
	        }
	        return code.toString();
	}

}
