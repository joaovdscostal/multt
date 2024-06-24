package br.com.jvlabs.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

import org.hibernate.annotations.ResultCheckStyle;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import br.com.jvlabs.util.DinheiroUtils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "OFERTA")
@SQLDelete(sql = "UPDATE OFERTA SET excluido = 'T' WHERE id = ?", check = ResultCheckStyle.COUNT)
@Where(clause = "excluido <> 'T'") @Getter @Setter @Builder @AllArgsConstructor @NoArgsConstructor
public class Oferta extends EntidadeNomeAtivo implements Cloneable, EntidadeInterface{
	private static final long serialVersionUID = 6870418303029482722L;
	
	private BigDecimal valor;
	
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
	
	public String pegarValorFormatado() {
		return DinheiroUtils.formataBigDecimal(this.valor, 2);
	}
}
