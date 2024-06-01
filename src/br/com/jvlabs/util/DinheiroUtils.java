package br.com.jvlabs.util;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class DinheiroUtils {

	public static BigDecimal obtemValorPorPorcentagem(BigDecimal valor,BigDecimal porcentagem){
		return porcentagem.multiply(valor).divide(new BigDecimal(100));
	}

	public static BigDecimal diminuiEmPorcentagem(BigDecimal valor, BigDecimal porcentagem){
		return valor.subtract(obtemValorPorPorcentagem(valor, porcentagem));
	}

	public static BigDecimal aumentaEmPorcentagem(BigDecimal valor,BigDecimal porcentagem){
		return valor.add(obtemValorPorPorcentagem(valor, porcentagem));
	}

	public static BigDecimal retiraPorcentagem(BigDecimal valor, BigDecimal porcentagem){
		BigDecimal cemPorcento = new BigDecimal(100);
		BigDecimal porcento = cemPorcento.subtract(porcentagem);
		return obtemValorPorPorcentagem(valor, porcento);
	}
	public static BigDecimal obtemPorcentagemDeInteiros(Integer total, Integer valor){
		BigDecimal valorTotal = new BigDecimal(total);
		BigDecimal valorParcial = new BigDecimal(valor);
		BigDecimal resultado = valorParcial.multiply(new BigDecimal(100)).divide(valorTotal,10, RoundingMode.HALF_EVEN);
		return resultado;
	}

	public static BigDecimal trunca(BigDecimal valor, int decimais) {
		return valor.setScale(decimais, BigDecimal.ROUND_HALF_EVEN);
	}

	public static BigDecimal divideIgualmente(BigDecimal valor, Integer parcelas, Integer casasDecimais){
		return valor.divide(new BigDecimal(parcelas),casasDecimais,BigDecimal.ROUND_HALF_EVEN);

	}


	public static BigDecimal[] divide(BigDecimal valor, Integer parcelas, Integer casasDecimais){
		BigDecimal valorParcelado = valor.divide(new BigDecimal(parcelas),new MathContext(casasDecimais, RoundingMode.HALF_EVEN));

		BigDecimal resultadoTotalDeParcelas = valorParcelado.multiply(new BigDecimal(parcelas)).setScale(casasDecimais, RoundingMode.HALF_EVEN);

		BigDecimal[] parcelasRetorno = new BigDecimal[parcelas];

		for (int i=0;i < parcelasRetorno.length ; i++) {
			valorParcelado=valorParcelado.setScale(casasDecimais, RoundingMode.HALF_UP);
			parcelasRetorno[i] = valorParcelado;
		}

		if(!resultadoTotalDeParcelas.equals(valor)){
			BigDecimal diferenca = valor.subtract(resultadoTotalDeParcelas);
			parcelasRetorno[0]=valorParcelado.add(diferenca).setScale(casasDecimais, RoundingMode.HALF_UP);
		}
		return parcelasRetorno;
	}

	public static String formataBigDecimal(BigDecimal valor, int decimais){

		DecimalFormat format = new DecimalFormat();
		if (decimais == 0) {
			format.applyPattern("#,###,##0");
		} else if (decimais == 1) {
			format.applyPattern("#,###,##0.0");
		} else if (decimais == 2) {
			format.applyPattern("#,###,##0.00");
		} else if (decimais == 3) {
			format.applyPattern("#,###,##0.000");
		} else if (decimais == 4) {
			format.applyPattern("#,###,##0.0000");
		} else if (decimais == 5) {
			format.applyPattern("#,###,##0.00000");
		}
		return format.format(valor);
	}

	public static String formataComParametrosParaLocaweb(BigDecimal valor) {
		DecimalFormat dec = new DecimalFormat("#,##0.00");
		DecimalFormatSymbols decDef = new DecimalFormatSymbols();
		decDef.setZeroDigit('0');
		decDef.setDecimalSeparator(',');
		decDef.setMonetaryDecimalSeparator(',');
		decDef.setDigit('#');
		decDef.setGroupingSeparator('.');
		dec.setDecimalFormatSymbols(decDef);
		return dec.format(valor);
	}
	public static String formataComParametrosParaLocawebNovo(BigDecimal valor) {
		DecimalFormat dec = new DecimalFormat("#0.00");
		DecimalFormatSymbols decDef = new DecimalFormatSymbols();
		decDef.setDecimalSeparator('.');
		decDef.setMonetaryDecimalSeparator('.');
		decDef.setZeroDigit('0');
		decDef.setDigit('#');
		dec.setDecimalFormatSymbols(decDef);
		return dec.format(valor);
	}

	public static BigDecimal converteRealEmCentavos(BigDecimal total) {
		return total.multiply(new BigDecimal("100"));
	}

	public static BigDecimal obtemPorcentagem(BigDecimal valorTotal, BigDecimal valorParcial) {
		return new BigDecimal("100").multiply(valorParcial).divide(valorTotal, 2, RoundingMode.DOWN);
	}

	public static BigDecimal validaValor(String value) {

		if(value == null || value.isEmpty())
			return BigDecimal.ZERO;

		try {
			return new BigDecimal(value);
		}catch (Exception e) {
			return BigDecimal.ZERO;
		}

	}




}
