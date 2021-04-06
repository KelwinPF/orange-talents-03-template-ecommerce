 package com.api.mercadolivre.dto;

import java.math.BigDecimal;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import com.api.mercadolivre.configuration.Exists;
import com.api.mercadolivre.configuration.Repeated;
import com.api.mercadolivre.entity.Produto;
import com.api.mercadolivre.repository.CategoriaRepository;
import com.api.mercadolivre.repository.UsuarioRepository;

public class ProdutoDTO {
	@NotBlank(message="insira um nome")
	private String nome;
	@NotNull
	@Positive
	private BigDecimal valor;
	@NotNull
	@Positive
	private Integer quantidade;
	@NotBlank(message="insira uma categoria")
	@Size(max=1000)
	private String descricao;
	@NotNull
	@Exists(table = "categorias")
	private Long categoria;
	@Size(min=3,message="a quantidade de caracteristicas tem que ser maior que 3")
	@Valid
	@Repeated(message="exitem caracteristicas repetidas")
	private List<CaracteristicaDTO> caracteristicas;
	
	@Deprecated
	public void Produto() {
	}
	
	public ProdutoDTO(@NotBlank(message = "insira um nome") String nome, @NotNull @Positive BigDecimal valor,
			@NotNull @Positive Integer quantidade, @Size(max = 1000) String descricao, @NotNull Long categoria,
			@Size(min=3) @Valid List<CaracteristicaDTO> caracteristicas) {
		super();
		this.nome = nome;
		this.valor = valor;
		this.quantidade = quantidade;
		this.descricao = descricao;
		this.categoria = categoria;
		this.caracteristicas = caracteristicas;
	}
	
	public List<CaracteristicaDTO> getCaracteristicas() {
		return caracteristicas;
	}

	public Produto converter(CategoriaRepository categoriaRepository, 
			UsuarioRepository usuarioRepository, Long user) {
		
		return  new Produto(descricao, valor, quantidade, descricao
				,categoriaRepository.getOne(categoria),usuarioRepository.getOne(user));
	}
	
	
}
