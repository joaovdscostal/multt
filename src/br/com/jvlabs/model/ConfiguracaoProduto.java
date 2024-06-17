package br.com.jvlabs.model;

import java.util.List;

import javax.persistence.Embeddable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Type;

import lombok.Getter;
import lombok.Setter;

@Embeddable @Getter @Setter
public class ConfiguracaoProduto {
	
	@ManyToMany
	private List<MetodoDePagamento> metodosDePagamento;
	
	@OneToMany(mappedBy = "produtoReferencia")
	private List<OrdemBump> ordersBump;
	
	private Integer limiteParcela;
	private Integer diasVencimentoBoleto;
	
	@Type(type = "true_false")
	private Boolean habilitar2Cartoes;
	
	@Type(type = "true_false")
	private Boolean coletarIntagramDoComprador;	
	
	@Type(type = "true_false")
	private Boolean paginaDeObrigadoOuUpsell;
	
	private String linkPaginaObrigado;
	
}
