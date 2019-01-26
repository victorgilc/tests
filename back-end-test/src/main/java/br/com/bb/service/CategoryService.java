package br.com.bb.service;

import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.bb.dto.CategoryDTO;
import br.com.bb.filter.CategoryFilter;
import br.com.bb.handler.CategoryHandler;

@Service
@Transactional(readOnly = true)
public class CategoryService {

	private static final Logger LOGGER = LoggerFactory.getLogger(CategoryService.class);

	@Autowired
	private CategoryHandler handler;

	/**
	 * List all categories
	 * 
	 * @return list of categories(DTO)
	 */
	public List<CategoryDTO> listAll() {

		List<CategoryDTO> toReturn = handler.listAll();

		LOGGER.info("Categories quantity: {}", toReturn.size());

		if (toReturn.isEmpty()) {
			throw new NoSuchElementException();
		}

		return toReturn;
	}

	/**
	 * List categories by filter
	 * 
	 * @param filter
	 * 
	 * @return list of categories(DTO)
	 */
	public CategoryDTO listByFilter(CategoryFilter filter) {
		if (filter == null || StringUtils.isBlank(filter.getToFind())) {
			throw new IllegalArgumentException("Argumentos inválidos");
		}

		String toFindLowerCase = filter.getToFind().toLowerCase();

		Comparator<CategoryDTO> comparatorToFindMax = (CategoryDTO first, CategoryDTO second) -> {
			return Integer.compare(StringUtils.countMatches(first.getName().toLowerCase(), toFindLowerCase),
					StringUtils.countMatches(second.getName().toLowerCase(), toFindLowerCase));
		};

		List<CategoryDTO> categories = handler.listAllContain(toFindLowerCase);

		if (categories.isEmpty()) {
			throw new NoSuchElementException();
		}

		Optional<CategoryDTO> optMaxChar = categories.stream().max(comparatorToFindMax);

		LOGGER.info("Category found:{}", optMaxChar);

		if (optMaxChar.isPresent()) {
			return optMaxChar.get();
		}

		throw new NoSuchElementException();
	}
}