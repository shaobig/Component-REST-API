package com.shaobig.component.api.repository;

import com.mongodb.MongoCommandException;
import com.mongodb.MongoException;
import com.mongodb.MongoWriteConcernException;
import com.mongodb.MongoWriteException;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.shaobig.component.api.entities.Element;
import org.bson.conversions.Bson;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Stream;

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

    static List<Arguments> readSourceData() {
        return List.of(Arguments.of(null, Optional.empty()), Arguments.of(new Element("ELEMENT_NAME"), Optional.of(new Element("ELEMENT_NAME"))));
    }

    @ParameterizedTest
    @MethodSource(value = "readSourceData")
    void read(Element sourceElement, Optional<Element> expected) {
        String sourceName = "ELEMENT_NAME";
        FindIterable<Element> findIterable = Mockito.mock(FindIterable.class);
        Mockito.when(findIterable.first()).thenReturn(sourceElement);
        Mockito.when(elementCollection.find(Mockito.<Bson>any())).thenReturn(findIterable);

        Optional<Element> actual = elementRepository.read(sourceName);

        assertEquals(expected, actual);
    }

    static Stream<Arguments> readAllSourceData() {
        return Stream.of(Arguments.of(Spliterators.emptySpliterator(), List.of()), Arguments.of(Spliterators.spliterator(List.of(new Element("1"), new Element("2")), 0), List.of(new Element("1"), new Element("2"))));
    }

    @ParameterizedTest
    @MethodSource(value = "readAllSourceData")
    void readAll(Spliterator<Element> sourceSplitIterator, List<Element> expected) {
        FindIterable<Element> findIterable = Mockito.mock(FindIterable.class);
        Mockito.when(findIterable.spliterator()).thenReturn(sourceSplitIterator);
        Mockito.when(elementCollection.find()).thenReturn(findIterable);

        List<Element> actual = elementRepository.readAll();

        assertEquals(expected, actual);
    }

}
