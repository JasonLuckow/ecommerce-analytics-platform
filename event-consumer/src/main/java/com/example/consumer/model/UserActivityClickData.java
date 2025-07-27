package com.example.consumer.model;

import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import lombok.Data;

@Data
@Table(value = "user_activity_click_data")
public class UserActivityClickData {

    private int productId;
    private String productName;
    @PrimaryKey
    private long timestamp;
    
}
