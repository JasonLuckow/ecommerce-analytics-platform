package com.ecommerce.producer.controller;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.stereotype.Component;

import com.ecommerce.producer.service.KafkaProducerService;

@Component
public class UserActivityClick extends UserActivityProducerCommon {

    ArrayList<String> productList;
    KafkaProducerService kafkaProducerService;

    public UserActivityClick(KafkaProducerService kafkaProducerService) {
        // Constructor to initialize Kafka producer service if needed
        // This can be used to send events to Kafka
        this.kafkaProducerService = kafkaProducerService;
        productList = new ArrayList<>(Arrays.asList("Product1", "Product2", "Product3", "Product4", "Product5"));
    }

    @Override
    protected int getMinGenerationInterval() {
        return 30;
    }

    @Override
    protected int getMaxGenerationInterval() {
        return 45;
    }

    @Override
    protected String produceJsonActivityEvent() {
        int productId = generateRandomProduct();
        String productName = generateRandomProductName(productId);
        // Create a JSON string representing the user activity event
        long timestamp = System.currentTimeMillis();
        return String.format(
            "{\"productId\":%d,\"productName\":\"%s\",\"timestamp\":%d}",
            productId, productName, timestamp
        );
    }

    private String generateRandomProductName(int productId) {
        return productList.get(productId % productList.size()); // Returns a product name based on the product ID
    }

    @Override
    protected void sendEvent(String event) {
        kafkaProducerService.sendUserClickActivity(event);
    }

}
