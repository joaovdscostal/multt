package br.com.jvlabs.util;

import lombok.Builder;
import lombok.Data;

@Builder @Data
public class EmailUtil {

	private String nome;
	private String email;
}
