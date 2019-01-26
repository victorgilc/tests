package br.com.bb.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.bb.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

	/**
	 * Busca uma listagem de produtos pela respectiva categoria(id)
	 * 
	 * @param categoryId
	 * @return Uma collection com os produtos relacionados
	 */
	Collection<Product> findAllByCategoryId(Long categoryId);
}