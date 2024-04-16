package com.shaobig.component.api.repository;

import com.mongodb.client.MongoCollection;
import com.shaobig.component.api.entities.Element;
import org.bson.Document;
import org.springframework.stereotype.Repository;
import org.springframework.util.StreamUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

@Repository
public class ElementRepository implements CreateRepository<Element>, ReadRepository<Element>, ReadAllRepository<Element> {

    private static final String ELEMENT_NAME_KEY = "name";

    private final MongoCollection<Element> elementCollection;

    public ElementRepository(MongoCollection<Element> elementCollection) {
        this.elementCollection = elementCollection;
    }

    @Override
    public Element create(Element element) {
        getElementCollection().insertOne(element);
        return new Element(element.getName());
    }

    @Override
    public Optional<Element> read(String name) {
        return Optional.ofNullable(getElementCollection().find(new Document(ELEMENT_NAME_KEY, name)).first());
    }

    @Override
    public List<Element> readAll() {
        return StreamSupport.stream(getElementCollection().find().spliterator(), false).toList();
    }

    public MongoCollection<Element> getElementCollection() {
        return elementCollection;
    }

}
