package com.api.mercadolivre.controller;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.mercadolivre.dto.UsuarioDTO;
import com.api.mercadolivre.entity.Usuario;
import com.api.mercadolivre.repository.UsuarioRepository;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
	
	private UsuarioRepository repo;
	
	public UsuarioController(UsuarioRepository repo) {
		this.repo = repo;		
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<?> cadastrar(@Valid @RequestBody UsuarioDTO dto) {
		Usuario user = dto.converter();
		repo.save(user);
		return ResponseEntity.ok().build();
	}

}
