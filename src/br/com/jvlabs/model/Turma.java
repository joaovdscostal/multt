package br.com.jvlabs.model;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import org.hibernate.annotations.ResultCheckStyle;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import lombok.Getter;
import lombok.Setter;

@Entity(name = "TURMA")
@SQLDelete(sql = "UPDATE TURMA SET excluido = 'T' WHERE id = ?", check = ResultCheckStyle.COUNT)
@Where(clause = "excluido <> 'T'") @Getter @Setter
public class Turma extends Entidade implements Cloneable, EntidadeInterface{

	private static final long serialVersionUID = 6870418303029482722L;

	private BigDecimal valor;
	
	@OneToMany
	private List<Oferta> ofertas;

	@Override
	public void validarTransient() {}

	@Override
	public Entidade clone() throws CloneNotSupportedException {
		Turma turma = (Turma) super.clone();
		turma.removerId();
		return turma;
	}

	@Override
	public String getExibicao() {
		// TODO Auto-generated method stub
		return null;
	}
}
