package com.example.demo.product.service.product;

import com.example.demo.product.domain.Product;

/**
 * Describes a product service. The service is
 * responsible for performing various operations with products,
 * sending the necessary events, etc.
 */
public interface ProductService {
	
	/**
	 * Creates a product.
	 * 
	 * @param product description of the product that needs to be created.
	 * 
	 * @return the newly created product.
	 */
	public Product createProduct(Product product);
}
