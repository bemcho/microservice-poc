package com.example.demo.promotion.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.promotion.domain.Promotion;

@Repository
public interface PromotionRepository extends JpaRepository<Promotion, Integer>{
	Collection<Promotion> findByCardNumber(String cardNumber);
}
