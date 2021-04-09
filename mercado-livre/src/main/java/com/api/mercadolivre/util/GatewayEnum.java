package com.api.mercadolivre.util;

import org.springframework.web.util.UriComponentsBuilder;

import com.api.mercadolivre.entity.Compra;

public enum GatewayEnum {

    PAYPAL("PAYPAL","paypal.com?buyerId="),
    PAGSEGURO("PAGSEGURO","pagseguro.com?returnId=");

    private final String value;
    private final String url;
    
    GatewayEnum(String value,String url) {
        this.value = value;
        this.url = url;
    }

    public String retorno(Compra compra, UriComponentsBuilder builder) {
    	String retorno = builder.path("/sucesso").buildAndExpand(compra.getId()).toString();
    	return url+compra.getId().toString()+"&redirectUrl="+retorno;
    }
    
	
	public static GatewayEnum getEnum(String value) {
	        for(GatewayEnum t : values()) {
	            if(value.equals(t.getValue())) {
	                return t;
	            }
	        }
	        return null;
	}

	public String getValue() {
		return value;
	}

	public String getUrl() {
		return url;
	} 
    
    
    
}
