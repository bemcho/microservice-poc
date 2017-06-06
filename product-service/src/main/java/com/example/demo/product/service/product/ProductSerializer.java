package com.example.demo.product.service.product;

import java.util.Map;
import java.util.Objects;

import org.apache.kafka.common.serialization.Serializer;

import com.example.demo.product.domain.Product;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Charsets;

/**
 * Serializes a product to an appropriate JSON representation.
 */
public class ProductSerializer implements Serializer<Product> {

	@Override
	public void configure(Map<String, ?> configs, boolean isKey) {
		// nothing
	}

	@Override
	public byte[] serialize(String topic, Product product) {
		// Definitely not sure if this is correct but the serializers
		// are meant to be stateless and used in Thread XYZ where spring beans
		// may not be available thus we can't grab the spring configured object mapper...
		// This should be considered in production environment.
		// Maybe use the configuration and push inside 	MappingJackson2HttpMessageConverter???
		Objects.requireNonNull(product, "Product can't be null!");
		
		ObjectMapper mapper = new ObjectMapper();
		
		String serialized;
		try {
			serialized = mapper.writeValueAsString(product);
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}
		
		return serialized.getBytes(Charsets.UTF_8);
	}

	@Override
	public void close() {
		// nothing
	}

}
