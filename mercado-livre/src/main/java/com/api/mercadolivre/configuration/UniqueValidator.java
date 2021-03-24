package com.api.mercadolivre.configuration;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueValidator implements ConstraintValidator<Unique,String>{
	
	private String table;
	private String column;
	
	@PersistenceContext
	private EntityManager manager;
	
	@Override
    public void initialize(Unique unique) {
		this.column = unique.column();
		this.table = unique.table();
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context){
		Boolean is_empty = false;
		try {
			is_empty = manager.createNativeQuery(
				    "SELECT * FROM "+table+" WHERE UPPER("+column+") LIKE '"+ value.toUpperCase() +"' ")
					.getResultList().isEmpty();
		}catch(Exception e) {}
		return is_empty;
	}

}
