package com.api.mercadolivre.configuration.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.api.mercadolivre.entity.Usuario;
import com.api.mercadolivre.util.RoleEnum;

public class JwtUserFactory {
	
    public static JwtUser create(Usuario user) {
        return new JwtUser(user.getId(), user.getLogin(), user.getSenha(), createGrantedAuthorities(user.getRole()));
    }
    
    private static List<GrantedAuthority> createGrantedAuthorities(RoleEnum role) {
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority(role.toString()));
        return authorities;
    }
}
