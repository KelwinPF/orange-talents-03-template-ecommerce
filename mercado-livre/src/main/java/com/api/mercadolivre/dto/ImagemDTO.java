package com.api.mercadolivre.dto;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.web.multipart.MultipartFile;

import com.api.mercadolivre.entity.Imagem;
import com.api.mercadolivre.entity.Produto;

public class ImagemDTO {
	
	@NotNull(message="insira uma ou mais imagens")
	@Size(min=1)
	private List<MultipartFile> files;
	
	public ImagemDTO(@Size(min=1) List<MultipartFile> files){
		this.files = files;
	}

	public List<MultipartFile> getFiles() {
		return files;
	}

	public List<Imagem> converter(List<String> adresses,Produto produto) {
		return adresses.stream().map(adress->new Imagem(adress,produto)).collect(Collectors.toList());
		
	}

	

}
