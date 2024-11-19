/*
package com.vivahsanskar.vivahsanskar.config;

import com.mongodb.ConnectionString;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;

@Configuration
public class MongoConfig {

    @Bean
    public SimpleMongoClientDatabaseFactory mongoDatabaseFactory() {
        // Load the MongoDB URI from the .env file
        Dotenv dotenv = Dotenv.configure().load();
        String mongoUri = dotenv.get("MONGODB_URI");

        // Create a MongoClient using the connection string
        MongoClient mongoClient = MongoClients.create(new ConnectionString(mongoUri));

        // Return a SimpleMongoClientDatabaseFactory using the MongoClient
        return new SimpleMongoClientDatabaseFactory(mongoClient, new ConnectionString(mongoUri).getDatabase());
    }

    @Bean
    public MongoTemplate mongoTemplate() {
        return new MongoTemplate(mongoDatabaseFactory());
    }
}
*/
