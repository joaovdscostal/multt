package br.com.jvlabs.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.ResultCheckStyle;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import br.com.jvlabs.enumerated.StatusMatricula;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "HISTORICOMATRICULA")
@SQLDelete(sql = "UPDATE HISTORICOMATRICULA SET excluido = 'T' WHERE id = ?", check = ResultCheckStyle.COUNT)
@Where(clause = "excluido <> 'T'") @Getter @Setter
public class HistoricoMatricula extends Entidade implements Cloneable, EntidadeInterface{

	private static final long serialVersionUID = 6870418303029482722L;

	@OneToOne
	private Matricula matricula;
	
	@Enumerated(EnumType.STRING)
	private StatusMatricula statusMatricula;
		
	@Temporal(TemporalType.TIMESTAMP)
	private Date data;
	
	@Lob
	private String observacao;

	@Override
	public void validarTransient() {}

	@Override
	public Entidade clone() throws CloneNotSupportedException {		
		HistoricoMatricula historicoMatricula = (HistoricoMatricula) super.clone();
		historicoMatricula.removerId();
		return historicoMatricula;
	}

	@Override
	public String getExibicao() {
		// TODO Auto-generated method stub
		return null;
	}
}
