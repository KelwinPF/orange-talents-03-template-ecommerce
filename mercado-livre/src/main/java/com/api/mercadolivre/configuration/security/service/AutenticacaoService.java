package com.api.mercadolivre.configuration.security.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.api.mercadolivre.configuration.security.JwtUserFactory;
import com.api.mercadolivre.entity.Usuario;
import com.api.mercadolivre.repository.UsuarioRepository;

@Service
@Component("AutenticacaoService")
public class AutenticacaoService implements UserDetailsService{
	
	@Autowired
	private UsuarioRepository repo;
	
	@Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Usuario> user = repo.findByLogin(username);
        
        if (user.isPresent()) {
            return JwtUserFactory.create(user.get());
        }
        throw new UsernameNotFoundException("usuario não encontrado.");
    }
}
