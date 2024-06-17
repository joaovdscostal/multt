package br.com.jvlabs.dto;

import lombok.Builder;
import lombok.Data;

@Data @Builder
public class ImagemJsonDTO {
	private Long id;
	private String src;
}
