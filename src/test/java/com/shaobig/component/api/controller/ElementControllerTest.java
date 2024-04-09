package com.shaobig.component.api.controller;

import com.shaobig.component.api.entities.Element;
import com.shaobig.component.api.service.ElementService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ElementControllerTest {

    private ElementService elementService;


    private ElementController elementController;

    @BeforeEach
    void init() {
        this.elementService = Mockito.mock(ElementService.class);

        this.elementController = new ElementController(elementService);
    }

    @Test
    void create() {
        Element sourceElement = new Element("ELEMENT_NAME");
        Element sourceCreatedElement = new Element("CREATED_ELEMENT_NAME");
        Mockito.when(elementService.create(Mockito.any())).thenReturn(sourceCreatedElement);

        ResponseEntity<Element> actual = elementController.create(sourceElement);

        ResponseEntity<Element> expected = ResponseEntity.status(HttpStatus.CREATED)
                .body(new Element("CREATED_ELEMENT_NAME"));
        assertEquals(expected, actual);
    }

}
