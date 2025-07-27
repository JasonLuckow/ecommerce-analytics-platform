package com.ecommerce.producer.controller;

import org.springframework.stereotype.Component;

import com.ecommerce.producer.service.KafkaProducerService;

@Component
public class UserActivityPurchase extends UserActivityProducerCommon {

    KafkaProducerService kafkaProducerService;

    public UserActivityPurchase(KafkaProducerService kafkaProducerService) {
        // Constructor to initialize Kafka producer service if needed
        // This can be used to send events to Kafka
        this.kafkaProducerService = kafkaProducerService;
    }

    @Override
    protected int getMinGenerationInterval() {
        return 120;
    }

    @Override
    protected int getMaxGenerationInterval() {
        return 180;
    }

    @Override
    protected String produceJsonActivityEvent() {
        return produceTransactionalJsonActivityEvent();
    }

    @Override
    protected void sendEvent(String event) {
        kafkaProducerService.sendUserPurchaseActivity(event);
    }

    

}
