package com.example.shoporderingsystem.repository;

import java.util.List;

public interface Repository <T,Tid>{
    void add(T elem);
    void delete(T elem);
    void update(T elem);
    T findById(Tid id);
    List<T> findAll();

}
