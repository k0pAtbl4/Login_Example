package com.example.demo.service;

import com.example.demo.entity.User;

public interface UserService {
    User create(User user);
    User findById(Integer id) throws Exception;
    User findByName(String name);
}