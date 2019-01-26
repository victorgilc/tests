package br.com.bb.mapper;

import org.springframework.stereotype.Component;

import br.com.bb.dto.CategoryDTO;
import br.com.bb.entity.Category;

@Component
public class CategoryMapper implements IMapper<Category, CategoryDTO> {

	@Override
	public CategoryDTO toDto(Category entity) {
		CategoryDTO dto = new CategoryDTO();
		dto.setId(entity.getId());
		dto.setName(entity.getName());
		return dto;
	}

	@Override
	public Category toEntity(CategoryDTO dto) {
		Category entity = new Category();
		entity.setId(dto.getId());
		entity.setName(dto.getName());
		return entity;
	}
}
