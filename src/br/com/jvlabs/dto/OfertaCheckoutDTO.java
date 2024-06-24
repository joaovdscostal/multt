package br.com.jvlabs.dto;

import br.com.jvlabs.model.Oferta;
import lombok.Builder;
import lombok.Data;

@Data @Builder
public class OfertaCheckoutDTO {
	private Oferta oferta;
	private Boolean selected;
}
