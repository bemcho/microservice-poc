package com.example.demo.product.service.product;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import com.example.demo.product.domain.Product;
import com.example.demo.product.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private KafkaTemplate<Integer, Product> kafkaProductTemplate;

	@Override
	public Product createProduct(Product product) {

		Product newProduct = productRepository.save(product);
		
		ListenableFuture<SendResult<Integer, Product>> res = 
				kafkaProductTemplate.send("product", Integer.valueOf(product.getId()), product);
		
		res.addCallback(new KafkaCallback(newProduct.getId()));
		
		return newProduct;
	}
	
	private class KafkaCallback implements ListenableFutureCallback<SendResult<Integer, Product>>
	{
		private final Integer productId;
		
		KafkaCallback(Integer productId)
		{
			this.productId = productId;
		}
		
		@Override
		public void onSuccess(SendResult<Integer, Product> result) {
			
			ProducerRecord<Integer, Product> producerRecord = result.getProducerRecord();
			
			LOGGER.debug(
					"Successfully serialized product with id {} to topic {} and partition {}.",
						producerRecord.key(),
						producerRecord.topic(),
						producerRecord.partition()
					);
		}

		@Override
		public void onFailure(Throwable ex) {
			LOGGER.error("Failed to serialize the product {}. ", productId, ex);
		}			
	}

}



