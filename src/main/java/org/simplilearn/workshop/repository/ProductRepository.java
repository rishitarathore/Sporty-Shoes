package org.simplilearn.workshop.repository;

import org.simplilearn.workshop.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    void deleteById(Long id);
}