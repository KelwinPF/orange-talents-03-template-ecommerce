package com.api.mercadolivre.entity;

import java.math.BigDecimal;
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
@Table(name="produtos")
public class Produto {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
	private String nome;
	@Column(nullable = false,scale = 2)
	private BigDecimal valor;
	@Column(nullable = false)
	private Integer quantidade;
	@Column(nullable = false, length = 1000)
	private String descricao;
	@Column(nullable=false)
	@CreationTimestamp
	private LocalDateTime instante;
	@ManyToOne 
	@JoinColumn(nullable = false)
	private Categoria categoria;
	@ManyToOne
	@JoinColumn(nullable = false)
	private Usuario usuario;
	
	public Produto(String nome, BigDecimal valor, Integer quantidade, String descricao,
			 Categoria categoria,Usuario usuario) {
		super();
		this.nome = nome;
		this.valor = valor;
		this.quantidade = quantidade;
		this.descricao = descricao;
		this.categoria = categoria;
		this.usuario = usuario;
	}
	
	public Produto() {
		
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public String getNome() {
		return nome;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public String getDescricao() {
		return descricao;
	}
 
	public Categoria getCategoria() {
		return categoria;
	}

	public Integer SubtrairEstoque(Integer quantidade2) {
		this.quantidade = this.quantidade-quantidade2;
		return quantidade;
	}
	
	
}