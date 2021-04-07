package com.api.mercadolivre.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.api.mercadolivre.entity.Opiniao;
import com.api.mercadolivre.entity.Produto;
import com.api.mercadolivre.entity.Usuario;

public class OpiniaoDTO {
	
	@Min(value=0)
	@Max(value=5)
	@NotNull
	private Integer nota;
	@NotBlank(message="insira uma descricao")
	@Size(max=500)
	private String descricao;
	@NotBlank(message="insira um titulo")
	private String titulo;
	
	public OpiniaoDTO(@Min(0) @Max(5) @NotNull Integer nota,
			@NotBlank(message = "insira uma descricao") @Size(max = 500) String descricao,
			@NotBlank(message = "insira um titulo") String titulo) {
		super();
		this.nota = nota;
		this.descricao = descricao;
		this.titulo = titulo;
	}

	public Opiniao converter(Produto produto, Usuario user) {
		return new Opiniao(nota, titulo, descricao,user,produto);

	}
	
}
