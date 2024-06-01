package br.com.jvlabs.model;

import javax.persistence.MappedSuperclass;

import org.hibernate.validator.constraints.NotEmpty;
import br.com.jvlabs.annotation.FieldJvLabs;

@MappedSuperclass
public abstract class EntidadeNomeAtivo extends EntidadeAtivo {

	private static final long serialVersionUID = 1L;

	@FieldJvLabs(required = true, ordem = -2, exibirPesquisa = true, exibirListagem = true)
	@NotEmpty(message = "Por favor, insira um nome!")
	private String nome;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String toString() {
		return nome;
	}

	public String getExibicao() {
		return nome;
	}

}
