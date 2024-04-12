package com.shaobig.component.api.repository;

import com.mongodb.MongoCommandException;
import com.mongodb.MongoException;
import com.mongodb.MongoWriteConcernException;
import com.mongodb.MongoWriteException;
import com.mongodb.client.MongoCollection;
import com.shaobig.component.api.entities.Element;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ElementRepositoryTest {

    private MongoCollection<Element> elementCollection;

    private ElementRepository elementRepository;

    @BeforeEach
    void init() {
        this.elementCollection = Mockito.mock(MongoCollection.class);

        this.elementRepository = new ElementRepository(elementCollection);
    }

    @Test
    void createMongoWriteException() {
        Element sourceElement = new Element("ELEMENT_NAME");
        Mockito.when(elementCollection.insertOne(Mockito.any())).thenThrow(MongoWriteException.class);

        Assertions.assertThrows(MongoWriteException.class, () -> elementRepository.create(sourceElement));
    }

    @Test
    void createMongoWriteConcernException() {
        Element sourceElement = new Element("ELEMENT_NAME");
        Mockito.when(elementCollection.insertOne(Mockito.any())).thenThrow(MongoWriteConcernException.class);

        Assertions.assertThrows(MongoWriteConcernException.class, () -> elementRepository.create(sourceElement));
    }

    @Test
    void createMongoCommandException() {
        Element sourceElement = new Element("ELEMENT_NAME");
        Mockito.when(elementCollection.insertOne(Mockito.any())).thenThrow(MongoCommandException.class);

        Assertions.assertThrows(MongoCommandException.class, () -> elementRepository.create(sourceElement));
    }

    @Test
    void createMongoException() {
        Element sourceElement = new Element("ELEMENT_NAME");
        Mockito.when(elementCollection.insertOne(Mockito.any())).thenThrow(MongoException.class);

        Assertions.assertThrows(MongoException.class, () -> elementRepository.create(sourceElement));
    }

    @Test
    void create() {
        Element sourceElement = new Element("ELEMENT_NAME");

        Element actual = elementRepository.create(sourceElement);

        Element expected = new Element("ELEMENT_NAME");
        assertEquals(expected, actual);
    }

}
