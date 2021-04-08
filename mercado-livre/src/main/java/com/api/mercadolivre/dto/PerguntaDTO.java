package com.api.mercadolivre.dto;

import javax.validation.constraints.NotBlank;
import com.api.mercadolivre.entity.Pergunta;
import com.api.mercadolivre.entity.Produto;
import com.api.mercadolivre.entity.Usuario;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PerguntaDTO {
	
	@NotBlank(message="insira um titulo")
	private String titulo;

	public PerguntaDTO(@JsonProperty("titulo") @NotBlank(message = "insira um titulo") String titulo) {
		super();
		this.titulo = titulo;
	}

	public Pergunta converte(Produto produto, Usuario one) {
		return new Pergunta(titulo, one, produto);
	}
}
