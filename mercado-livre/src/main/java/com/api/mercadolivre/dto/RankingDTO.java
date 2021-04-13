package com.api.mercadolivre.dto;

import javax.validation.constraints.NotBlank;

public class RankingDTO {
	@NotBlank
	String compra;
	@NotBlank
	String vendedor;
	
	public RankingDTO(String compra, String vendedor) {
		super();
		this.compra = compra;
		this.vendedor = vendedor;
	}

	public String getCompra() {
		return compra;
	}

	public String getVendedor() {
		return vendedor;
	}
	
	
}
