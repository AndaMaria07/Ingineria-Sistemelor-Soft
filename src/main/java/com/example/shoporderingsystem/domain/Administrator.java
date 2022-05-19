package com.example.shoporderingsystem.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "USER")
public class Administrator extends User {

    public Administrator(String userName, String password, String name, String role) {
        super(userName,password,name,role);
    }


    public Administrator() {
        super();
    }
}