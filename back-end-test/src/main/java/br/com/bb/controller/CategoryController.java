package br.com.bb.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.bb.filter.CategoryFilter;
import br.com.bb.service.CategoryService;

@RestController
@RequestMapping(value = "category")
public class CategoryController {

	private static final Logger LOGGER = LoggerFactory.getLogger(CategoryController.class);

	@Autowired
	private CategoryService service;

	/**
	 * List all categories
	 * 
	 * @return list of categories(DTO)
	 */
	@GetMapping("listAll")
	public ResponseEntity<Object> listAll() {
		LOGGER.info("Entrando em listAll");
		return new ResponseEntity<>(service.listAll(), HttpStatus.OK);
	}

	/**
	 * List categories by filter
	 * 
	 * @param filter
	 * 
	 * @return list of categories(DTO)
	 */
	@GetMapping("listByFilter")
	public ResponseEntity<Object> listByFilter(CategoryFilter categoryFilter) {
		LOGGER.info("Entrando em listByFilter, categoryFilter: {}", categoryFilter);
		return new ResponseEntity<>(service.listByFilter(categoryFilter), HttpStatus.OK);
	}
}