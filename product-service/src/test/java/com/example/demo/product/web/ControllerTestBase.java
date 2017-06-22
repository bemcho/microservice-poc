package com.example.demo.product.web;

import java.io.IOException;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.http.MockHttpOutputMessage;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

@TestPropertySource(locations = "classpath:application-controller-tests.properties")
public abstract class ControllerTestBase {

	@Autowired
	protected MockMvc mockMvc;

	protected HttpMessageConverter<Object> mappingJackson2HttpMessageConverter;

	@SuppressWarnings("unchecked")
	@Autowired
	void setConverters(HttpMessageConverter<?>[] converters) {

		this.mappingJackson2HttpMessageConverter = (HttpMessageConverter<Object>) Arrays.asList(converters).stream()
				.filter(hmc -> hmc instanceof MappingJackson2HttpMessageConverter).findAny()
				.orElseThrow(() -> new RuntimeException("The JSON message converter must not be null!"));
	}

	protected String json(Object o) {
		MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
		try {
			this.mappingJackson2HttpMessageConverter.write(o, MediaType.APPLICATION_JSON, mockHttpOutputMessage);
		} catch (HttpMessageNotWritableException | IOException e) {
			throw new RuntimeException(e);
		}
		return mockHttpOutputMessage.getBodyAsString();
	}
}
