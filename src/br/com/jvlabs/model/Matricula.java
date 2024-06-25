package br.com.jvlabs.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.ResultCheckStyle;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import br.com.jvlabs.enumerated.StatusMatricula;
import br.com.jvlabs.enumerated.TipoPagamentoProduto;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "MATRICULA")
@SQLDelete(sql = "UPDATE MATRICULA SET excluido = 'T' WHERE id = ?", check = ResultCheckStyle.COUNT)
@Where(clause = "excluido <> 'T'") @Getter @Setter
public class Matricula extends Entidade implements Cloneable, EntidadeInterface{

	private static final long serialVersionUID = 6870418303029482722L;

	@OneToOne
	private Conta aluno;
	
	@OneToOne
	private Turma turma;
	
	@Enumerated(EnumType.STRING)
	private StatusMatricula statusMatricula;
	
	@Enumerated(EnumType.STRING)
	private TipoPagamentoProduto tipoPagamentoProduto;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date data;

	@Override
	public void validarTransient() {}

	@Override
	public Entidade clone() throws CloneNotSupportedException {
		Matricula matricula = (Matricula) super.clone();
		matricula.removerId();
		return matricula;
	}

	@Override
	public String getExibicao() {
		// TODO Auto-generated method stub
		return null;
	}
}
