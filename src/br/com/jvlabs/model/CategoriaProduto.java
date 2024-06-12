package br.com.jvlabs.model;

import javax.persistence.Entity;

import org.hibernate.annotations.ResultCheckStyle;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import lombok.Getter;
import lombok.Setter;

@Entity(name = "CATEGORIAPRODUTO")
@SQLDelete(sql = "UPDATE CATEGORIAPRODUTO SET excluido = 'T' WHERE id = ?", check = ResultCheckStyle.COUNT)
@Where(clause = "excluido <> 'T'") @Getter @Setter
public class CategoriaProduto extends EntidadeNomeAtivo implements Cloneable, EntidadeInterface {
	
	private static final long serialVersionUID = 6870418303029482722L;
	
	@Override
	public void validarTransient() {}

	@Override
	public Entidade clone() throws CloneNotSupportedException {
		CategoriaProduto clube = (CategoriaProduto) super.clone();
		clube.removerId();
		return clube;
	}
}
