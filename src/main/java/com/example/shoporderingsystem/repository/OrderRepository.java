package com.example.shoporderingsystem.repository;

import com.example.shoporderingsystem.domain.Order;
import com.example.shoporderingsystem.domain.Product;

import java.sql.*;
import java.util.*;

public class OrderRepository implements OrderRepositoryInterface{

    private String url;
    public OrderRepository(String url) {
        this.url = url;
    }


    @Override
    public void add(Order elem) {
        String sql = "insert into orders (product) values (?)";
        try (Connection connection = DriverManager.getConnection(url);
             PreparedStatement ps = connection.prepareStatement(sql)) {
             String products = "";
             for(Product p : elem.getProductList()){
                products += p.getName() + "," + p.getPrice() + "," + p.getQuantity() + ";";
             }
             ps.setString(1, products);
             ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Order elem) {

    }

    @Override
    public void update(Order elem) {

    }

    @Override
    public Order findById(Integer id) {
        return null;
    }


    @Override
    public List<Order> findAll() {
        List<Order> orders = new ArrayList<>();
        String sql = "SELECT * FROM orders";
        try (Connection connection = DriverManager.getConnection(url);
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery();) {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String products = resultSet.getString("product");
                String[] productListStr = products.split(";");
                List<Product> productsInOrder = new ArrayList<>();
                for (String s : productListStr) {
                    List<String> productStr = Arrays.asList(s.split(","));
                    Product p = new Product(productStr.get(0), Double.parseDouble(productStr.get(1)), Integer.parseInt(productStr.get(2)));
                    productsInOrder.add(p);
                }
                Order order = new Order(productsInOrder);
                order.setId(id);
                orders.add(order);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }
}
