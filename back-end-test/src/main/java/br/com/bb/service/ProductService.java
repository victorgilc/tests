package br.com.bb.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.bb.dto.ProductDTO;
import br.com.bb.handler.ProductHandler;

@Service
@Transactional(readOnly = true)
public class ProductService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProductService.class);

	@Autowired
	private ProductHandler handler;

	/**
	 * Find a product list by their respective id
	 * 
	 * @param categoryId
	 * 
	 * @return product list
	 * 
	 */
	public List<ProductDTO> listByCategory(Long categoryId) {
		List<ProductDTO> toReturn = handler.listByCategoryId(categoryId);

		LOGGER.info("Founded {} products for category {}", toReturn.size(), categoryId);

		if (toReturn.isEmpty()) {
			throw new NoSuchElementException();
		}

		return toReturn;
	}
}
