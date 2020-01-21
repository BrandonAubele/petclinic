package com.example.petclinic.controller;

import java.util.List;

public interface BasicController<T> {

    T add(T t);

    T get(Long id);

    T modify(T t);

    boolean delete(T t);

    List<T> getAll();

}
