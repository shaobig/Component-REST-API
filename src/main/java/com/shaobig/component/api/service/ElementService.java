package com.shaobig.component.api.service;

import com.shaobig.component.api.entities.Element;
import com.shaobig.component.api.repository.ElementRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ElementService implements CreateService<Element>, ReadService<Element>, ReadAllService<Element> {

    private final ElementRepository elementRepository;

    public ElementService(ElementRepository elementRepository) {
        this.elementRepository = elementRepository;
    }

    @Override
    public Element create(Element element) {
        return getElementRepository().create(element);
    }

    @Override
    public Element read(String name) {
        return getElementRepository().read(name).orElseThrow(() -> new RuntimeException(String.format("Can't find element '%s'", name)));
    }

    @Override
    public List<Element> readAll() {
        return getElementRepository().readAll();
    }

    public ElementRepository getElementRepository() {
        return elementRepository;
    }

}
