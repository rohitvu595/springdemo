package com.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.demo.dao.Product;
import com.demo.exception.ProductException;
import com.demo.repo.ProductRepository;

@RestController
public class ProductController {

	@Autowired
	private ProductRepository productRepository;

	@GetMapping("/products")
	public List<Product> getAllProduct() {
		return productRepository.findAll();
	}

	@GetMapping("/products/{id}")
	public Product getProductById(@PathVariable String id) {
		Optional<Product> product = productRepository.findById(Long.parseLong(id));
		if (product.isEmpty())
			throw new ProductException("Id - " + id);
		return product.get();
	}

	@PostMapping("/products")
	public Product saveProduct(@RequestBody Product newProduct) {
		productRepository.save(newProduct);// add as new product
		return newProduct;
	}

	@PutMapping("/products/{id}")
	public Product updateProduct(@RequestBody Product newProduct, @PathVariable String id) {
		Optional<Product> product = productRepository.findById(Long.parseLong(id));
		if (product.isEmpty())
			productRepository.save(newProduct);// add as new product item
		else {// update
			newProduct.setId(Long.parseLong(id));
			productRepository.save(newProduct);
		}
		return newProduct;
	}

	@DeleteMapping("/products/{id}")
	public ResponseEntity<HttpStatus> deleteProduct(@PathVariable String id) {
		try {
			this.productRepository.deleteById(Long.parseLong(id));
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
