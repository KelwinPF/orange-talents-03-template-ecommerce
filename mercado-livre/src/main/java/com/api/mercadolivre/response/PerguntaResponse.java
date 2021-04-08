package com.api.mercadolivre.response;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PerguntaResponse {
	private String titulo;
	private String usuario;
	private String instante;
	
	public PerguntaResponse(String titulo, String usuario, LocalDateTime instante) {
		super();
		this.titulo = titulo;
		this.usuario = usuario;
		this.instante = instante.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
	}

	public String getTitulo() {
		return titulo;
	}

	public String getUsuario() {
		return usuario;
	}

	public String getInstante() {
		return instante;
	}
	
}
