package com.example.consumer.repository;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import com.example.consumer.model.UserActivityClickData;

@Repository
public interface UserActivityClickRepository extends CassandraRepository<UserActivityClickData, String> {
    // This interface can be extended with custom query methods if needed
}