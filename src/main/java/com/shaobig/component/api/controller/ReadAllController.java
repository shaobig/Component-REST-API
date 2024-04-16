package com.shaobig.component.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

public interface ReadAllController<E> {

    @GetMapping(path = "/read-all")
    ResponseEntity<List<E>> readAll();

}
