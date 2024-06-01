package br.com.jvlabs.model;

import javax.persistence.Entity;
import javax.persistence.Lob;

import org.hibernate.annotations.ResultCheckStyle;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import br.com.jvlabs.util.FormatterString;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "UNIDADE")
@SQLDelete(sql = "UPDATE UNIDADE SET excluido = 'T' WHERE id = ?", check = ResultCheckStyle.COUNT)
@Where(clause = "excluido <> 'T'") @Getter @Setter
public class Unidade extends EntidadeNomeAtivo {

	private static final long serialVersionUID = 1L;

	@Lob
	private String endereco;

	private String urlCurta;

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public void gerarUrlCurta() {
		this.urlCurta = new FormatterString().generateNamedUrl(super.getNome());
	}

}