package com.example.shoporderingsystem.domain;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.List;


public class Order {

    private Integer id;
    private List<Product> productList;
    private String infoOrder;

    public Order(List<Product> productList) {
        this.productList  = productList;
    }

    public String getInfoOrder() {
        return infoOrder;
    }

    public void setInfoOrder(String infoOrder) {
        this.infoOrder = infoOrder;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }



    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }
}
