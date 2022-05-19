package com.example.shoporderingsystem.domain;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@MappedSuperclass
public abstract class User implements Identifiable<Integer> , Serializable {
    @Id
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
    @Column(name = "id", updatable=false, nullable=false, unique=true)
    private int Id;
    @Column(name = "username",nullable = false)
    private String userName;
    @Column(name = "password",nullable = false)
    private String password;
    @Column(name = "name",nullable = false)
    private String name;
    //@Enumerated(EnumType.STRING)
    @Column(name = "role",nullable = false)
    private String role;

    public User(String userName, String password, String name,String role) {
        this.userName = userName;
        this.password = password;
        this.name = name;
        this.role = role;
    }

    public User() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User employee = (User) o;
        return Id == employee.Id && Objects.equals(userName, employee.userName) && Objects.equals(password, employee.password) && Objects.equals(name, employee.name) && Objects.equals(role, employee.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userName, password, name, role, Id);
    }

    @Override
    public Integer getID() {
        return this.Id;
    }

    @Override
    public void setID(Integer integer) {
        this.Id = integer;
    }

    @Override
    public String toString() {
        return "User{" +
                "Id=" + Id +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
