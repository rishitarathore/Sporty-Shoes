package org.simplilearn.workshop.repository;

import org.simplilearn.workshop.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
	
	void deleteById(Long id);
}