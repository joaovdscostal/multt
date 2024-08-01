package br.com.jvlabs.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import org.hibernate.annotations.ResultCheckStyle;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.Where;

import lombok.Getter;
import lombok.Setter;

@Entity(name = "MODULO")
@SQLDelete(sql = "UPDATE MODULO SET excluido = 'T' WHERE id = ?", check = ResultCheckStyle.COUNT)
@Where(clause = "excluido <> 'T'") @Getter @Setter
public class Modulo extends EntidadeNomeAtivo implements Cloneable, EntidadeInterface{

	private static final long serialVersionUID = 6870418303029482722L;
	
	private Integer ordem;

	@OneToOne
	private Produto produto;
	
	@OneToMany	
	private List<Conteudo> conteudos;
	
	@ManyToMany
	@JoinTable(name = "MODULO_TURMA",
	joinColumns = @JoinColumn(name = "modulo_id"),
	inverseJoinColumns = @JoinColumn(name = "turma_id"))
	private List<Turma> turmas;
	
	@Type(type = "true_false")
	private Boolean permitirTodasAsTurmas = Boolean.FALSE;

	@Override
	public void validarTransient() {}

	@Override
	public Entidade clone() throws CloneNotSupportedException {
		Modulo modulo = (Modulo) super.clone();
		modulo.removerId();
		return modulo;
	}

	public void addConteudo(Conteudo conteudo) {
		if(this.conteudos == null) {
			this.conteudos = new ArrayList<>();
		}
		
		this.conteudos.add(conteudo);
		
	}

	public boolean possuiProduto() {
		return this.produto != null;
	}

	public boolean possuiTurmas() {
		return this.turmas != null && !this.turmas.isEmpty();
	}
}
