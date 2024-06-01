package br.com.jvlabs.model;

import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.Type;

@MappedSuperclass
public abstract class EntidadeAtivo extends Entidade {

	private static final long serialVersionUID = 1L;

	@Type(type = "true_false")
	private Boolean ativo = Boolean.FALSE;

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

}
