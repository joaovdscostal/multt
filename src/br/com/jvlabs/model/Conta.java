package br.com.jvlabs.model;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToOne;

import org.hibernate.annotations.ResultCheckStyle;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import br.com.jvlabs.enumerated.TipoConta;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "CONTA")
@SQLDelete(sql = "UPDATE CONTA SET excluido = 'T' WHERE id = ?", check = ResultCheckStyle.COUNT)
@Where(clause = "excluido <> 'T'") @Getter @Setter
public class Conta extends EntidadeNome implements Cloneable, EntidadeInterface{

	private static final long serialVersionUID = 6870418303029482722L;
	
	private String email;
	
	private String celular;
	
	private String imagem;
	
	@OneToOne
	private Usuario usuario;
	
	@Embedded
	private Endereco endereco;
	
	@Enumerated(EnumType.STRING)
	private TipoConta tipoConta;
	
	@Override
	public void validarTransient() {}

	@Override
	public Entidade clone() throws CloneNotSupportedException {
		Conta curso = (Conta) super.clone();
		curso.removerId();
		return curso;
	}



}

