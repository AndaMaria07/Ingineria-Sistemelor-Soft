package com.example.shoporderingsystem.controller;
import com.example.shoporderingsystem.domain.Order;
import com.example.shoporderingsystem.domain.Product;
import com.example.shoporderingsystem.domain.User;
import com.example.shoporderingsystem.repository.OrderRepositoryInterface;
import com.example.shoporderingsystem.repository.ProductRepositoryInterface;
import com.example.shoporderingsystem.repository.UserRepositoryInterface;

import java.util.ArrayList;
import java.util.List;

public class Controller {

    private UserRepositoryInterface userRepositoryInterface;
    private ProductRepositoryInterface productRepositoryInterface;
    private OrderRepositoryInterface orderRepositoryInterface;
    private List<Product> productsForCart;

    public Controller(UserRepositoryInterface userRepositoryInterface, ProductRepositoryInterface productRepositoryInterface, OrderRepositoryInterface orderRepositoryInterface) {
        this.userRepositoryInterface = userRepositoryInterface;
        this.productRepositoryInterface = productRepositoryInterface;
        this.orderRepositoryInterface = orderRepositoryInterface;
        this.productsForCart = new ArrayList<>();
    }

    public void setProductsForCart(List<Product> productsForCart) {
        this.productsForCart = productsForCart;
    }

    public User findOneByNameAndPassword(String userName, String password) throws Exception {
        User user = userRepositoryInterface.findByCredentials(userName,password);
        return user;
    }

    public List<Product> getProducts(){
        List<Product> products = new ArrayList<>();
        products = productRepositoryInterface.findAll();
        return products;
    }

    public void addProduct(String name, String price, String quantity){
        Product p = new Product(name, Double.parseDouble(price), Integer.parseInt(quantity));
        productRepositoryInterface.add(p);
    }

    public void updateProduct(int id,String name, String price, String quantity){
        Product p = new Product(name, Double.parseDouble(price), Integer.parseInt(quantity));
        p.setId(id);
        productRepositoryInterface.update(p);
    }

    public void deleteProduct(int id,String name, String price, String quantity, String companyStr){
        Product p = new Product(name, Double.parseDouble(price), Integer.parseInt(quantity));
        p.setId(id);
        productRepositoryInterface.delete(p);
    }

    public List<Product> getProductsForCart(){
        return productsForCart;
    }

    public void addInCart(Product p){
        productsForCart.add(p);
    }

    public List<Order> getOrders() {
        return orderRepositoryInterface.findAll();
    }

    public void addOrder(Order order){
        orderRepositoryInterface.add(order);
    }

    public void modifyCart(Product selected, String q) {
        for(Product p : productsForCart){
            if(p.equals(selected)){
                p.setQuantity(Integer.parseInt(q));
            }
        }
    }

    public void deleteFromCart(Product selected) {
        productsForCart.remove(selected);
    }
}
