package com.api.mercadolivre.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name="perguntas")
public class Pergunta {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
	private String titulo;
	@Column(nullable=false)
	@CreationTimestamp
	private LocalDateTime instante;
	@ManyToOne
	@JoinColumn(nullable=false)
	private Usuario usuario;
	@ManyToOne
	@JoinColumn(nullable=false)
	private Produto produto;
	
	public Pergunta(String titulo, Usuario usuario, Produto produto) {
		super();
		this.titulo = titulo;
		this.usuario = usuario;
		this.produto = produto;
	}

	public Produto getProduto() {
		return produto;
	}

	public String getTitulo() {
		return titulo;
	}

	public Usuario getUsuario() {
		return usuario;
	}
	
	public LocalDateTime getInstante() {
		return instante;
	}

	public Pergunta() {
		
	}
}
