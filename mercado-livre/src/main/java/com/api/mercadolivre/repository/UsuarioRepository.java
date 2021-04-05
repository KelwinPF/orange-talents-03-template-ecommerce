package com.api.mercadolivre.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.mercadolivre.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario,Long>{

	Optional<Usuario> findByLogin(String username);
	
}
