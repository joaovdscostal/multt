package br.com.jvlabs.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;

import org.hibernate.annotations.ResultCheckStyle;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import lombok.Getter;
import lombok.Setter;

@Entity(name = "CHECKOUT")
@SQLDelete(sql = "UPDATE CHECKOUT SET excluido = 'T' WHERE id = ?", check = ResultCheckStyle.COUNT)
@Where(clause = "excluido <> 'T'") @Getter @Setter
public class Checkout extends EntidadeNome implements Cloneable, EntidadeInterface{
	private static final long serialVersionUID = 6870418303029482722L;

	@ManyToMany 
	private List<Oferta> ofertas;
	
	@Override
	public void validarTransient() {}

	@Override
	public Entidade clone() throws CloneNotSupportedException {
		Imagem clube = (Imagem) super.clone();
		clube.removerId();
		clube.setProduto(null);
		return clube;
	}

	public void addOferta(Oferta oferta) {
		if(this.ofertas == null) {
			this.ofertas = new ArrayList<Oferta>();
		}
		
		ofertas.add(oferta);
		
	}
}
