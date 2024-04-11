package com.shaobig.component.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface ReadController<E> {

    @GetMapping(path = "/read")
    ResponseEntity<E> read(@RequestBody E entity);

}
