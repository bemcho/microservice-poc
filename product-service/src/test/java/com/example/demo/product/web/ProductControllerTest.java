package com.example.demo.product.web;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
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

	private Product testProductExisting, testProductNew, testProductNewCreated;

	@Before
	public void setUp() {

		// ******
		testProductExisting = new Product();

		testProductExisting.setId(1);
		testProductExisting.setName("LOREM");
		testProductExisting.setSku("IPSUM");
		//

		// ******
		testProductNew = new Product();
		testProductNewCreated = new Product();

		testProductNew.setName("NEW");
		testProductNew.setSku("PRODUCT");

		testProductNewCreated.setId(100);
		testProductNewCreated.setName(testProductNew.getName());
		testProductNewCreated.setSku(testProductNew.getSku());
		//
	}

	@Test
	public void testProductFound() throws Exception {

		given(mockProductRepository.findOne(1)).willReturn(testProductExisting);

		//@formatter:off
		mockMvc.
			perform(get("/api/products/1").
					accept(MediaType.APPLICATION_JSON)).
					andExpect(status().isOk()).
					andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)).	
                    andExpect(jsonPath("$.id", is(1))).
                    andExpect(jsonPath("$.name", is(testProductExisting.getName()))).
                    andExpect(jsonPath("$.sku", is(testProductExisting.getSku())));
		//@formatter:on
	}

	@Test
	public void testProductNotFound() throws Exception {

		//@formatter:off
		mockMvc.
			perform(get("/api/products/1").
					accept(MediaType.APPLICATION_JSON)).
					andExpect(status().isNotFound());
		//@formatter:on
	}

	@Test
	public void testProductCreated() throws Exception {

		given(mockProductService.createProduct(Mockito.any(Product.class))).willReturn(testProductNewCreated);

		//@formatter:off
		mockMvc.perform(post("/api/products").
				contentType(MediaType.APPLICATION_JSON).
				content(json(testProductNew)))
					.andExpect(status().isCreated())
					.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andExpect(jsonPath("$.id", is(testProductNewCreated.getId())))
					.andExpect(jsonPath("$.name", is(testProductNewCreated.getName())))
					.andExpect(jsonPath("$.sku", is(testProductNewCreated.getSku())));
		//@formatter:on
		
		ArgumentCaptor<Product> productArgCaptor = ArgumentCaptor.forClass(Product.class);
		verify(mockProductService, Mockito.times(1)).createProduct(productArgCaptor.capture());
		verifyNoMoreInteractions(mockProductService);

		Product productToCreate = productArgCaptor.getValue();
		
		assertNull(productToCreate.getId());
		assertThat(productToCreate.getName(), is(testProductNewCreated.getName()));
		assertThat(productToCreate.getSku(), is(testProductNewCreated.getSku()));
	}

}
