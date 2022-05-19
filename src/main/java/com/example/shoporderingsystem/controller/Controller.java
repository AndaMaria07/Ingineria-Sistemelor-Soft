package com.example.shoporderingsystem.controller;

import com.example.shoporderingsystem.domain.Company;
import com.example.shoporderingsystem.domain.Product;
import com.example.shoporderingsystem.domain.User;
import com.example.shoporderingsystem.repository.CompanyRepositoryInterface;
import com.example.shoporderingsystem.repository.ProductRepositoryInterface;
import com.example.shoporderingsystem.repository.UserRepositoryInterface;

import java.util.ArrayList;
import java.util.List;

public class Controller {

    private UserRepositoryInterface userRepositoryInterface;
    private ProductRepositoryInterface productRepositoryInterface;
    private CompanyRepositoryInterface companyRepositoryInterface;

    public Controller(UserRepositoryInterface userRepositoryInterface,ProductRepositoryInterface productRepositoryInterface,CompanyRepositoryInterface companyRepositoryInterface) {
        this.userRepositoryInterface = userRepositoryInterface;
        this.productRepositoryInterface = productRepositoryInterface;
        this.companyRepositoryInterface = companyRepositoryInterface;
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

    public void addProduct(String name, String price, String quantity, String companyStr){
        List<Company> companies = companyRepositoryInterface.findAll();
        for(Company company : companies)
            if(company.getName().equals(companyStr)) {
                Product p = new Product(name, Double.parseDouble(price), Integer.parseInt(quantity), company.getId());
                productRepositoryInterface.add(p);
                break;
            }
    }

    public List<Company> getCompanies(){
        List<Company> companies = new ArrayList<>();
        companies = companyRepositoryInterface.findAll();
        return companies;

    }
}
