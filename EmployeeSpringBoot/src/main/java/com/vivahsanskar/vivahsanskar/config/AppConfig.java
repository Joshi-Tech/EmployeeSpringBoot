/*
package com.vivahsanskar.vivahsanskar.config;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    private static final Dotenv dotenv = Dotenv.load();

    @Value("${spring.data.mongodb.uri}")
    private String mongodbUri;

    public String getMongoUri() {
        return dotenv.get("MONGODB_URI", mongodbUri);
    }
}
*/
