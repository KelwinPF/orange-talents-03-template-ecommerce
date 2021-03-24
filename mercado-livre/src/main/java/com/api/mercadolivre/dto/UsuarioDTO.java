package com.api.mercadolivre.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.api.mercadolivre.configuration.Unique;
import com.api.mercadolivre.entity.Usuario;
import org.springframework.security.crypto.bcrypt.BCrypt;

public class UsuarioDTO {
	@NotBlank(message="insira um login")
	@Unique(column = "login", table = "usuarios",message="login já existente")
	@Email(message="formato incorreto de email")
	private String login;
	@NotBlank(message="insira uma senha")
	@Size(min = 6,message="a senha deve possuir no minimo 6 caracteres")
	private String senha;
	
	public UsuarioDTO(
			@NotBlank(message = "insira um login") @Email(message = "formato incorreto de email") String login,
			@NotBlank(message = "insira uma senha") String senha) {
		super();
		this.login = login;
		this.senha = senha;
	}

	public Usuario converter() {
		String bc = BCrypt.hashpw(senha,BCrypt.gensalt());
		return new Usuario(login,bc);
	}
}
