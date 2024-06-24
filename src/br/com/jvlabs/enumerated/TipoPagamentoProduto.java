package br.com.jvlabs.enumerated;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor @Getter
public enum TipoPagamentoProduto {
	PAGAMENTO_UNICO("Pagamento Único"),
	ASSINATURA_RECORRENTE("Assinatura Recorrente");

	private String descricao;
}
