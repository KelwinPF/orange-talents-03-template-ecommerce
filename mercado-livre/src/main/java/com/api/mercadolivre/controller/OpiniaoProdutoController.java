package com.api.mercadolivre.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.mercadolivre.configuration.security.JwtUser;
import com.api.mercadolivre.dto.OpiniaoDTO;
import com.api.mercadolivre.entity.Produto;
import com.api.mercadolivre.repository.OpiniaoRepository;
import com.api.mercadolivre.repository.ProdutoRepository;
import com.api.mercadolivre.repository.UsuarioRepository;

@RestController
@RequestMapping("/produtos")
public class OpiniaoProdutoController {
	
	private OpiniaoRepository opiniaoRepository;
	private UsuarioRepository usuarioRepository;
	private ProdutoRepository produtoReposiotory;
	
	public OpiniaoProdutoController(OpiniaoRepository opiniaoRepository, UsuarioRepository usuarioRepository, ProdutoRepository produtoReposiotory) {
		this.opiniaoRepository = opiniaoRepository;
		this.usuarioRepository = usuarioRepository;
		this.produtoReposiotory = produtoReposiotory;
	}
	
	@PostMapping(value = "/{id}/opinioes")
	public ResponseEntity<?> opinar(@PathVariable(name = "id", required = true) Long id,@Valid @RequestBody OpiniaoDTO dto,@AuthenticationPrincipal JwtUser user){
		
		Optional<Produto> prod = produtoReposiotory.findById(id);
		
		if(prod.isEmpty()){
			return ResponseEntity.notFound().build();
		}	
		
		opiniaoRepository.save(dto.converter(prod.get(),usuarioRepository.getOne(user.getId())));
		return ResponseEntity.ok().build();
	}
}
