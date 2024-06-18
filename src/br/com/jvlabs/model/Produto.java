package br.com.jvlabs.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import org.hibernate.annotations.ResultCheckStyle;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import br.com.jvlabs.dto.ImagemJsonDTO;
import br.com.jvlabs.enumerated.TipoDeProduto;
import br.com.jvlabs.enumerated.TipoPagamentoProduto;
import br.com.jvlabs.util.GsonUtils;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "PRODUTO")
@SQLDelete(sql = "UPDATE PRODUTO SET excluido = 'T' WHERE id = ?", check = ResultCheckStyle.COUNT)
@Where(clause = "excluido <> 'T'") @Getter @Setter
public class Produto extends EntidadeNomeAtivo implements Cloneable, EntidadeInterface{

	private static final long serialVersionUID = 6870418303029482722L;
	
	@Transient
	private BigDecimal valor;

	@Lob
	private String descricao;
	
	private String emailDeSuporte;
	
	private String paginaDeVendasExterna;
	
	@Enumerated(EnumType.STRING)
	private TipoDeProduto tipo;
	
	@Enumerated(EnumType.STRING)
	private TipoPagamentoProduto tipoPagamento;
	
	@OneToMany(mappedBy="produto")
	private List<Imagem> imagens;
	
	@OneToMany(mappedBy="produto")
	private List<Oferta> ofertas;
	
	@OneToOne
	private Conta conta;
	
	@OneToOne
	private CategoriaProduto categoria;

	@Embedded
	private ConfiguracaoProduto configuracao;
	
	@Override
	public void validarTransient() {
		if(this.categoria != null && this.categoria.getId() == null) {
			this.categoria = null;
		}
	}

	@Override
	public Entidade clone() throws CloneNotSupportedException {
		Produto clube = (Produto) super.clone();
		clube.removerId();
		return clube;
	}

	public boolean temContaVinculada() {
		return this.conta != null;
	}

	public void limparListas() {
		this.ofertas = null;
		
	}

	public void addOferta(Oferta ofertaPadrao) {
		if(this.ofertas == null) {
			this.ofertas = new ArrayList<Oferta>();
		}
		
		this.ofertas.add(ofertaPadrao);
	}

	public void validarImagens() {
		if(this.imagens == null) {
			this.imagens = new ArrayList<Imagem>();
		}
		
	}
	
	public String pegarPrimeiraImagem() {
		
		if(this.imagens != null && !this.imagens.isEmpty()) {
			return this.imagens.get(0).getNomeImagem();
		}
		
		return null; 
	}

	public String getImagensJson(String url) {
		List<ImagemJsonDTO> listImagem = new ArrayList<>();

		if(imagens != null) {
			for(Imagem imagem : imagens) {
				listImagem.add(ImagemJsonDTO.builder()
											.id(imagem.getId())
											.src(url + "arquivos/imagens/" + imagem.getNomeImagem())
											.build());
			}
		}

		try {
			return new GsonUtils().padrao().toJson(listImagem);
		}catch (Exception e) {
			return "";
		}
	}



}
