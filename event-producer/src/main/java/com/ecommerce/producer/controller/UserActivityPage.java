package com.ecommerce.producer.controller;

import org.springframework.stereotype.Component;

import com.ecommerce.producer.service.KafkaProducerService;

@Component
public class UserActivityPage extends UserActivityProducerCommon {

    private static final int MAX_PAGES = 7;
    KafkaProducerService kafkaProducerService;

    public UserActivityPage(KafkaProducerService kafkaProducerService) {
        // Constructor to initialize Kafka producer service if needed
        // This can be used to send events to Kafka
        this.kafkaProducerService = kafkaProducerService;
    }

    @Override
    protected int getMinGenerationInterval() {
        return 15; // Minimum generation interval in seconds
    }

    @Override
    protected int getMaxGenerationInterval() {
        return 30; // Maximum generation interval in seconds
    }

    @Override
    protected String produceJsonActivityEvent() {
        int pageNumber = generateRandomePage();
        double pageDuration = generatePageDuration();   
        // Create a JSON string representing the user activity event
        long timestamp = System.currentTimeMillis();
        return String.format("{\"pageNumber\":%d,\"duration\":%.2f,\"timestamp\":%d}", pageNumber, pageDuration, timestamp);
    }

    private int generateRandomePage() {
        return (int) (Math.random() * MAX_PAGES) + 1; // Generates a random page number between 1 and MAX_PAGES
    }

    private double generatePageDuration() {
        return Math.random() * 10 + 1; // Generates a random page duration between 1 and 10 seconds
    }

    @Override
    protected void sendEvent(String event) {
        kafkaProducerService.sendUserPageActivity(event);
    }

    

    

}
