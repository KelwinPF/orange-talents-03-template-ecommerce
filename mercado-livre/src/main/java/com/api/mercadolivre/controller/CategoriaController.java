package com.api.mercadolivre.controller;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.mercadolivre.dto.CategoriaDTO;
import com.api.mercadolivre.repository.CategoriaRepository;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {
	
	private CategoriaRepository repository;
	
	public CategoriaController(CategoriaRepository repository) {
		this.repository=repository;
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<?> cadastrar(@Valid @RequestBody CategoriaDTO dto){
		repository.save(dto.converter(repository));
		return ResponseEntity.ok().build();
	}
	
}
