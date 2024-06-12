package br.com.jvlabs.model;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

import org.hibernate.annotations.ResultCheckStyle;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import lombok.Getter;
import lombok.Setter;

@Entity(name = "IMAGEM")
@SQLDelete(sql = "UPDATE IMAGEM SET excluido = 'T' WHERE id = ?", check = ResultCheckStyle.COUNT)
@Where(clause = "excluido <> 'T'") @Getter @Setter
public class Imagem extends Entidade implements Cloneable, EntidadeInterface{

	private static final long serialVersionUID = 6870418303029482722L;

	private String nomeImagem;

	@OneToOne
	private Produto produto;

	@Override
	public void validarTransient() {}

	@Override
	public Entidade clone() throws CloneNotSupportedException {
		Imagem clube = (Imagem) super.clone();
		clube.removerId();
		clube.setProduto(null);
		return clube;
	}

	public static Imagem montar(Long idParaExcluir) {
		Imagem imagem = new Imagem();
		imagem.setId(idParaExcluir);

		return imagem;
	}

	@Override
	public String getExibicao() {
		// TODO Auto-generated method stub
		return null;
	}

}
