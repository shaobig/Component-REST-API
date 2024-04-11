package com.shaobig.component.api.repository;

import com.mongodb.client.MongoCollection;
import com.shaobig.component.api.entities.Element;
import org.bson.Document;
import org.springframework.stereotype.Repository;

@Repository
public class ElementRepository implements CreateRepository<Element>, ReadRepository<Element> {

    private static final String ELEMENT_NAME_KEY = "name";

    private final MongoCollection<Document> elementCollection;

    public ElementRepository(MongoCollection<Document> elementCollection) {
        this.elementCollection = elementCollection;
    }

    @Override
    public Element create(Element element) {
        getElementCollection().insertOne(new Document(ELEMENT_NAME_KEY, element.getName()));
        return new Element(element.getName());
    }

    @Override
    public Element read(String name) {
        Document document = getElementCollection().find(new Document(ELEMENT_NAME_KEY, name)).first();
        return new Element(document.getString(ELEMENT_NAME_KEY));
    }

    public MongoCollection<Document> getElementCollection() {
        return elementCollection;
    }

}
