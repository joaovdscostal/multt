package br.com.jvlabs.dinamico;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class RetornoSelect {

	private Long codigo;
	private String texto;

	private String textoExibicao;
	private String campoAppend;


}
