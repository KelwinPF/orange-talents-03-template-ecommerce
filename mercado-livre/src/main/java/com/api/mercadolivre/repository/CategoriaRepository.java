package com.api.mercadolivre.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.mercadolivre.entity.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria,Long>{

}
