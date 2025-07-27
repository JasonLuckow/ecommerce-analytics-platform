package com.example.consumer.repository;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import com.example.consumer.model.UserActivityPurchaseData;

@Repository
public interface UserActivityPurchaseRepository extends CassandraRepository<UserActivityPurchaseData, String> {
    // This interface can be extended with custom query methods if needed
}