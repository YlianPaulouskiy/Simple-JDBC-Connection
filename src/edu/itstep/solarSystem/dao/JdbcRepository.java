package edu.itstep.solarSystem.dao;

import java.util.List;
import java.util.Optional;

public interface JdbcRepository<T> {

    Optional<T> findOne(Long id);

    List<T> findAll();

    Optional<T> create(T model);

    Optional<T> update(T model);

    void remove(Long id);

}
