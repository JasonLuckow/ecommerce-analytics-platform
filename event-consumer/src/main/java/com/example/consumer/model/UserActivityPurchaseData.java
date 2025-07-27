package com.example.consumer.model;

import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import lombok.Data;

@Data
@Table(value = "user_activity_purchase_data")
public class UserActivityPurchaseData {

    
    private int productId;
    private int quantity;
    private double price;
    private double totalPrice;
    @PrimaryKey
    private long timestamp;
}