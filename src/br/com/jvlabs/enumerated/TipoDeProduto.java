package br.com.jvlabs.enumerated;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor @Getter
public enum TipoDeProduto {
	MENTORIA("Mentoria"),
	CURSO("Curso"),
	EVENTO_ONLINE("Evento Online"),
	EVENTO_PRESENCIAL("Evento Presencial");

	private String descricao;
}
