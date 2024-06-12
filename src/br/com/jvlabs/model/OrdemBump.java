package br.com.jvlabs.model;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.OneToOne;

import org.hibernate.annotations.ResultCheckStyle;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.Where;

import lombok.Getter;
import lombok.Setter;

@Entity(name = "ORDEMBUMP")
@SQLDelete(sql = "UPDATE ORDEMBUMP SET excluido = 'T' WHERE id = ?", check = ResultCheckStyle.COUNT)
@Where(clause = "excluido <> 'T'") @Getter @Setter
public class OrdemBump extends Entidade implements Cloneable, EntidadeInterface{

	private static final long serialVersionUID = 6870418303029482722L;
	
	@OneToOne
	private Produto produto;
	
	@OneToOne
	private Oferta oferta;
	
	private String callToAction;
	
	private String titulo;
	
	@Lob
	private String descricao;
	
	@Type(type = "true_false")
	private Boolean exibirImagemDoProduto;
	
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
}
