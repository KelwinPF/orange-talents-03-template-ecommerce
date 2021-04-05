package com.api.mercadolivre.configuration.security.dto;

public class TokenDTO {

    private String token;
    
    public String getToken() {
		return token;
	}

	public TokenDTO(String token) {
        this.token = token;
    }
}
