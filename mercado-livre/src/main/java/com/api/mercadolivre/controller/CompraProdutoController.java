package com.api.mercadolivre.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.api.mercadolivre.configuration.security.JwtUser;
import com.api.mercadolivre.dto.CompraDTO;
import com.api.mercadolivre.entity.Compra;
import com.api.mercadolivre.entity.Produto;
import com.api.mercadolivre.repository.CompraRepository;
import com.api.mercadolivre.repository.ProdutoRepository;
import com.api.mercadolivre.repository.UsuarioRepository;
import com.api.mercadolivre.util.EmailSender;

@RestController
@RequestMapping("/produtos/compras")
public class CompraProdutoController {
	
	private CompraRepository compraRepository;
	private UsuarioRepository usuarioRepository;
	private ProdutoRepository produtoRepository;
	private EmailSender sender;
	
	public CompraProdutoController(CompraRepository compraRepository, 
			ProdutoRepository produtoRepository, UsuarioRepository usuarioRepository,EmailSender sender) {
		this.compraRepository=compraRepository;
		this.produtoRepository = produtoRepository;
		this.usuarioRepository = usuarioRepository;
		this.sender = sender;
	}
	
	@PostMapping
	public ResponseEntity<?> cadastrar(@Valid @RequestBody CompraDTO dto,
			@AuthenticationPrincipal JwtUser user,UriComponentsBuilder builder){
		Produto produto = produtoRepository.getOne(dto.getProduto());
			
		Compra compra = compraRepository.save(
				dto.convert(produto,usuarioRepository.getOne(user.getId()),builder));
		
		produto.SubtrairEstoque(dto.getQuantidade());
		
		produtoRepository.save(produto);
		sendMail(compra);
		return ResponseEntity.status(HttpStatus.FOUND).body(compra.getGateway().retorno(compra, builder));
	}
	
	private Boolean sendMail(Compra compra) {
		return sender.send("Olá Sr(a). "+compra.getProduto().getUsuario().getLogin()+", foram compradas "
				+compra.getQuantidade().toString() +" unidades de seu produto "
				+compra.getProduto().getNome()+ " pelo valor unitário de R$ "+ 
				compra.getProduto().getValor().toString() + "."
				+ " Pelo usuário "+compra.getUsuario().getLogin());
	}
	
}
