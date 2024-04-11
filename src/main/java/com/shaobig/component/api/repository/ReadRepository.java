package com.shaobig.component.api.repository;

public interface ReadRepository<C, E> {

    E read(C criteria);

}
