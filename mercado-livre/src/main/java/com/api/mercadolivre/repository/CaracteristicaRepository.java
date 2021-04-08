package com.api.mercadolivre.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.mercadolivre.entity.Caracteristica;

public interface CaracteristicaRepository extends JpaRepository<Caracteristica,Long>{

	List<Caracteristica> findAllByProdutoId(Long id);

}
