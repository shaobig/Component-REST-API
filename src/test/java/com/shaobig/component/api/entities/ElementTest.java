package com.shaobig.component.api.entities;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ElementTest {

    @Test
    void getName() {
        Element sourceElement = new Element("ELEMENT_NAME");

        String expectedName = "ELEMENT_NAME";
        Assertions.assertEquals(sourceElement.getName(), expectedName);
    }

}
