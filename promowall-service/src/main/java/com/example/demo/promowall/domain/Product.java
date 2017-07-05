package com.example.demo.promowall.domain;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * An entity representing a product and its related properties.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Product {

	private Integer id;
	private String name;
	@NotNull
	private BigDecimal price;
	private String url;
	
	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

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
