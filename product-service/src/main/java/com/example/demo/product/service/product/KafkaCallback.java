package com.example.demo.product.service.product;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFutureCallback;

import com.example.demo.product.domain.Product;

/**
 * A call-back that should handle the result from the product publication in Kafka.
 */
class KafkaCallback implements ListenableFutureCallback<SendResult<Integer, Product>>
{
	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	
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

