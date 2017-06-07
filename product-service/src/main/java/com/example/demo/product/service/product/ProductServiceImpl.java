package com.example.demo.product.service.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

import com.example.demo.product.domain.Product;
import com.example.demo.product.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private KafkaTemplate<Integer, Product> kafkaProductTemplate;

	@Override
	public Product createProduct(Product product) {

		Product newProduct = productRepository.save(product);
		
		ListenableFuture<SendResult<Integer, Product>> res = 
				kafkaProductTemplate.send("product", 0, product.getId(), product);
		
		res.addCallback(new KafkaCallback(newProduct.getId()));
		
		return newProduct;
	}
}