package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Slf4j
@Service
public class UserServiceBean implements UserService{
    private final UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Override
    public User create(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public User findById(Integer id) throws Exception {
        User user = userRepository.findById(id).orElseThrow(() -> new Exception("User not found with id: " + id));

        if (user.isDeleted()) {
            throw new Exception("User was deleted");
        }
        return user;
    }
}