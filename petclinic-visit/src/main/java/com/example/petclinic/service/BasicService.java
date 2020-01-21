package com.example.petclinic.service;

import java.util.List;

public interface BasicService<T> {

    T add(T t);

    T get(Long id);

    T modify(T t);

    boolean delete(T t);

    List<T> getAll();
}
