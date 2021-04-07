package com.api.mercadolivre.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
@Profile("dev")
public class FileUploaderDev implements FileUploader{
	
	private static String UPLOADED_FOLDER = System.getProperty("user.dir")+"\\src\\images\\";
	private UUID uuid;
	private String adress = null;
	
	@Override
	public List<String> upload(List<MultipartFile> files) {
		System.out.println(files.size());
 		return files.stream().map(file->{
			try {
				adress = null;
				uuid = UUID.randomUUID(); 
				if(file.getContentType().equals("image/jpeg")) {
					adress = UPLOADED_FOLDER + uuid.toString()+".jpg";
					Files.write(Paths.get(adress),file.getBytes());
					return  "\\src\\images\\"+ uuid.toString()+".jpg";
				}else if(file.getContentType().equals("image/png")) {
					adress = UPLOADED_FOLDER + uuid.toString()+".png";
					Files.write(Paths.get(adress),file.getBytes());
					return  "\\src\\images\\"+ uuid.toString()+".png";
				}else {
					return null;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			return adress;
		}).collect(Collectors.toList());
	}
}
 