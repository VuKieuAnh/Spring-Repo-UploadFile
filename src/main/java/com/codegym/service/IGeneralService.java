package com.codegym.service;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

public interface  IGeneralService<T> {
        Iterable<T> findAll();

        Optional<T> findById(Long id);

        void save(T t);

        void remove(Long id);
}
