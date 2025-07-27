package com.example.consumer;

import java.net.InetSocketAddress;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.datastax.oss.driver.api.core.CqlSession;

@SpringBootApplication
public class ConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConsumerApplication.class, args);
	}

	@Bean
    public CqlSession cassandraSession() {
        CqlSession session = CqlSession.builder()
            .addContactPoint(new InetSocketAddress("cassandra", 9042))
            .withLocalDatacenter("datacenter1")
            .build();

		//The properties file was not working correctly, so i decided to use manually override the configuration

        // // Create keyspace if not exists
        session.execute("""
            CREATE KEYSPACE IF NOT EXISTS user_activity_schema
            WITH replication = {'class': 'SimpleStrategy', 'replication_factor': 1};
        """);

        // // Use keyspace
		session.execute("USE user_activity_schema");

		session.execute("""
			DROP TABLE IF EXISTS user_activity_cart_data;
		""");
		session.execute("""
			DROP TABLE IF EXISTS user_activity_purchase_data;
		""");
		session.execute("""
			DROP TABLE IF EXISTS user_activity_click_data;
		""");
		session.execute("""
			DROP TABLE IF EXISTS user_activity_page_data;
		""");
		
        // Create user_activity_cart_data table if not exists
        session.execute("""
            CREATE TABLE IF NOT EXISTS user_activity_cart_data (
                productId INT,
                quantity INT,
                price DOUBLE,
                totalPrice DOUBLE,
                timestamp BIGINT PRIMARY KEY
            );
        """);

        // Create user_activity_purchase_data table if not exists
        session.execute("""
            CREATE TABLE IF NOT EXISTS user_activity_purchase_data (
                productId INT,
                quantity INT,
                price DOUBLE,
                totalPrice DOUBLE,
                timestamp BIGINT PRIMARY KEY
            );
        """);

        // Create user_activity_click_data table if not exists
        session.execute("""
            CREATE TABLE IF NOT EXISTS user_activity_click_data (
                productId INT,
                productName TEXT,
                timestamp BIGINT PRIMARY KEY
            );
        """);

        // Create user_activity_page_data table if not exists
        session.execute("""
            CREATE TABLE IF NOT EXISTS user_activity_page_data (
				pageNumber INT,
                duration DOUBLE,
                timestamp BIGINT PRIMARY KEY
            );
        """);

        return session;
    }

}
