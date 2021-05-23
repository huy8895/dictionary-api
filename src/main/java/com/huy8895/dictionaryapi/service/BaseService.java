package com.huy8895.dictionaryapi.service;

import java.util.List;

public interface BaseService <T>{
    T create(T t);
    T get(Long id);
    void delete(T t);
    T update(T t);
    List<T> getList();
}
