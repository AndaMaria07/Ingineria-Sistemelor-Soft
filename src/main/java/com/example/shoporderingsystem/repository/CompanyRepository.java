package com.example.shoporderingsystem.repository;

import com.example.shoporderingsystem.domain.Company;
import com.example.shoporderingsystem.domain.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CompanyRepository implements CompanyRepositoryInterface{

    private String url;

    public CompanyRepository(String url) {
        this.url = url;
    }

    @Override
    public void add(Company elem) {
    }

    @Override
    public void delete(Company elem) {

    }

    @Override
    public void update(Company elem) {

    }

    @Override
    public Company findById(Integer id) {
        return null;
    }

    public Product findByName(String name, String companyName) throws SQLException {
        Product product = null;
        String sql = "SELECT * FROM products WHERE name = (?) AND company = (?)";
        Connection connection = DriverManager.getConnection(url);
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
             preparedStatement.setString(1,name);
             preparedStatement.setString(2,companyName);
             try(ResultSet resultSet = preparedStatement.executeQuery()){
                 if(resultSet.next()){
                     int id = resultSet.getInt(1);
                     String nameStr = resultSet.getString(2);
                     Double price = resultSet.getDouble(3);
                     int quantity = resultSet.getInt(4);
                     int company = resultSet.getInt(5);
                     Product product1 = new Product(nameStr,price,quantity,company);
                     product1.setId(id);
                     return product1;
                 }

             }
        }
       return null;
    }

    @Override
    public List<Company> findAll() {
        List<Company> companies = new ArrayList<>();
        String sql = "SELECT * FROM company";
        try (Connection connection = DriverManager.getConnection(url);
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery();) {
             while(resultSet.next()){
                 int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String p = resultSet.getString("products");
                List<String> products = Arrays.asList(p.split(";"));
                List<Product> productList = new ArrayList<>();
                for(String s: products)
                {
                    productList.add(findByName(s,name));
                }
                Company company = new Company(name);
                company.setProducts(productList);
                company.setId(id);
                companies.add(company);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return companies;
    }
}
