package com.api.mercadolivre.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.api.mercadolivre.dto.TransacaoDTO;
import com.api.mercadolivre.entity.Compra;
import com.api.mercadolivre.entity.Pergunta;
import com.api.mercadolivre.entity.Transacao;
import com.api.mercadolivre.repository.CompraRepository;
import com.api.mercadolivre.repository.TransacaoRepository;
import com.api.mercadolivre.util.EmailSender;
import com.api.mercadolivre.util.Processa;
import com.api.mercadolivre.util.TransacaoStatusEnum;

@RestController
@RequestMapping("/transacoes")
public class TransacaoController {
	
	private CompraRepository compraRepository;
	private TransacaoRepository transacaoRepository;
	private EmailSender sender;
	private Processa processa;
	
	public TransacaoController(CompraRepository compraRepository,TransacaoRepository transacaoRepository,
			EmailSender sender,Processa processa) {
		this.transacaoRepository = transacaoRepository;
		this.compraRepository = compraRepository;
		this.sender = sender;
		this.processa = processa;
	}
	
	@PostMapping(value={"/retorno-pagseguro/{id}","/retorno-paypal/{id}"})
	public ResponseEntity<?> cadastrar(@PathVariable(name="id",required =true) Long id
			,@Valid @RequestBody TransacaoDTO dto, UriComponentsBuilder uri){
		
		Optional<Compra> compra = compraRepository.findById(id);
		
		if(compra.isEmpty()) {
			return ResponseEntity.badRequest().build();
		}
	
		Transacao transaction = transacaoRepository.save(dto.convert(compra.get()));
		processar(uri,transaction,compra.get());
		return ResponseEntity.ok().build();
	}
	
	public void processar(UriComponentsBuilder uri,Transacao transacao,Compra compra) {
		if(transacao.getStatus().equals(TransacaoStatusEnum.SUCESSO)) {
			transacao.getCompra().finalizacompra(compraRepository);
			processa.GerarNota(compra);
			processa.Ranking(compra);
		};
		sendMail(transacao,transacao.getStatus(),uri);
	}
	
	private Boolean sendMail(Transacao transacao, TransacaoStatusEnum transacaoStatusEnum, 
			UriComponentsBuilder uri) {
		if(transacao.getStatus().equals(TransacaoStatusEnum.SUCESSO)) {
			return sender.send("Caro Sr."+transacao.getCompra().getUsuario().getLogin()+", "+
					"a sua transacao foi finalizada com sucesso: "+transacao.getCompra().getQuantidade().toString()+""
					+ " unidades do produto "+transacao.getCompra().getProduto().getNome()+ 
					" pelo valor unitário de "+ transacao.getCompra().getValor_unitario().toString());		
		}else {
			return sender.send("Caro Sr."+transacao.getCompra().getUsuario().getLogin()+", "+
					"a sua transacao Não foi concluida com sucesso, acesse o link para tentar novamente"
					+transacao.getCompra().getGateway().retorno(transacao.getCompra(),uri));
		}
	}
}
