package com.api.mercadolivre.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="categorias")
public class Categoria{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@Column(nullable=false,unique=true)
	private String nome;
    @ManyToOne
    @JoinColumn(name = "categoria_mom", referencedColumnName = "id")
    private Categoria categoria_mom;
    
	public Categoria(String nome, Categoria categoria_mom) {
		super();
		this.nome = nome;
		this.categoria_mom = categoria_mom;
	}
	
    @Deprecated
	public Categoria() {
		
	}
    
}
