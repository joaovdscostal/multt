package br.com.jvlabs.model;

import javax.persistence.Entity;

import org.hibernate.annotations.ResultCheckStyle;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import lombok.Getter;
import lombok.Setter;

@Entity(name = "CONFIGURACAO")
@SQLDelete(sql = "UPDATE CONFIGURACAO SET excluido = 'T' WHERE id = ?", check = ResultCheckStyle.COUNT)
@Where(clause = "excluido <> 'T'") @Getter @Setter
public class Configuracao extends Entidade {

	private static final long serialVersionUID = 1L;

	private String nome;

}
