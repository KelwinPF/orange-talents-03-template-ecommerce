package com.api.mercadolivre.dto;

import javax.validation.constraints.NotBlank;

import com.api.mercadolivre.entity.Caracteristica;
import com.api.mercadolivre.entity.Produto;

public class CaracteristicaDTO {
	@NotBlank(message="insira um nome")
	private String nome;
	@NotBlank(message="insira uma descricao")
	private String descricao;
	
	public CaracteristicaDTO(@NotBlank(message = "insira um nome") String nome,
			@NotBlank(message = "insira uma descricao") String descricao) {
		super();
		this.nome = nome;
		this.descricao = descricao;
	}
	
	public Caracteristica Converte(Produto produto) {
		
		return new Caracteristica(nome, descricao,produto);
	}

	public String getNome() {
		return nome;
	}
	
}
