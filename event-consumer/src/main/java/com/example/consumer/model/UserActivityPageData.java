package com.example.consumer.model;

import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import lombok.Data;

@Data
@Table(value = "user_activity_page_data")
public class UserActivityPageData {

    private int pageNumber;
    private double duration;
    @PrimaryKey
    private long timestamp;

}