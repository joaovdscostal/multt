package br.com.jvlabs.enumerated;

import lombok.Getter;

@Getter
public enum TipoFormaPagamento {
	PIX("Pix", "PIX"),
	CARTAO_CREDITO("Cartão de crédito", "CREDIT_CARD"),
	BOLETO("Boleto", "BOLETO"),
	GRATUITA("Gratuita", "GRATUITA");

	private String exibicao;
	private String asaas;

	TipoFormaPagamento(String exibicao, String asaas) {
		this.exibicao = exibicao;
		this.asaas = asaas;
	}

	public boolean isPix() {
		return this.equals(PIX);
	}

	public boolean isCartao() {
		return this.equals(CARTAO_CREDITO);
	}
	public TipoFormaPagamento getBoleto() {
		return BOLETO;
	}
	public TipoFormaPagamento getPix() {
		return BOLETO;
	}
}
