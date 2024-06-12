package br.com.jvlabs.model;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Transient;

import org.hibernate.annotations.ResultCheckStyle;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import br.com.jvlabs.enumerated.TipoFormaPagamento;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "METODODEPAGAMENTO")
@SQLDelete(sql = "UPDATE METODODEPAGAMENTO SET excluido = 'T' WHERE id = ?", check = ResultCheckStyle.COUNT)
@Where(clause = "excluido <> 'T'") @Getter @Setter
public class MetodoDePagamento extends EntidadeNomeAtivo implements Cloneable, EntidadeInterface {
	
	private static final long serialVersionUID = 6870418303029482722L;

	@Enumerated(EnumType.STRING)
	private TipoFormaPagamento portador;

	private Integer maximoDeParcelas;

	private BigDecimal taxaApi;

	private BigDecimal desconto;

	@Transient
	private List<CondicaoDePagamento> condicoes;

	@Override
	public void validarTransient() {}

	public Entidade clone() throws CloneNotSupportedException {
		MetodoDePagamento clube = (MetodoDePagamento) super.clone();
		clube.removerId();
		return clube;
	}

	public boolean isCartaoDeCredito() {
		return portador.equals(TipoFormaPagamento.CARTAO_CREDITO);
	}

	public boolean temDesconto() {
		return this.desconto != null && this.desconto.compareTo(BigDecimal.ZERO) > 0;
	}

	public boolean isPix() {
		return portador.equals(TipoFormaPagamento.PIX);
	}

	public boolean isBoleto() {
		return portador.equals(TipoFormaPagamento.BOLETO);
	}
}
