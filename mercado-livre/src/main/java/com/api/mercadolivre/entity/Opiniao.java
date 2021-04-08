package com.api.mercadolivre.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="opinioes")
public class Opiniao {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false,columnDefinition = "TINYINT")
	private Integer nota;
	@Column(nullable = false)
	private String titulo;
	@Column(nullable = false, length = 500)
	private String descricao;
	@ManyToOne
	private Usuario usuario;
	@ManyToOne
	private Produto produto;
	
	public Opiniao(Integer nota, String titulo, String descricao, Usuario usuario, Produto produto) {
		super();
		this.nota = nota;
		this.titulo = titulo;
		this.descricao = descricao;
		this.usuario = usuario;
		this.produto = produto;
	}
	
	public Integer getNota() {
		return nota;
	}
	public String getTitulo() {
		return titulo;
	}
	public String getDescricao() {
		return descricao;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public Opiniao() {
		
	}
	
}
