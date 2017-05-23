package de.rewe.demo.product.repository;

import org.springframework.data.repository.CrudRepository;

import de.rewe.demo.product.domain.Product;

/**
 * A repository enabling CRUD operations on products.
 */
public interface ProductRepository extends CrudRepository<Product, Integer> {
	
}
