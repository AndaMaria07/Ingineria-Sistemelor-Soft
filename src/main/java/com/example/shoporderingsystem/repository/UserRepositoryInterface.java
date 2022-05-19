package com.example.shoporderingsystem.repository;

import com.example.shoporderingsystem.domain.User;

public interface UserRepositoryInterface extends Repository<User,Integer> {
    public User findByCredentials(String userName, String password) throws Exception;
}
