package com.shaobig.component.api.repository;

import com.mongodb.client.MongoCollection;
import com.shaobig.component.api.entities.Element;
import org.bson.Document;
import org.springframework.stereotype.Repository;

@Repository
public class ElementRepository  implements CreateRepository<Element> {

    private static final String ELEMENT_NAME_KEY = "name";

    private final MongoCollection<Document> elementCollection;

    public ElementRepository(MongoCollection<Document> elementCollection) {
        this.elementCollection = elementCollection;
    }

    @Override
    public Element create(Element element) {
        getElementCollection().insertOne(new Document(ELEMENT_NAME_KEY, element.getName()));
        return element;
    }

    public MongoCollection<Document> getElementCollection() {
        return elementCollection;
    }

}
