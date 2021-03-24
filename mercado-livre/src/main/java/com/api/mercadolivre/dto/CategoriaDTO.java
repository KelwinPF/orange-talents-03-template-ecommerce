package com.api.mercadolivre.dto;

import javax.validation.constraints.NotBlank;

import com.api.mercadolivre.configuration.Exists;
import com.api.mercadolivre.configuration.Unique;
import com.api.mercadolivre.entity.Categoria;
import com.api.mercadolivre.repository.CategoriaRepository;

public class CategoriaDTO {
	@NotBlank(message="insira um nome")
	@Unique(column = "nome", table = "categorias",message="nome já existente")
	private String nome;
	@Exists(table = "categorias")
	private Long mae;
	
	public CategoriaDTO(@NotBlank(message = "insira um nome") String nome, Long mae) {
		super();
		this.nome = nome;
		this.mae = mae;
	}

	public Categoria converter(CategoriaRepository repository) {
		Categoria categoria = null;
		if(mae!=null) {
			 categoria = repository.getOne(mae);
		}
		return new Categoria(nome,categoria);
	}
	
	
}
