package br.com.jvlabs.dinamico;

import java.math.BigDecimal;

import br.com.jvlabs.util.DateUtils;

public class Dinamico {
	private String nome;
	private String valor;
	private String tipo;

	public Dinamico() {
	}

	public Dinamico(String string, String valor, String tipo) {
		this.nome = string;
		this.valor = valor;
		this.tipo = tipo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getValor() {
		return valor;
	}

	public Object getValorValidandoTipo() {
		if(tipo.equals("text"))
			return valor;
		else if(tipo.equals("bigDecimal"))
			return new BigDecimal(valor);
		else if(tipo.equals("integer"))
			return new Integer(valor);
		else if(tipo.equals("date"))
			return DateUtils.stringToDate(valor);
		else
			return valor.equals("on") ? true : false;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public String getNomeSet() {
		String nomeOneUp = capitalize(nome);
		return "set" + nomeOneUp;
	}

	public static String capitalize(String str) {
	    if(str == null || str.isEmpty()) {
	        return str;
	    }

	    return str.substring(0, 1).toUpperCase() + str.substring(1);
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public boolean isTexto() {
		return this.tipo.equalsIgnoreCase("text");
	}

	public boolean temValor() {
		return this.valor != null && !this.valor.trim().isEmpty();
	}

}
