package com.example.consumer.repository;

import org.springframework.data.cassandra.repository.CassandraRepository;

import com.example.consumer.model.User;

public interface UserRepository extends CassandraRepository<User, String> {
}
