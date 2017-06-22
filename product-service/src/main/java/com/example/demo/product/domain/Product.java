package com.example.demo.product.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * An entity representing a product and its related properties.
 */
@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Product {

	private Integer id;
	@NotEmpty
	private String sku;
	private String name;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Integer getId() {
		return id;
	}

	/**
	 * The ID of the product.
	 * 
	 * @param id the if of the product.
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * Returns the unique stock-keeping unit of the product.
	 * 
	 * @return the unique stock-keeping unit of the product.
	 */
	public String getSku() {
		return sku;
	}

	/**
	 * Sets the unique stock keeping unit of this product.
	 * 
	 * @param sku the stock keeping unit of this product.
	 */
	public void setSku(String sku) {
		this.sku = sku;
	}

	/**
	 * Returns the name of this product.
	 * 
	 * @return the name of this product.
	 */
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
