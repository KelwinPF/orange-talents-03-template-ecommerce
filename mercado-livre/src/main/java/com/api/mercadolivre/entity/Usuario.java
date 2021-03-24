package com.api.mercadolivre.entity;

import java.time.LocalDateTime;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name="usuarios")
public class Usuario{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@Column(nullable=false)
	@CreationTimestamp
	private LocalDateTime instante;
	@Column(nullable=false,unique=true)
	private String login;
	@Column(nullable=false)
	private String senha;
	
	public Usuario(String login, String senha) {
		this.login = login;
		this.senha = senha;
	}
	
	@Deprecated
	public Usuario() {
		
	}

	
}
