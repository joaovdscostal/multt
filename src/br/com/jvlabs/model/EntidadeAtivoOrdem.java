package br.com.jvlabs.model;

import javax.persistence.MappedSuperclass;

import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Getter @Setter
public abstract class EntidadeAtivoOrdem extends EntidadeAtivo {

	private static final long serialVersionUID = 1L;

	private Integer ordem;

}
