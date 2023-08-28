package com.demo.controller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.demo.dao.ProductImage;
import com.demo.repo.ProductImageRepository;

@RestController
public class ProductImageController {
	
	private static String UPLOAD_FOLDER = "D://storage//";

	@Autowired
	private ProductImageRepository productImageRepository;

	@PostMapping("/productImage/{id}")
	public ResponseEntity<String> uploadProductImage(@RequestParam("file") MultipartFile file,
			@PathVariable String id) {
		try {
			byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOAD_FOLDER + file.getOriginalFilename());
            Files.write(path, bytes);
			ProductImage image = new ProductImage();
			image.setProductId(Long.parseLong(id));
			image.setName(file.getOriginalFilename());
			System.out.println("===>"+path.toFile().getAbsolutePath().toString());
			image.setData(path.toFile().getAbsolutePath().toString());
			productImageRepository.save(image);
			return ResponseEntity.status(HttpStatus.CREATED).body("Product image uploaded successfully");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload images");
		}

	}
}
