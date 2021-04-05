package com.api.mercadolivre.configuration.security.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class JwtAuthenticationDTO {
	
	@NotNull(message="Informe uma Senha")
	@NotEmpty(message="Informe uma Senha")
    private String login;
	@NotNull(message="Informe uma Senha")
	@NotEmpty(message="Informe uma Senha")
    private String senha;
	
    public String getLogin() {
        return this.login;
    }

    public String getSenha() {
        return senha;
    }
    
    
    
	public JwtAuthenticationDTO(
			@NotNull(message = "Informe uma Senha") @NotEmpty(message = "Informe uma Senha") String login,
			@NotNull(message = "Informe uma Senha") @NotEmpty(message = "Informe uma Senha") String senha) {
		super();
		this.login = login;
		this.senha = senha;
	}

	public UsernamePasswordAuthenticationToken converter() {
		return new UsernamePasswordAuthenticationToken(login,senha);
	}
    
    
}
