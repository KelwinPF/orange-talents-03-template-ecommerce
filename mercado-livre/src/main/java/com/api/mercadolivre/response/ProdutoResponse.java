package com.api.mercadolivre.response;

import java.math.BigDecimal;
import java.util.List;
import java.util.OptionalDouble;
import java.util.stream.Collectors;

import com.api.mercadolivre.entity.Caracteristica;
import com.api.mercadolivre.entity.Opiniao;
import com.api.mercadolivre.entity.Pergunta;
import com.api.mercadolivre.entity.Produto;

public class ProdutoResponse {
	
	private String nome_produto;
	private String descricao_produto;
	private BigDecimal preco_produto;
	private String cadastrado_por;
	private List<CaracteristicaResponse> caracteristicas_produto;
	private Double media_notas;
	private Integer quantidade_notas;
	private List<OpiniaoResponse> opinioes_produto;
	private List<PerguntaResponse> perguntas;
	
	public ProdutoResponse(Produto produto,List<Caracteristica> caracteristicas_produto,
			List<Opiniao> opinioes_produto, List<Pergunta> perguntas) {
		super();
		this.nome_produto = produto.getNome();
		this.descricao_produto = produto.getDescricao();
		this.preco_produto = produto.getValor();
		this.caracteristicas_produto = this.caracteristicaToResponse(caracteristicas_produto);
		this.media_notas = this.calculaMedia(opinioes_produto);
		this.quantidade_notas = opinioes_produto.size();
		this.opinioes_produto = this.opiniaoToResponse(opinioes_produto);
		this.perguntas = this.perguntaToResponse(perguntas);
		this.cadastrado_por = produto.getUsuario().getLogin();
	}

	public String getNome_produto() {
		return nome_produto;
	}

	public String getDescricao_produto() {
		return descricao_produto;
	}
	
	public String getCadastrado_por() {
		return cadastrado_por;
	}

	public BigDecimal getPreco_produto() {
		return preco_produto;
	}

	public List<CaracteristicaResponse> getCaracteristicas_produto() {
		return caracteristicas_produto;
	}

	public Double getMedia_notas() {
		return media_notas;
	}

	public Integer getQuantidade_notas() {
		return quantidade_notas;
	}

	public List<OpiniaoResponse> getOpinioes_produto() {
		return opinioes_produto;
	}

	public List<PerguntaResponse> getPerguntas() {
		return perguntas;
	}

	public List<PerguntaResponse> perguntaToResponse(List<Pergunta> perguntas){
		return perguntas.stream().map(pergunta->new PerguntaResponse(pergunta.getTitulo(),
				pergunta.getUsuario().getLogin(),pergunta.getInstante())).collect(Collectors.toList());
	}
	
	public List<OpiniaoResponse> opiniaoToResponse(List<Opiniao> opinioes){
		return  opinioes.stream()
				.map(opiniao->new OpiniaoResponse(opiniao.getNota(),opiniao.getDescricao(),
						opiniao.getTitulo(),opiniao.getUsuario().getLogin())).collect(Collectors.toList());
	}
	
	public List<CaracteristicaResponse> caracteristicaToResponse(List<Caracteristica> caracteristicas){
		return caracteristicas.stream()
				.map(carac->new CaracteristicaResponse(carac.getNome(),
						carac.getDescricao())).collect(Collectors.toList());
	}
	
	public double calculaMedia(List<Opiniao> opinioes) {
		OptionalDouble opitional = opinioes.stream().mapToDouble(op->op.getNota()).average();
		return opitional.orElse(0);
	}
	
}
