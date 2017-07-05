package com.example.demo.promowallui.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.example.demo.promowallui.domain.Product;
import com.example.demo.promowallui.domain.Promotion;


@Controller
public class PromoWallController {

	private static final String PROMOTIONS_ENDPOINT = "http://localhost:8080/api/promowall/";
	@Autowired
	private RestTemplate restTemplate;

	@RequestMapping(method = RequestMethod.GET, path = "/")
	public String index(Model model) {
		model.addAttribute("commonPromotions", getCommonPromotions());
		return "index";
	}
	
	@RequestMapping(method = RequestMethod.GET, path = "/personal-promotions/{code}")
	public @ResponseBody Collection<Promotion> getPersonalPromotion(@PathVariable String code) {
		return getPersonalPromotions(code);
	}

	public Collection<Promotion> getCommonPromotions() {
		ResponseEntity<Collection<Promotion>> promotionEntities = restTemplate.exchange(PROMOTIONS_ENDPOINT,
				HttpMethod.GET, null, new ParameterizedTypeReference<Collection<Promotion>>() {
				});
		Collection<Promotion> promotions = promotionEntities.getBody();
		return promotions;
	}

	public Collection<Promotion> getPersonalPromotions(String cardNumber) {
		ResponseEntity<Collection<Promotion>> promotionEntities = restTemplate.exchange(
				PROMOTIONS_ENDPOINT + "/" + cardNumber, HttpMethod.GET, null,
				new ParameterizedTypeReference<Collection<Promotion>>() {
				});

		Collection<Promotion> promotions = promotionEntities.getBody();
		return promotions;
	}
}
