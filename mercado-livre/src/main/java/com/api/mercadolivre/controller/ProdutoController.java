package com.api.mercadolivre.controller;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;
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
import com.api.mercadolivre.dto.ImagemDTO;
import com.api.mercadolivre.dto.ProdutoDTO;
import com.api.mercadolivre.entity.Produto;
import com.api.mercadolivre.repository.CaracteristicaRepository;
import com.api.mercadolivre.repository.CategoriaRepository;
import com.api.mercadolivre.repository.ImagemRepository;
import com.api.mercadolivre.repository.ProdutoRepository;
import com.api.mercadolivre.repository.UsuarioRepository;
import com.api.mercadolivre.util.FileUploader;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {
	
	private FileUploader uploader;
	private ProdutoRepository produtoReposiotory;
	private CategoriaRepository categoriaRepository;
	private UsuarioRepository usuarioRepository;
	private CaracteristicaRepository caracteristicaRepository;
	private ImagemRepository imagemRepository;
	
	public ProdutoController(ProdutoRepository produtoReposiotory,
			CategoriaRepository categoriaRepository, 
			UsuarioRepository usuarioRepository, 
			CaracteristicaRepository caracteristicaRepository,
			ImagemRepository imagemRepository,
			FileUploader uploader) {
		
		this.produtoReposiotory = produtoReposiotory;
		this.categoriaRepository = categoriaRepository;
		this.usuarioRepository = usuarioRepository;
		this.caracteristicaRepository = caracteristicaRepository;
		this.imagemRepository = imagemRepository;
		this.uploader = uploader;
	}
	
	@PostMapping
	public ResponseEntity<?> cadastrar(@Valid @RequestBody ProdutoDTO dto
			,@AuthenticationPrincipal JwtUser user){
		
		Produto prod = produtoReposiotory.save(
				dto.converter(categoriaRepository,usuarioRepository,user.getId()));
		
		caracteristicaRepository.saveAll(dto.getCaracteristicas().stream()
                .map(caracteristica -> caracteristica.Converte(prod))
                .collect(Collectors.toList()));
		
		return ResponseEntity.ok().build();
	}
	
	@PostMapping(value = "/{id}/imagem")
	public ResponseEntity<?> upload(@PathVariable(name="id",required =true) Long id,
			@Valid ImagemDTO files,@AuthenticationPrincipal JwtUser user) {
	
		Optional<Produto> prod = produtoReposiotory.findById(id);
		if(prod.isEmpty()){
			return ResponseEntity.notFound().build();
		}	
		
		if (user.getId() != prod.get().getUsuario().getId()) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		}
		
		List<String> adresses = uploader.upload(files.getFiles());
		
		imagemRepository.saveAll(files.converter(adresses,prod.get()));
		
		return ResponseEntity.ok().build();
	}
}
