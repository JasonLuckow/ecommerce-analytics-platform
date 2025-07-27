package com.example.consumer.repository;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import com.example.consumer.model.UserActivityCartData;

@Repository
public interface UserActivityCartRepository extends CassandraRepository<UserActivityCartData, String> {
    // This interface can be extended with custom query methods if needed
}
