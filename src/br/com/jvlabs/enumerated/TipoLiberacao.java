package br.com.jvlabs.enumerated;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor @Getter
public enum TipoLiberacao {
	IMEDIATA("Imediata"),
	POR_DATA("Por Data"),
	POR_DIAS_APOS_COMPRA("Pos Dias Após Compra");
	
	private String descricao;
}
