package com.shaobig.component.api.service;

import com.shaobig.component.api.entities.Element;
import com.shaobig.component.api.repository.ElementRepository;
import org.springframework.stereotype.Service;

@Service
public class ElementService implements CreateService<Element>, ReadService<Element> {

    private final ElementRepository elementRepository;

    public ElementService(ElementRepository elementRepository) {
        this.elementRepository = elementRepository;
    }

    @Override
    public Element create(Element element) {
        return getElementRepository().create(element);
    }

    @Override
    public Element read(Element element) {
        return getElementRepository().read(element.getName());
    }

    public ElementRepository getElementRepository() {
        return elementRepository;
    }

}
