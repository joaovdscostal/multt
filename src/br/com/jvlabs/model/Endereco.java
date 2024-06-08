package br.com.jvlabs.model;

import java.io.Serializable;

import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
public class Endereco implements Serializable{

	private static final long serialVersionUID = 1L;

	private String rua;

	private String numero;

	private String complemento;

	private String bairro;

	private String cidade;

	private String estado;

	private String cep;
	
}
