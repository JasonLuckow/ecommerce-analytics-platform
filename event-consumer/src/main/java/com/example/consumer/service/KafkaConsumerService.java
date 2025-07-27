package com.example.consumer.service;


import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.example.consumer.model.UserActivityCartData;
import com.example.consumer.model.UserActivityClickData;
import com.example.consumer.model.UserActivityPageData;
import com.example.consumer.model.UserActivityPurchaseData;
import com.example.consumer.repository.UserActivityCartRepository;
import com.example.consumer.repository.UserActivityClickRepository; 
import com.example.consumer.repository.UserActivityPageRepository;
import com.example.consumer.repository.UserActivityPurchaseRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


@Service
public class KafkaConsumerService {

    private final UserActivityPurchaseRepository userActivityPurchaseRepository;
    private final UserActivityPageRepository userActivityPageRepository;
    private final UserActivityClickRepository userActivityClickRepository;
    private final UserActivityCartRepository userActivityCartRepository;
    private final ObjectMapper objectMapper;

    public KafkaConsumerService(UserActivityPurchaseRepository userActivityPurchaseRepository,
                                UserActivityPageRepository userActivityPageRepository,
                                UserActivityClickRepository userActivityClickRepository,
                                UserActivityCartRepository userActivityCartRepository,
                                ObjectMapper objectMapper) {
        this.userActivityPurchaseRepository = userActivityPurchaseRepository;
        this.userActivityPageRepository = userActivityPageRepository;
        this.userActivityClickRepository = userActivityClickRepository;
        this.userActivityCartRepository = userActivityCartRepository;
        this.objectMapper = objectMapper;
    }

    @KafkaListener(topics = "user.activity.cart", groupId = "event-group1")
    public void consumeCart(String message) throws JsonProcessingException {
        System.out.println("Consumed Cart: " + message);
        // Here you can add logic to process the message, e.g., save it to a database
        // For example, you can use a Cassandra repository to save the data
        try {
            UserActivityCartData[] userActivityCartDataArray = objectMapper.readValue(message, UserActivityCartData[].class);
            for (UserActivityCartData userActivityCartData : userActivityCartDataArray) {
                userActivityCartRepository.save(userActivityCartData);
            }
        } catch (JsonProcessingException e) {
            System.err.println("Error processing cart message: " + e.getMessage());
        }
        
    }

    @KafkaListener(topics = "user.activity.click", groupId = "event-group2")
    public void consumeClick(String message) throws JsonProcessingException {
        System.out.println("Consumed Click: " + message);
        // Process click activity message
        // You can deserialize the message and save it to a database
        try {
            UserActivityClickData userActivityClickData = objectMapper.readValue(message, UserActivityClickData.class);
            userActivityClickRepository.save(userActivityClickData);
        } catch (JsonProcessingException e) {
            System.err.println("Error processing click message: " + e.getMessage());
        }
    }

    @KafkaListener(topics = "user.activity.purchase", groupId = "event-group3")
    public void consumePurchase(String message) throws JsonProcessingException {
        System.out.println("Consumed Purchase: " + message);
        // Process purchase activity message
        // You can deserialize the message and save it to a database
        try {
            UserActivityPurchaseData[] userActivityPurchaseDataArray = objectMapper.readValue(message, UserActivityPurchaseData[].class);
            for (UserActivityPurchaseData userActivityPurchaseData : userActivityPurchaseDataArray) {
                userActivityPurchaseRepository.save(userActivityPurchaseData);
            }
        } catch (JsonProcessingException e) {
            System.err.println("Error processing purchase message: " + e.getMessage());
        }
    }

    @KafkaListener(topics = "user.activity.page", groupId = "event-group4")
    public void consumePage(String message) throws JsonProcessingException {
        System.out.println("Consumed Page: " + message);
        // Process page activity message
        // You can deserialize the message and save it to a database
        try {
            UserActivityPageData userActivityPageData = objectMapper.readValue(message, UserActivityPageData.class);
            userActivityPageRepository.save(userActivityPageData);
        } catch (JsonProcessingException e) {
            System.err.println("Error processing page message: " + e.getMessage());
        }
    }
}
