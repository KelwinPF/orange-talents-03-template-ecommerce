package com.api.mercadolivre.configuration;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.api.mercadolivre.dto.CaracteristicaDTO;

public class CaracteristicsValidator implements ConstraintValidator<Repeated,List<CaracteristicaDTO>>{
	
	@PersistenceContext
	private EntityManager manager;

	@Override
	public boolean isValid(List<CaracteristicaDTO> value, ConstraintValidatorContext context){
		
		List<String> caracterisics = value.stream()
                .map(v_titulo -> v_titulo.getNome().toUpperCase())
                .collect(Collectors.toList());
		
		Set<String> set = new HashSet<String>(caracterisics);
		
		if(set.size() < value.size()){
		    return false;
		}
		
		return true;
	}
}
