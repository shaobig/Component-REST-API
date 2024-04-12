package com.shaobig.component.api.entities;

import java.util.Objects;

public class Element {

    private String name;

    protected Element() {}

    public Element(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Element element = (Element) o;
        return name.equals(element.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

}
