package com.api.mercadolivre.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.mercadolivre.entity.Opiniao;

public interface OpiniaoRepository extends JpaRepository<Opiniao,Long>{

	List<Opiniao> findAllByProdutoId(Long id);
	
}
