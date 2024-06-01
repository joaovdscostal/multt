package br.com.jvlabs.model;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.OneToOne;

import org.hibernate.annotations.ResultCheckStyle;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity(name="LOG")
@SQLDelete(sql = "UPDATE log SET excluido = 'T' WHERE id = ?", check = ResultCheckStyle.COUNT)
@Where(clause = "excluido <> 'T'")
public class Log extends Entidade{

	private static final long serialVersionUID = 1L;

	private Long idObjeto;

	private String classeObjeto;

	private String titulo;

	@Lob
	private String descricao;

	@OneToOne
	private Usuario usuario;

	public Log() {
	}

	public Log(Usuario usuario, String titulo, String descricao, Entidade objeto) {
		this();
		this.usuario = usuario;
		this.titulo = titulo;
		this.descricao = descricao;
		if(objeto != null) {
			this.idObjeto = objeto.getId();
			this.classeObjeto = objeto.getClass().getSimpleName();
		}
	}

	public Long getIdObjeto() {
		return idObjeto;
	}

	public String getClasseObjeto() {
		return classeObjeto;
	}

	public String getTitulo() {
		return titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}

}
