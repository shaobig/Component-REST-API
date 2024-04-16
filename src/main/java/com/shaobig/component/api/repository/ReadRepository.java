package com.shaobig.component.api.repository;

import java.util.Optional;

public interface ReadRepository<E> {

    Optional<E> read(String name);

}
