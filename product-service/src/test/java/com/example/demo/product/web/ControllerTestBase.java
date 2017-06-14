package com.example.demo.product.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

@TestPropertySource(locations = "classpath:application-controller-tests.properties")
public abstract class ControllerTestBase {

	@Autowired
	protected MockMvc mockMvc;

	
}
