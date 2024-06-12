package br.com.jvlabs.model;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.ResultCheckStyle;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import br.com.jvlabs.enumerated.TipoDeProduto;
import br.com.jvlabs.enumerated.TipoPagamentoProduto;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "PRODUTO")
@SQLDelete(sql = "UPDATE PRODUTO SET excluido = 'T' WHERE id = ?", check = ResultCheckStyle.COUNT)
@Where(clause = "excluido <> 'T'") @Getter @Setter
public class Produto extends EntidadeNomeAtivo implements Cloneable, EntidadeInterface{

	private static final long serialVersionUID = 6870418303029482722L;

	private BigDecimal valor;

	@Lob
	private String descricao;
	
	private String emailDeSuporte;
	
	private String paginaDeVendasExterna;
	
	@Enumerated(EnumType.STRING)
	private TipoDeProduto tipo;
	
	@Enumerated(EnumType.STRING)
	private TipoPagamentoProduto tipoPagamento;
	
	@OneToMany
	private List<Imagem> imagens;
	
	@OneToMany
	private List<Oferta> oferta;
	
	@OneToOne
	private Conta conta;
	
	@OneToOne
	private CategoriaProduto categoria;

	@Embedded
	private ConfiguracaoProduto configuracao;
	
	
	@Override
	public void validarTransient() {}

	@Override
	public Entidade clone() throws CloneNotSupportedException {
		Produto clube = (Produto) super.clone();
		clube.removerId();
		return clube;
	}

	public boolean temContaVinculada() {
		return this.conta != null;
	}





}
