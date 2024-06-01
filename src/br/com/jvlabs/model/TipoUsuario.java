package br.com.jvlabs.model;

public enum TipoUsuario {

	ADMINISTRADOR("Administrador"),
	PROJETO("Projeto");

	private String exibicao;

	TipoUsuario(String exibicao) {
		this.exibicao = exibicao;
	}


	public String getExibicao() {
		return exibicao;
	}

	public boolean isAdministrador() {
		return this.equals(TipoUsuario.ADMINISTRADOR);
	}

}
