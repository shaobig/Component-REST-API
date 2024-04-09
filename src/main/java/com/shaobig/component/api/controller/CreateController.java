package com.shaobig.component.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface CreateController<T> {

    @PostMapping(path = "/create")
    ResponseEntity<T> create(@RequestBody T entity);

}
