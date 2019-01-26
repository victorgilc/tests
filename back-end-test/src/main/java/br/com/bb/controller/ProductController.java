package br.com.bb.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.bb.service.ProductService;

@RestController
@RequestMapping(value = "product")
public class ProductController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);

	@Autowired
	private ProductService productService;

	@GetMapping("listByCategory/{categoryId}")
	public ResponseEntity<Object> listByCategory(@PathVariable(required = true, name = "categoryId") Long categoryId) {
		LOGGER.info("Entrando em listByCategory, categoryId: {}", categoryId);
		return new ResponseEntity<>(productService.listByCategory(categoryId), HttpStatus.OK);
	}
}