package com.api.mercadolivre.entity;

import java.time.LocalDateTime;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.api.mercadolivre.util.RoleEnum;

@Entity
@Table(name="usuarios")
public class Usuario {
	
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
    @Enumerated(EnumType.STRING)
    private RoleEnum role = RoleEnum.ROLE_USER;
	
	public Usuario(String login, String senha) {
		this.login = login;
		this.senha = senha;
	}
	
	@Deprecated
	public Usuario() {
		
	}

	public String getLogin() {
		return login;
	}

	public String getSenha() {
		return senha;
	}

	public Long getId() {
		return id;
	}

	public LocalDateTime getInstante() {
		return instante;
	}

	public RoleEnum getRole() {
		return role;
	}

	
}
