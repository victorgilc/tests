package br.com.bb.handler;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.bb.dto.CategoryDTO;
import br.com.bb.mapper.CategoryMapper;
import br.com.bb.repository.CategoryRepository;

@Component
public class CategoryHandler {

	@Autowired
	private CategoryRepository repository;

	@Autowired
	private CategoryMapper mapper;

	/**
	 * List all categories
	 * 
	 * @return DTO
	 */
	public List<CategoryDTO> listAll() {
		return repository.findAll().stream().map(mapper::toDto).collect(Collectors.toList());
	}

	/**
	 * List all categories that contains the text
	 * 
	 * @return DTO
	 */
	public List<CategoryDTO> listAllContain(String toFind) {
		return repository.findCategoryContain(toFind).stream().map(mapper::toDto).collect(Collectors.toList());
	}
}