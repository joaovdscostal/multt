package br.com.jvlabs.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.OneToOne;

import org.hibernate.annotations.ResultCheckStyle;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.Where;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "ORDEMBUMP")
@SQLDelete(sql = "UPDATE ORDEMBUMP SET excluido = 'T' WHERE id = ?", check = ResultCheckStyle.COUNT)
@Where(clause = "excluido <> 'T'") @Getter @Setter @Builder @AllArgsConstructor @NoArgsConstructor
public class OrdemBump extends Entidade implements Cloneable, EntidadeInterface{

	private static final long serialVersionUID = 6870418303029482722L;
	
	@OneToOne
	private Produto produtoReferencia;
	
	@OneToOne
	private Produto produto;
	
	@OneToOne
	private Oferta oferta;
	
	private String callToAction;
	
	private String titulo;
	
	@Lob
	private String descricao;
	
	@Type(type = "true_false")
	private Boolean exibirImagemDoProduto = Boolean.FALSE;
	
	@Override
	public void validarTransient() {}

	@Override
	public Entidade clone() throws CloneNotSupportedException {
		Produto clube = (Produto) super.clone();
		clube.removerId();
		return clube;
	}

	@Override
	public String getExibicao() {
		// TODO Auto-generated method stub
		return null;
	}

	public Long pegarIdDoProdutoReferencia() {
		
		return produtoReferencia.getId();
	}

	public Serializable pegarIdDoProduto() {
		return produto.getId();
	}
}
