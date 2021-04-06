package com.api.mercadolivre.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.mercadolivre.entity.Produto;

public interface ProdutoRepository extends JpaRepository<Produto,Long>{
		
}
