package br.com.jvlabs.model;

import javax.persistence.MappedSuperclass;

import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Getter @Setter
public abstract class EntidadeNomeOrdem extends EntidadeNome{

	private static final long serialVersionUID = 1L;

	private Integer ordem;

}
