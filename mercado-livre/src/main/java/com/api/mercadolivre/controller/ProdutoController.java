package com.api.mercadolivre.controller;
import java.util.stream.Collectors;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.mercadolivre.configuration.security.JwtUser;
import com.api.mercadolivre.dto.ProdutoDTO;
import com.api.mercadolivre.entity.Produto;
import com.api.mercadolivre.repository.CaracteristicaRepository;
import com.api.mercadolivre.repository.CategoriaRepository;
import com.api.mercadolivre.repository.ProdutoRepository;
import com.api.mercadolivre.repository.UsuarioRepository;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {
	
	private ProdutoRepository produtoReposiotory;
	private CategoriaRepository categoriaRepository;
	private UsuarioRepository usuarioRepository;
	private CaracteristicaRepository caracteristicaRepository;
	
	public ProdutoController(ProdutoRepository produtoReposiotory,
			CategoriaRepository categoriaRepository, 
			UsuarioRepository usuarioRepository, 
			CaracteristicaRepository caracteristicaRepository) {
		this.produtoReposiotory = produtoReposiotory;
		this.categoriaRepository = categoriaRepository;
		this.usuarioRepository = usuarioRepository;
		this.caracteristicaRepository = caracteristicaRepository;
		
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<?> cadastrar(@Valid @RequestBody ProdutoDTO dto
			,@AuthenticationPrincipal JwtUser user){
		
		Produto prod = produtoReposiotory.save(
				dto.converter(categoriaRepository,usuarioRepository,user.getId()));
		
		caracteristicaRepository.saveAll(dto.getCaracteristicas().stream()
                .map(caracteristica -> caracteristica.Converte(prod))
                .collect(Collectors.toList()));
		
		return ResponseEntity.ok().build();
	}
}
