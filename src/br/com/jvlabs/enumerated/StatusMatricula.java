package br.com.jvlabs.enumerated;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor @Getter
public enum StatusMatricula {
	
	PENDENTE("Pendente"), 
	CANCELADO("Cancelado"),
	CONCLUIDO("Concluído");

	private String descricao;

}
