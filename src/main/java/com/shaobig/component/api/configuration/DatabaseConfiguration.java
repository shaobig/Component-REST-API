package com.shaobig.component.api.configuration;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.shaobig.component.api.entities.Element;
import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DatabaseConfiguration {

    private static final String CONNECTION_STRING = "mongodb://admin:password@localhost:27017/";
    private static final String DATABASE_NAME = "component-rest-api";
    private static final String ELEMENT_COLLECTION_NAME = "elements";

    @Bean
    public CodecProvider pojoCoderProvider() {
        return PojoCodecProvider.builder()
                .register(Element.class)
                .build();
    }

    @Bean
    public CodecRegistry codecRegistry(CodecProvider pojoCodecProvider) {
        return CodecRegistries.fromRegistries(CodecRegistries.fromProviders(pojoCodecProvider), MongoClientSettings.getDefaultCodecRegistry());
    }

    @Bean
    public MongoClientSettings mongoClientSettings(CodecRegistry codecRegistry) {
        return MongoClientSettings.builder()
                .applyConnectionString(new ConnectionString(CONNECTION_STRING))
                .codecRegistry(codecRegistry)
                .build();
    }

    @Bean
    public MongoClient mongoClient(MongoClientSettings mongoClientSettings) {
        return MongoClients.create(mongoClientSettings);
    }

    @Bean
    public MongoDatabase mongoDatabase(MongoClient mongoClient) {
        return mongoClient.getDatabase(DATABASE_NAME);
    }

    @Bean
    public MongoCollection<Element> elementCollection(MongoDatabase mongoDatabase) {
        return mongoDatabase.getCollection(ELEMENT_COLLECTION_NAME, Element.class);
    }

}
