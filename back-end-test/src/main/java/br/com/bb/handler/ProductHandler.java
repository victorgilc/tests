package br.com.bb.handler;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.bb.dto.ProductDTO;
import br.com.bb.mapper.ProductMapper;
import br.com.bb.repository.ProductRepository;

@Component
public class ProductHandler {

	@Autowired
	private ProductRepository repository;

	@Autowired
	private ProductMapper mapper;

	/**
	 * Find a product list by their respective id
	 * 
	 * @param categoryId
	 * 
	 * @return product list
	 */
	public List<ProductDTO> listByCategoryId(Long categoryId) {
		return repository.findAllByCategoryId(categoryId).stream().map(mapper::toDto).collect(Collectors.toList());
	}
}
