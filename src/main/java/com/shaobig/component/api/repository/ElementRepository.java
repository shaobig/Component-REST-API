package com.shaobig.component.api.repository;

import com.mongodb.client.MongoCollection;
import com.shaobig.component.api.entities.Element;
import org.bson.Document;
import org.springframework.stereotype.Repository;

@Repository
public class ElementRepository implements CreateRepository<Element>, ReadRepository<Element> {

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
    public Element read(String name) {
        return getElementCollection().find(new Document(ELEMENT_NAME_KEY, name)).first();
    }

    public MongoCollection<Element> getElementCollection() {
        return elementCollection;
    }

}
