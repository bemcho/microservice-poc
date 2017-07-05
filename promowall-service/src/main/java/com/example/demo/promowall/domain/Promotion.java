package com.example.demo.promowall.domain;

import static javax.persistence.GenerationType.AUTO;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Promotion {
	private Integer id;
	
	private Integer productId;
	
	private Product product;
	
	private String cardNumber;
	
	private Integer discount;
	
	private String description;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	
	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public Integer getDiscount() {
		return discount;
	}

	public void setDiscount(Integer discount) {
		this.discount = discount;
	}
	
	public BigDecimal getDiscountedPrice() {
		if(product == null){
			return null;
		}
		
		BigDecimal price = product.getPrice();
		
		BigDecimal multiplicand = BigDecimal.valueOf(100 - getDiscount()).divide(BigDecimal.valueOf(100));
		
		return price.multiply(multiplicand).setScale(2, BigDecimal.ROUND_HALF_UP);
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
