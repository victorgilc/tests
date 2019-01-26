package br.com.bb.mapper;

import org.springframework.stereotype.Component;

import br.com.bb.dto.ProductDTO;
import br.com.bb.entity.Product;

@Component
public class ProductMapper implements IMapper<Product, ProductDTO> {

	@Override
	public ProductDTO toDto(Product entity) {
		ProductDTO dto = new ProductDTO();
		dto.setId(entity.getId());
		dto.setName(entity.getName());
		return dto;
	}

	@Override
	public Product toEntity(ProductDTO dto) {
		Product entity = new Product();
		entity.setId(dto.getId());
		entity.setName(dto.getName());
		return entity;
	}
}