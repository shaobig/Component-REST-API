package com.shaobig.component.api.service;

import com.shaobig.component.api.entities.Element;
import com.shaobig.component.api.repository.ElementRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ElementServiceTest {

    private ElementRepository elementRepository;

    private ElementService elementService;

    @BeforeEach
    void init() {
        this.elementRepository = Mockito.mock(ElementRepository.class);

        this.elementService = new ElementService(elementRepository);
    }

    @Test
    void create() {
        Element sourceElement = new Element("ELEMENT_NAME");
        Element sourceRepositoryElement = new Element("REPOSITORY_ELEMENT_NAME");
        Mockito.when(elementRepository.create(Mockito.any())).thenReturn(sourceRepositoryElement);

        Element actual = elementService.create(sourceElement);

        Element expected = new Element("REPOSITORY_ELEMENT_NAME");
        assertEquals(expected, actual);
    }

}
