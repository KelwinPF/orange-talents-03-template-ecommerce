package com.api.mercadolivre.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.springframework.web.util.UriComponentsBuilder;

import com.api.mercadolivre.configuration.EmEstoque;
import com.api.mercadolivre.configuration.Exists;
import com.api.mercadolivre.entity.Compra;
import com.api.mercadolivre.entity.Produto;
import com.api.mercadolivre.entity.Usuario;
import com.api.mercadolivre.util.GatewayEnum;

@EmEstoque
public class CompraDTO {
	@NotNull
    @Pattern(regexp="^(PAGSEGURO|PAYPAL)$", message = "Para o gateway somente são aceitos os valores PAGSEGURO ou PAYPAL")
    private String gateway;
	@NotNull
	@Exists(table = "produtos")
	private Long produto;
	@NotNull @Min(1)
	private Integer quantidade;
	
	public CompraDTO(@NotNull String gateway, @NotNull Long produto, @NotNull @Min(1) Integer quantidade) {
		super();
		this.gateway = gateway;
		this.produto = produto;
		this.quantidade = quantidade;
	}

	public Compra convert(Produto produto, Usuario usuario, UriComponentsBuilder builder) {
		return new Compra(GatewayEnum.getEnum(gateway),produto , quantidade, usuario, produto.getValor());
	}

	public Long getProduto() {
		return produto;
	}

	public Integer getQuantidade() {
		return quantidade;
	}
	
}
