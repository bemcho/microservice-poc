package com.example.demo.product.web;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.product.domain.Product;
import com.example.demo.product.repository.ProductRepository;
import com.example.demo.product.service.product.ProductService;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = ProductController.class, secure = false)
public class ProductControllerTest extends ControllerTestBase {
	
	@MockBean
	private ProductRepository mockProductRepository;

	@MockBean
	private ProductService mockProductService;

	private Product testProductExisting;

	@Before
	public void setUp() {

		testProductExisting = new Product();

		testProductExisting.setId(1);
		testProductExisting.setName("LOREM");
		testProductExisting.setSku("IPSUM");
	}

	@Test
	public void testProductFound() throws Exception {

		given(mockProductRepository.findOne(1)).willReturn(testProductExisting);

		mockMvc.
			perform(get("/api/products/1").
					accept(MediaType.APPLICATION_JSON)).
					andExpect(status().isOk()).
					andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)).	
                    andExpect(jsonPath("$.id", is(1))).
                    andExpect(jsonPath("$.name", is(testProductExisting.getName()))).
                    andExpect(jsonPath("$.sku", is(testProductExisting.getSku())));
        
	}	
}
