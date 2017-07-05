package com.example.demo.promotion.web;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.promotion.domain.Promotion;
import com.example.demo.promotion.repository.PromotionRepository;

@RestController
@RequestMapping("/api/promotions")
public class PromotionController {
	
	@Autowired
	private PromotionRepository promotionRepository;
	
	@GetMapping("")
	public Collection<Promotion> getCommonPromotions(){
		return promotionRepository.findByCardNumber(null);
	}
	
	@GetMapping("/{cardNumber}")
	public Collection<Promotion> getPersonalPromotions(@PathVariable String cardNumber){
		return promotionRepository.findByCardNumber(cardNumber);
	}
}
