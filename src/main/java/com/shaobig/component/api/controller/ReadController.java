package com.shaobig.component.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public interface ReadController<E> {

    @GetMapping(path = "/read/{name}")
    ResponseEntity<E> read(@PathVariable String name);

}
