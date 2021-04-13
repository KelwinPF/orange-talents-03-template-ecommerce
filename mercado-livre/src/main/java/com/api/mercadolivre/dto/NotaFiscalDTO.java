package com.api.mercadolivre.dto;

import javax.validation.constraints.NotBlank;

public class NotaFiscalDTO {
	@NotBlank
	String compra;
	@NotBlank
	String comprador;
	
	public NotaFiscalDTO(String compra, String comprador) {
		super();
		this.compra = compra;
		this.comprador = comprador;
	}

	public String getCompra() {
		return compra;
	}

	public String getComprador() {
		return comprador;
	}
	
	
}
