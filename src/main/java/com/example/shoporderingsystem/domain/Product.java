package com.example.shoporderingsystem.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "PRODUCTS")
public class Product {
    @Id
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
    @Column(name = "id", updatable=false, nullable=false, unique=true)
    private int Id;
    @Column(name = "name",nullable = false)
    private String name;
    @Column(name = "price",nullable = false)
    private Double price;
    @Column(name = "quantity",nullable = false)
    private int quantity;
    @Column(name = "company",nullable = false)
    private int idCompany;

    public Product(String name, Double price, int quantity, int idCompany) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.idCompany = idCompany;
    }

    public Product(){}

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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getCompany() {
        return idCompany;
    }

    public void setCompany(int company) {
        this.idCompany = company;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Id == product.Id && quantity == product.quantity && Objects.equals(name, product.name) && Objects.equals(price, product.price) && Objects.equals(idCompany, product.idCompany);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Id, name, price, quantity, idCompany);
    }

    @Override
    public String toString() {
        return "Product{" +
                "Id=" + Id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", company=" + idCompany +
                '}';
    }
}
