package de.rewe.demo.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import de.rewe.demo.product.domain.Product;
import de.rewe.demo.product.repository.ProductRepository;

@Component
public class DataLoader implements ApplicationRunner {

	private ProductRepository productRepo;

	@Autowired
	public DataLoader(ProductRepository productRepo) {
		this.productRepo = productRepo;
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {

		// student
		Product product = new Product();
		
		product.setSku("KAM1");
		product.setName("Beer Kamentiza");

		productRepo.save(product);
	}
}
