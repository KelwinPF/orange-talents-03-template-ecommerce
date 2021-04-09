package com.api.mercadolivre.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.mercadolivre.configuration.security.JwtUser;
import com.api.mercadolivre.dto.PerguntaDTO;
import com.api.mercadolivre.entity.Pergunta;
import com.api.mercadolivre.entity.Produto;
import com.api.mercadolivre.repository.PerguntaRepository;
import com.api.mercadolivre.repository.ProdutoRepository;
import com.api.mercadolivre.repository.UsuarioRepository;
import com.api.mercadolivre.util.EmailSender;

@RestController
@RequestMapping("/produtos")
public class PerguntaProdutoController {
	
	private PerguntaRepository perguntaRrepository;
	private ProdutoRepository produtoReposiotory;
	private UsuarioRepository usuarioRepository;
	private EmailSender sender;
	
	public PerguntaProdutoController(UsuarioRepository usuarioRepository,
			PerguntaRepository perguntaRrepository,
			ProdutoRepository produtoReposiotory,
			EmailSender sender) {
		this.perguntaRrepository = perguntaRrepository;
		this.produtoReposiotory = produtoReposiotory;
		this.usuarioRepository =  usuarioRepository;
		this.sender = sender;
	}
	
	@PostMapping(value = "/{id}/perguntas")
	public ResponseEntity<?> perguntas(@PathVariable(name = "id", required = true) Long id
			,@Valid @RequestBody PerguntaDTO dto,@AuthenticationPrincipal JwtUser user){
		
		Optional<Produto> prod = produtoReposiotory.findById(id);
		
		if(prod.isEmpty()){
			return ResponseEntity.notFound().build();
		}
		Pergunta pergunta = perguntaRrepository.save(dto.converte(prod.get(), usuarioRepository.getOne(user.getId())));
		
		sendMail(pergunta);
		
		return ResponseEntity.ok().build();
	}
	
	private Boolean sendMail(Pergunta pergunta) {
		return sender.send("Caro Sr."+pergunta.getProduto().getUsuario().getLogin()+", "+
				"o usuário "+pergunta.getUsuario().getLogin()+ " fez a seguinte pergunta ao seu " +
				"produto " +pergunta.getProduto().getNome() + ": "+ pergunta.getTitulo());
	}
	
	
}
