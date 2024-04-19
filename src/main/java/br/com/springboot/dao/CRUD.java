package br.com.springboot.dao;

import java.util.List;

public interface CRUD<T, ID>{

    T findById(ID id);
    List<T> list();
    void insert(T t);
    void update(T t);
    void delete(T t);
}
