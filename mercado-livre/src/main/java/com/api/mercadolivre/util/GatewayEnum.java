package com.api.mercadolivre.util;

import org.springframework.web.util.UriComponentsBuilder;

import com.api.mercadolivre.entity.Compra;

public enum GatewayEnum {

    PAYPAL("PAYPAL","paypal.com?buyerId=","/retorno-paypal/"),
    PAGSEGURO("PAGSEGURO","pagseguro.com?returnId=","/retorno-pagseguro/");

    private final String value;
    private final String url;
    private final String retorno_url;
    
    GatewayEnum(String value,String url,String retorno_url) {
        this.value = value;
        this.url = url;
        this.retorno_url = retorno_url;
    }

    public String retorno(Compra compra, UriComponentsBuilder builder) {
    	String retorno = builder.path(retorno_url+compra.getId().toString())
    			.buildAndExpand(compra.getId()).toString();
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
