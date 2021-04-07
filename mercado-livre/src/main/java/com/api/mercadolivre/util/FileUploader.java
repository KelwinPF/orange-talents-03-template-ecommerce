package com.api.mercadolivre.util;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public interface FileUploader {
	List<String> upload(List<MultipartFile> files);
}
