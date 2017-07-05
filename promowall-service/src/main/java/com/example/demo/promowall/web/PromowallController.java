package com.example.demo.promowall.web;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.demo.promowall.domain.Product;
import com.example.demo.promowall.domain.Promotion;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
@RequestMapping("/api/promowall")
public class PromowallController {
	
	private static final String PROMOTIONS_ENDPOINT = "http://promotion-service/api/promotions";
	
	@Autowired
	private RestTemplate restTemplate;
	
	@GetMapping("")
	@HystrixCommand
	public Collection<Promotion> getCommonPromotions(){
		ResponseEntity<Collection<Promotion>> promotionEntities = restTemplate.exchange(PROMOTIONS_ENDPOINT,
				HttpMethod.GET,
				null,
				new ParameterizedTypeReference<Collection<Promotion>>() {});
		
		Collection<Promotion> promotions = promotionEntities.getBody();
		getProducts(promotions);
		return promotions;
	}
	
	@GetMapping("/{cardNumber}")
	@HystrixCommand
	public Collection<Promotion> getPersonalPromotions(@PathVariable String cardNumber){
		ResponseEntity<Collection<Promotion>> promotionEntities = restTemplate.exchange(PROMOTIONS_ENDPOINT + "/" + cardNumber,
				HttpMethod.GET,
				null,
				new ParameterizedTypeReference<Collection<Promotion>>() {});
		
		Collection<Promotion> promotions = promotionEntities.getBody();
		getProducts(promotions);
		return promotions;
	}
	
	private void getProducts(Collection<Promotion> promotions){
		promotions.forEach(this::getProduct);
	}
	
	private void getProduct(Promotion promotion){
		Product product = restTemplate.getForObject(
				"http://product-service/api/products/" + promotion.getProductId(),
				Product.class);
		promotion.setProduct(product);
	}
}
