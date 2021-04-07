package com.api.mercadolivre.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="imagens")
public class Imagem {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String uri;
	@ManyToOne
	@JoinColumn(nullable=false)
	private Produto produto;
	
	public Imagem(String uri,Produto produto) {
		this.uri = uri;
		this.produto = produto;
	}

	public Long getId() {
		return id;
	}

}
