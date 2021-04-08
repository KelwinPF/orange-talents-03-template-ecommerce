package com.api.mercadolivre.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.mercadolivre.entity.Caracteristica;
import com.api.mercadolivre.entity.Opiniao;
import com.api.mercadolivre.entity.Pergunta;
import com.api.mercadolivre.entity.Produto;
import com.api.mercadolivre.repository.CaracteristicaRepository;
import com.api.mercadolivre.repository.OpiniaoRepository;
import com.api.mercadolivre.repository.PerguntaRepository;
import com.api.mercadolivre.repository.ProdutoRepository;
import com.api.mercadolivre.response.ProdutoResponse;


@RestController
@RequestMapping("/produtos")
public class DetalhesProdutoController {
	
	private OpiniaoRepository opiniaoRepository;
	private PerguntaRepository perguntaRepository;
	private ProdutoRepository produtoReposiotory;
	private CaracteristicaRepository caracteristicaRepository;
	
	public DetalhesProdutoController(OpiniaoRepository opiniaoRepository,
			PerguntaRepository perguntaRepository,
			ProdutoRepository produtoReposiotory,
			CaracteristicaRepository caracteristicaRepository) {
		this.opiniaoRepository = opiniaoRepository;
		this.perguntaRepository = perguntaRepository;
		this.produtoReposiotory = produtoReposiotory;
		this.caracteristicaRepository = caracteristicaRepository;
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ProdutoResponse> detalhar(@PathVariable Long id) {
		
		Optional<Produto> produto = produtoReposiotory.findById(id);
	
		if(produto.isPresent()) {
			List<Caracteristica> caracteristica = caracteristicaRepository.findAllByProdutoId(id);
			List<Opiniao> opinioes = opiniaoRepository.findAllByProdutoId(id);
			List<Pergunta> perguntas = perguntaRepository.findAllByProdutoId(id);
			
			ProdutoResponse prod_res = new ProdutoResponse(produto.get(),
					caracteristica,opinioes,perguntas);
			return ResponseEntity.ok(prod_res);
		}
		return ResponseEntity.notFound().build();
	}
}
