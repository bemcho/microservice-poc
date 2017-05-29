package com.example.demo.product.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.product.domain.Product;

/**
 * A repository enabling CRUD operations on products.
 */
public interface ProductRepository extends CrudRepository<Product, Integer> {
	
}
