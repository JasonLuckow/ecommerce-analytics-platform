package com.ecommerce.producer.controller;

import org.springframework.stereotype.Component;

import com.ecommerce.producer.service.KafkaProducerService;

@Component
public class UserActivityCart extends UserActivityProducerCommon {

    KafkaProducerService kafkaProducerService;

    public UserActivityCart(KafkaProducerService kafkaProducerService) {
        // Constructor to initialize Kafka producer service if needed
        // This can be used to send events to Kafka
        this.kafkaProducerService = kafkaProducerService;
    }

    @Override
    protected int getMinGenerationInterval() {
        return 60;
    }

    @Override
    protected int getMaxGenerationInterval() {
        return 90;
    }

    @Override
    protected String produceJsonActivityEvent() {
        return produceTransactionalJsonActivityEvent();
    }

    @Override
    protected void sendEvent(String event) {
        kafkaProducerService.sendUserCartActivity(event);
    }

    

}
