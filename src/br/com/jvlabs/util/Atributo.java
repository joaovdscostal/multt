package br.com.jvlabs.util;

import org.hibernate.criterion.MatchMode;

public class Atributo {

	private String parametro;
	private Object valor;
	private MatchMode tipoPesquisa;

	public Atributo(String parametro, Object valor, MatchMode tipoPesquisa) {
		super();
		this.parametro = parametro;
		this.valor = valor;
		this.tipoPesquisa = tipoPesquisa;
	}

	public Atributo(String parametro, Object valor) {
		this.parametro = parametro;
		this.valor = valor;
		this.tipoPesquisa = MatchMode.EXACT;
	}

	public String getParametro() {
		return parametro;
	}

	public void setParametro(String parametro) {
		this.parametro = parametro;
	}

	public Object getValor() {
		return valor;
	}

	public void setValor(Object valor) {
		this.valor = valor;
	}

	public MatchMode getTipoPesquisa() {
		return tipoPesquisa;
	}

	public void setTipoPesquisa(MatchMode tipoPesquisa) {
		this.tipoPesquisa = tipoPesquisa;
	}

	public boolean isString() {
		return valor instanceof String;
	}





}
