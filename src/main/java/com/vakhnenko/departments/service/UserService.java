package com.vakhnenko.departments.service;

import com.vakhnenko.departments.entity.User;

public interface UserService {

    void save(User user);

    User findByUsername(String username);
}
