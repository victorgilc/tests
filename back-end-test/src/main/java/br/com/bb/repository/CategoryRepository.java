package br.com.bb.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.bb.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

	@Query("Select c from Category c where LOWER(c.name) like %:toFind%")
	Collection<Category> findCategoryContain(@Param("toFind") String toFind);
}