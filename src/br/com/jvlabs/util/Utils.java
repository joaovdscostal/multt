package br.com.jvlabs.util;

import java.math.BigDecimal;

public class Utils {

	public static boolean validaZeroPara(BigDecimal... tamanhos) {
		for (BigDecimal bigDecimal : tamanhos) {
			if(bigDecimal.equals(BigDecimal.ZERO))
				return false;
		}


		return true;
	}

}
