package br.com.jvlabs.model;

public enum TipoUsuario {

	ADMINISTRADOR("Administrador"),
	USUARIO("Usuário");

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
	
	public boolean isUsuario() {
		return this.equals(TipoUsuario.USUARIO);
	}

}
