package com.api.mercadolivre.response;

public class CaracteristicaResponse {
	private String nome;
	private String descricao;
	
	public CaracteristicaResponse(String nome, String descricao) {
		super();
		this.nome = nome;
		this.descricao = descricao;
	}

	public String getNome() {
		return nome;
	}

	public String getDescricao() {
		return descricao;
	}
	
	
}
