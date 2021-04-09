package com.api.mercadolivre.configuration;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.api.mercadolivre.dto.CompraDTO;

public class EmEstoqueValidator implements ConstraintValidator<EmEstoque,CompraDTO>{
	
	@PersistenceContext
	private EntityManager manager;
	
	@Override
    public void initialize(EmEstoque is) {
	}

	@Override
	public boolean isValid(CompraDTO dto, ConstraintValidatorContext context){
		Integer is_empty = 0;
		
		is_empty = manager.createNativeQuery(
				" SELECT * FROM produtos WHERE id = "+ dto.getProduto().toString()
				+" and quantidade >= "+dto.getQuantidade().toString()+";").getResultList().size();
			
		
		System.out.println(is_empty);
		return is_empty == 1;
	}

}
