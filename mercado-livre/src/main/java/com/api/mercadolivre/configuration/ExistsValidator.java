package com.api.mercadolivre.configuration;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ExistsValidator implements ConstraintValidator<Exists,Long>{
	
	private String table;
	
	@PersistenceContext
	private EntityManager manager;
	
	@Override
    public void initialize(Exists exist) {
		this.table = exist.table();
	}

	@Override
	public boolean isValid(Long value, ConstraintValidatorContext context){
		Boolean exist = false;
		try {
			if(value != null) {
				exist = !manager.createNativeQuery(
					    "SELECT * FROM "+ table +" WHERE id = " + value.toString()+";")
						.getResultList().isEmpty();
			}else {
				exist = true;
			}
		}catch(Exception e) {}
		return exist;
	}
}
