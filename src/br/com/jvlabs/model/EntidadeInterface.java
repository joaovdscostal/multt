package br.com.jvlabs.model;

public interface EntidadeInterface {
	public void validarTransient();
	public String toString();
	public Entidade clone() throws CloneNotSupportedException;
	public String getExibicao();
}
