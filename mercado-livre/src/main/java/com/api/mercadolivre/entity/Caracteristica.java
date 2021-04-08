package com.api.mercadolivre.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="caracteristicas")
public class Caracteristica {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
	private String nome;
	@Column(nullable = false)
	private String descricao;
	@ManyToOne
	@JoinColumn(name="produto_id", referencedColumnName="id",nullable=false)
	private Produto produto;
	
	public Caracteristica(String nome, String descricao,Produto produto) {
		super();
		this.nome = nome;
		this.descricao = descricao;
		this.produto = produto;
	}
	
	public Caracteristica() {
		
	}

	public String getNome() {
		return nome;
	}

	public String getDescricao() {
		return descricao;
	}

	
}
