package com.api.mercadolivre.response;

public class OpiniaoResponse {
	public Integer nota;
	public String descricao;
	public String titulo;
	public String usuario;
	
	public OpiniaoResponse(Integer nota, String descricao, String titulo, String usuario) {
		super();
		this.nota = nota;
		this.descricao = descricao;
		this.titulo = titulo;
		this.usuario = usuario;
	}
	public Integer getNota() {
		return nota;
	}
	public String getDescricao() {
		return descricao;
	}
	public String getTitulo() {
		return titulo;
	}
	public String getUsuario() {
		return usuario;
	}
	
	
}
