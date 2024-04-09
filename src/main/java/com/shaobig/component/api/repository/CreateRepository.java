package com.shaobig.component.api.repository;

public interface CreateRepository<T> {

    T create(T entity);

}
