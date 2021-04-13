package com.api.mercadolivre.util;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.api.mercadolivre.dto.NotaFiscalDTO;
import com.api.mercadolivre.dto.RankingDTO;
import com.api.mercadolivre.entity.Compra;

@Component
public class NotaRanking implements Processa{

	@Override
	public void GerarNota(Compra compra) {
		RestTemplate rest = new RestTemplate();
		rest.postForEntity("http://localhost:8080/notasfiscais",
				new NotaFiscalDTO(compra.getId().toString(),
						compra.getUsuario().getLogin().toString()),String.class);
	}
	
	@Override
	public void Ranking(Compra compra) {
		RestTemplate rest = new RestTemplate();
		rest.postForEntity("http://localhost:8080/ranking",
				new RankingDTO(compra.getId().toString(),
						compra.getProduto().getUsuario().getLogin().toString()),String.class);
		
	}

}
