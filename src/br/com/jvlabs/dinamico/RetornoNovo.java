package br.com.jvlabs.dinamico;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class RetornoNovo implements Comparable<RetornoNovo> {
	private Long value;
	private String text;
	private String html;

	@Override
	public int compareTo(RetornoNovo o) {
		return this.text.compareTo(o.getText());
	}
}

