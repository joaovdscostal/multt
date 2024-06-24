package br.com.jvlabs.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Transient;

import org.hibernate.annotations.ResultCheckStyle;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import lombok.Getter;
import lombok.Setter;

@Entity(name = "CONDICAODEPAGAMENTO")
@SQLDelete(sql = "UPDATE CONDICAODEPAGAMENTO SET excluido = 'T' WHERE id = ?", check = ResultCheckStyle.COUNT)
@Where(clause = "excluido <> 'T'") @Getter @Setter
public class CondicaoDePagamento extends EntidadeNomeAtivo implements Cloneable, EntidadeInterface{

	private static final long serialVersionUID = 6870418303029482722L;

	private Integer quantidadeDeParcelas;

	private BigDecimal jurosAoMes;

	@Transient
	private BigDecimal valor;

	@Override
	public void validarTransient() {}

	@Override
	public Entidade clone() throws CloneNotSupportedException {
		CondicaoDePagamento clube = (CondicaoDePagamento) super.clone();
		clube.removerId();
		return clube;

	}

	public boolean temJuros() {
		return jurosAoMes != null && jurosAoMes.compareTo(BigDecimal.ZERO) > 0;
	}

	public String toString() {
		String descricao = getNome();

		if(temJuros()) {
			descricao += " (Juros: " + jurosAoMes + "% ao mês)";
		}

		return descricao;
	}
}
