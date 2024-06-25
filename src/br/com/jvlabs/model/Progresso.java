package br.com.jvlabs.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.ResultCheckStyle;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.Where;

import lombok.Getter;
import lombok.Setter;

@Entity(name = "PROGRESSO")
@SQLDelete(sql = "UPDATE PROGRESSO SET excluido = 'T' WHERE id = ?", check = ResultCheckStyle.COUNT)
@Where(clause = "excluido <> 'T'") @Getter @Setter
public class Progresso extends Entidade implements Cloneable, EntidadeInterface{

	private static final long serialVersionUID = 6870418303029482722L;

	@OneToOne
	private Matricula aluno;
	
	@OneToOne
	private Conteudo conteudo;
	
	@Type(type = "true_false")
	private Boolean assistido = Boolean.FALSE;
	
	@OneToMany
	private List<Oferta> ofertas;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataAbertura;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataConclusao;

	@Override
	public void validarTransient() {}

	@Override
	public Entidade clone() throws CloneNotSupportedException {
		Progresso progresso = (Progresso) super.clone();
		progresso.removerId();
		return progresso;
	}

	@Override
	public String getExibicao() {
		// TODO Auto-generated method stub
		return null;
	}
}
