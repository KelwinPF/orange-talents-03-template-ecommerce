package com.api.mercadolivre.controller;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.api.mercadolivre.dto.NotaFiscalDTO;
import com.api.mercadolivre.dto.RankingDTO;

@RestController
public class OutrosSistemasController {
	@PostMapping(value="/notasfiscais")
	public void GeraNota(@Valid @RequestBody NotaFiscalDTO request) throws InterruptedException {
		System.out.println("criando nota para "+request.getCompra()+" do comprador "+ request.getComprador());
		Thread.sleep(150);
		
	}
	
	@PostMapping(value="/ranking")
	public void Ranking(@Valid @RequestBody RankingDTO request) throws InterruptedException {
		System.out.println("rankeado da compra "+request.getCompra() + "pelo vendedor "+ request.getVendedor());
		Thread.sleep(150);
		
	}
}
