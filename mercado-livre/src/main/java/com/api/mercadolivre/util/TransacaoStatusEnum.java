package com.api.mercadolivre.util;

public enum TransacaoStatusEnum {
	SUCESSO("SUCESSO","1"),
	ERRO("ERRO","0");
	
	private final String PagSeguroValue;
	private final String PaypalValue;
	
	TransacaoStatusEnum(String PaypalValue,String PagSeguroValue){
		this.PaypalValue = PaypalValue;
		this.PagSeguroValue = PagSeguroValue;
	}
	
	public static TransacaoStatusEnum getEnum(String value) {
        for(TransacaoStatusEnum t : values()) {
            if(value.equals(t.getPagSeguroValue()) || value.equals(t.getPaypalValue()) ) {
                return t;
            }
        }
        return null;
	}

	public String getPagSeguroValue() {
		return PagSeguroValue;
	}

	public String getPaypalValue() {
		return PaypalValue;
	}
	
}
