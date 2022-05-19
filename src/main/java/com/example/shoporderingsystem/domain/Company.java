package com.example.shoporderingsystem.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class Company {
    private int Id;
    private String name;
    private List<Product> products;

    public Company(String name) {
        this.name = name;
        this.products = new ArrayList<>();
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "Company{" +
                "Id=" + Id +
                ", name='" + name + '\'' +
                ", products=" + products +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Company company = (Company) o;
        return Id == company.Id && Objects.equals(name, company.name) && Objects.equals(products, company.products);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Id, name, products);
    }
}
