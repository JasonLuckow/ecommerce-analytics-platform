package com.ecommerce.producer.service;


import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {

    // This class will contain methods to produce events to Kafka
    // For example, it could have a method like:
    // public void sendEvent(UserEvent event) {
    //     // Logic to send the event to Kafka topic
    // }
    
    // Additional methods and logic can be added here as needed

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final String userCartActivityTopic = "user.activity.cart";
    private final String userClickActivityTopic = "user.activity.click";
    private final String userPurchaseActivityTopic = "user.activity.purchase";
    private final String userPageActivityTopic = "user.activity.page";

    public KafkaProducerService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendUserCartActivity(String message) {
        kafkaTemplate.send(userCartActivityTopic, message);
        System.out.println("Topic: " + userCartActivityTopic + " Produced: " + message);
    }

    public void sendUserClickActivity(String message) {
        kafkaTemplate.send(userClickActivityTopic, message);
        System.out.println("Topic: " + userClickActivityTopic + " Produced: " + message);
    }

    public void sendUserPurchaseActivity(String message) {
        kafkaTemplate.send(userPurchaseActivityTopic, message);
        System.out.println("Topic: " + userPurchaseActivityTopic + " Produced: " + message);
    }

    public void sendUserPageActivity(String message) {
        kafkaTemplate.send(userPageActivityTopic, message);
        System.out.println("Topic: " + userPageActivityTopic + " Produced: " + message);
    }
}