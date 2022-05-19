package com.example.shoporderingsystem.domain;

public interface Identifiable<ID> {
    ID getID();
    void setID(ID id);
}
