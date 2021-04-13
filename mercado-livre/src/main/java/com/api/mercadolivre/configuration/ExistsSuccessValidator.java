package com.api.mercadolivre.configuration;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.api.mercadolivre.dto.TransacaoDTO;

public class ExistsSuccessValidator implements ConstraintValidator<ExistsSuccess,TransacaoDTO>{
	
	@PersistenceContext
	private EntityManager manager;
	
	@Override
    public void initialize(ExistsSuccess exist) {
	}

	@Override
	public boolean isValid(TransacaoDTO value, ConstraintValidatorContext context){
		
		if(value.getStatus().equals("ERRO") || value.getStatus().equals("0")) {
			return true;
		}
		
		try {
				Boolean exist = !manager.createNativeQuery(
					    "SELECT * FROM transacoes as t WHERE t.id_transacao like '" + value.getId_transacao()+"'"
					    		+ " AND t.status like 'SUCESSO' ;")
						.getResultList().isEmpty();
				if(exist) {
					return false;
				}
		}catch(Exception e) {}
		return true;
	}
}
