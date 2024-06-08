package br.com.jvlabs.enumerated;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor @Getter
public enum TipoConta {
	PRODUTOR("Produtor"),
	ALUNO("Aluno");
	
	private String descricao;
}
