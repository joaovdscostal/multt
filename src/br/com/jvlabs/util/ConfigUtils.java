package br.com.jvlabs.util;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;


public class ConfigUtils {

	private static final String[] UF = { "AC", "AL", "AM", "AP", "BA", "CE",
		"DF", "ES", "GO", "MA", "MG", "MS", "MT", "PA", "PB", "PE", "PI",
		"PR", "RJ", "RN", "RO", "RR", "RS", "SC", "SE", "SP", "TO" };
	private static final String[] MESES = {"01", "02", "03", "04", "05", "06",
		"07", "08", "09", "10", "11", "12"};

	public static List<?> getUFArrayList() {
		return Arrays.asList(UF);
	}
	public static List<?> getMesesArrayList() {
		return Arrays.asList(MESES);
	}

	public static List<?> getAnosArrayList(){
		List<String> anos = new ArrayList<String>();
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		for(int i = 1; i<=20 ; i++){
			anos.add(String.valueOf(year));
			year ++;
		}
		return anos;
	}


	public static String capturaIp() {
		InetAddress i = null;
		try {
			i = InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			return "Ip n&atilde;o encontrado";
		}
		String ip = i.getHostAddress();
		return ip;
	}
	public static Integer[] gerarNumeros() {
		int quantidadeDeNumeros = 100;
		Integer[] retorno = new Integer[quantidadeDeNumeros];
		for(int i = 0; i< 100 ; i++)
			retorno[i] = i;

		return retorno;
	}
	public static List<String> getFontsList() {
		List<String> list = new ArrayList<String>();
		list.add("Arial");
		list.add("Georgia");
		list.add("Palatino Linotype");
		list.add("Book Antiqua");
		list.add("Times New Roman");
		list.add("Helvetica");
		list.add("Arial Black");
		list.add("Impact");
		list.add("Lucida Sans Unicode");
		list.add("Tahoma");
		list.add("Verdana");
		list.add("Courier New");
		list.add("Lucida Console");
		list.add("initial");
		list.add("Gudea");
		list.add("Oswald");
		list.add("sans-serif");
		list.add("'PT Sans'");
		list.add("Georgia, 'Times New Roman', serif");
		return list;
	}
	public static List<String> getBancos() {
		List<String> list = new ArrayList<String>();
		list.add("bradesco");
		list.add("itau");
		list.add("bancodobrasil");
		return list;
	}

	public static List<Object> getListaEmbaralhada(List<? extends Object> lista, Integer quantidade){
		List<Object> listaEmbaralhada = new ArrayList<Object>();
		listaEmbaralhada.addAll(lista);

		Collections.shuffle(listaEmbaralhada);
		int max=quantidade;
		if(quantidade > listaEmbaralhada.size())
			max=listaEmbaralhada.size();

		return (List<Object>) listaEmbaralhada.subList(0, max);
	}


}