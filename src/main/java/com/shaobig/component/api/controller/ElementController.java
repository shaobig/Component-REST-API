package com.shaobig.component.api.controller;

import com.shaobig.component.api.entities.Element;
import com.shaobig.component.api.service.ElementService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/element")
public class ElementController implements CreateController<Element> {

    private final ElementService elementService;

    public ElementController(ElementService elementService) {
        this.elementService = elementService;
    }

    @Override
    public ResponseEntity<Element> create(Element element) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(getElementService().create(element));
    }

    public ElementService getElementService() {
        return elementService;
    }

}
