package com.example.demo.product;

import java.math.BigDecimal;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.example.demo.product.domain.Product;
import com.example.demo.product.repository.ProductRepository;

@Component
public class DataLoader implements ApplicationRunner {

	private ProductRepository productRepo;

	@Autowired
	public DataLoader(ProductRepository productRepo) {
		this.productRepo = productRepo;
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		// products P1-P7
		Product p1 = new Product();

		p1.setId(1);
		p1.setName("REWE Beste Wahl Bananen");
		p1.setPrice(new BigDecimal(2.57));
		p1.setUrl("banana.jpg");

		// P2
		Product p2 = new Product();

		p2.setId(2);
		p2.setName("Salatgurke");
		p2.setPrice(new BigDecimal(1.23));
		p2.setUrl("gurke.jpg");

		Product p3 = new Product();

		p3.setId(3);
		p3.setName("REWE Beste Wahl Cherry Romatomaten 250g");
		p3.setPrice(new BigDecimal(2.80));
		p3.setUrl("cherry.jpg");

		Product p4 = new Product();

		p4.setId(4);
		p4.setName("Apfel Braeburn rot");
		p4.setPrice(new BigDecimal(1.33));
		p4.setUrl("apfel.jpg");

		Product p5 = new Product();

		p5.setId(5);
		p5.setName("Zucchini gruen");
		p5.setPrice(new BigDecimal(1.55));
		p5.setUrl("zucchini.jpg");

		Product p6 = new Product();

		p6.setId(6);
		p6.setName("Kiwi");
		p6.setPrice(new BigDecimal(3.57));
		p6.setUrl("kiwi.jpg");

		Product p7 = new Product();

		p7.setId(7);
		p7.setName("Van Nahmen Williams Christ Birnensaft 0,75l");
		p7.setPrice(new BigDecimal(6.12));
		p7.setUrl("birnensaft.jpg");

		productRepo.save(Arrays.asList(p1, p2, p3, p4, p5, p6, p7));
	}
}
